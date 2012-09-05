package ru.moneyshark

import org.springframework.dao.DataIntegrityViolationException

class IncomeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [incomeInstanceList: Income.list(params), incomeInstanceTotal: Income.count()]
    }

    def create() {
        [incomeInstance: new Income(params)]
    }

    def save() {
        def incomeInstance = new Income(params)
		incomeInstance.status = "accepted"
		incomeInstance.user = session.user
        if (!incomeInstance.save(flush: true)) {
            render(view: "create", model: [incomeInstance: incomeInstance])
            return
        } else {
			// updating balance
			def current_balance = Balance.list(sort:"id", order:"desc", max:1)?.find{it}?.balance?:0
			def new_balance = new Balance(balance:current_balance+incomeInstance.amount, date:incomeInstance.date, user:session.user, comment:incomeInstance.comment)
			new_balance.save(failOnError: true/*flush:true*/)
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'income.label', default: 'Income'), incomeInstance.id])
        redirect(action: "list")
    }

    def show() {
        def incomeInstance = Income.get(params.id)
        if (!incomeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'income.label', default: 'Income'), params.id])
            redirect(action: "list")
            return
        }

        [incomeInstance: incomeInstance]
    }

    def edit() {
        def incomeInstance = Income.get(params.id)
        if (!incomeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'income.label', default: 'Income'), params.id])
            redirect(action: "list")
            return
        }

        [incomeInstance: incomeInstance]
    }

    def update() {
        def incomeInstance = Income.get(params.id)
        if (!incomeInstance) {
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

		def old_amount = incomeInstance.amount
        incomeInstance.properties = params

        if (!incomeInstance.save(flush: true)) {
            render(view: "edit", model: [incomeInstance: incomeInstance])
            return
        } else {
			// updating balance
			def balances = Balance.findAllByDateGreaterThanEquals(incomeInstance.date)
			balances.each {
				it.balance -= old_amount
				it.balance += incomeInstance.amount
				it.comment += " ("+"Обновлено: "+incomeInstance.comment+")"
				it.save(failOnError: true)
			}		
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'income.label', default: 'Income'), incomeInstance.id])
        redirect(action: "list")
    }

    def delete() {
        def incomeInstance = Income.get(params.id)
        if (!incomeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'income.label', default: 'Income'), params.id])
            redirect(action: "list")
            return
        }

        try {
            incomeInstance.delete(flush: true)
			
			// updating balance
			def balances = Balance.findAllByDateGreaterThanEquals(incomeInstance.date)
			balances.each {
				it.balance -= incomeInstance.amount
				it.comment += " ("+"Отменено: "+incomeInstance.comment+")"
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
}
