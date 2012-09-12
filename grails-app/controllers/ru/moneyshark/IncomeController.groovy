package ru.moneyshark

import org.springframework.dao.DataIntegrityViolationException

class IncomeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET", accept: "GET"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		def incomeInstanceList = Income.findAllByUserAndStatus(session.user, "accepted", params)
        [
			incomeInstanceList: incomeInstanceList,
			incomeInstanceTotal: incomeInstanceList.size()
		]
    }

    def create() {
        [incomeInstance: new Income(params)]
    }

    def save() {		
        def incomeInstance = new Income(
			amount:new TwoIntegers(int1:params.amount as Integer, int2:session.user.id),
			comment:new StringInteger(s:params.comment, i:session.user.id),
			date:params.date,
			status: "accepted",
			user: session.user
		)
		
        if (!incomeInstance.save(flush: true)) {
            render(view: "create", model: [incomeInstance: incomeInstance])
            return
        } else {
			// updating balance
			def current_balance = Balance.findAllByUser(session.user, [sort:"id", order:"desc", max:1])?.find{it}?.balance?:0
			def new_balance = new Balance(balance:new TwoIntegers(int1: current_balance+incomeInstance.amount.int1, int2: session.user.id), 
										  date:incomeInstance.date, 
										  user:session.user, 
										  comment:new StringInteger(s:"Поступление: "+incomeInstance.amount.int1+" ("+incomeInstance.comment.s+")", i:session.user.id))
			new_balance.save(failOnError: true/*flush:true*/)
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'income.label', default: 'Income'), incomeInstance.id])
        redirect(action: "list")
    }

    def edit() {
        def incomeInstance = Income.get(params.id)
        if (!incomeInstance || incomeInstance.user.id != session.user.id) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'income.label', default: 'Income'), params.id])
            redirect(action: "list")
            return
        }

        [incomeInstance: incomeInstance]
    }

    def update() {
        def incomeInstance = Income.get(params.id)
        if (!incomeInstance || incomeInstance.user.id != session.user.id) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'income.label', default: 'Income'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (incomeInstance.version > version) {
                incomeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'income.label', default: 'Income')] as Object[],
                          "Another user has updated this Income while you were editing")
                render(view: "edit", model: [incomeInstance: incomeInstance])
                return
            }
        }

		def old_amount = incomeInstance.amount.int1
        incomeInstance.properties = params

        if (!incomeInstance.save(flush: true)) {
            render(view: "edit", model: [incomeInstance: incomeInstance])
            return
        } else {
			// updating balance
			def balances = Balance.findAllByDateGreaterThanEquals(incomeInstance.date)
			balances.each {
				it.balance.int1 -= old_amount
				it.balance.int1 += incomeInstance.amount
				it.comment.s += " ("+"Обновлено поступление: "+incomeInstance.comment+")"
				it.save(failOnError: true)
			}		
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'income.label', default: 'Income'), incomeInstance.id])
        redirect(action: "list")
    }

    def delete() {
        def incomeInstance = Income.get(params.id)
        if (!incomeInstance || incomeInstance.user.id != session.user.id) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'income.label', default: 'Income'), params.id])
            redirect(action: "list")
            return
        }

        try {
            incomeInstance.delete(flush: true)
			
			// updating balance
			def balances = Balance.findAllByDateGreaterThanEquals(incomeInstance.date)
			balances.each {
				it.balance.int1 -= incomeInstance.amount
				it.comment.s += " ("+"Отменено поступление: "+incomeInstance.comment+")"
				it.save(failOnError: true)	
			}
			
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'income.label', default: 'Income'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'income.label', default: 'Income'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
	
	def accept = {
		def incomeInstance = Income.get(params.id)
		if (!incomeInstance || incomeInstance.user.id != session.user.id || incomeInstance.status != "waiting") {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'income.label', default: 'Income'), params.id])
			redirect(controller: "balance", action: "list")
			return
		}

		if (params.version) {
			def version = params.version.toLong()
			if (incomeInstance.version > version) {
				incomeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						  [message(code: 'income.label', default: 'Income')] as Object[],
						  "Another user has updated this Income while you were editing")
				render(view: "edit", model: [incomeInstance: incomeInstance])
				return
			}
		}

		incomeInstance.status = "accepted"

		if (!incomeInstance.save(flush: true)) {
			render(view: "edit", model: [incomeInstance: incomeInstance])
			return
		} else {
			// updating balance
			def current_balance = Balance.findAllByUser(session.user, [sort:"id", order:"desc", max:1])?.find{it}?.balance.int1?:0
			def new_balance = new Balance(balance:new TwoIntegers(int1: current_balance+incomeInstance.amount, int2: session.user.id), 
										  date:incomeInstance.date, 
										  user:session.user, 
										  comment:new StringInteger(s:"Поступление: "+incomeInstance.amount+" ("+incomeInstance.comment+")", i:session.user.id))
			new_balance.save(failOnError: true/*flush:true*/)
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'income.label', default: 'Income'), incomeInstance.id])
		redirect(controller: "balance", action: "list")
	}
}

class NewIncomeCommand {
	Integer amount
	String comment
	Date date
	String password
	String password2

	static constraints = {
		amount(blank:false)
		comment(nullable:true)
	}
}
