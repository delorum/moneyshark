package ru.moneyshark

import org.springframework.dao.DataIntegrityViolationException

class OutcomeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		def outcomeInstanceList = Outcome.findAllByUser(session.user, params)
        [
			outcomeInstanceList: outcomeInstanceList, 
			outcomeInstanceTotal: outcomeInstanceList.size()
		]
    }

    def create() {
        [outcomeInstance: new Outcome(params)]
    }

    def save() {
        def outcomeInstance = new Outcome(
			amount:new TwoIntegers(int1:params.amount as Integer, int2:session.user.id),
			comment:new StringInteger(s:params.comment, i:session.user.id),
			date:params.date,
			status: params.status,
			user: session.user
		)
        if (!outcomeInstance.save(flush: true)) {
            render(view: "create", model: [outcomeInstance: outcomeInstance])
            return
        } else if(outcomeInstance.status == "accepted") {
			// updating balance
			def current_balance = Balance.findAllByUser(session.user, [sort:"id", order:"desc", max:1])?.find{it}?.balance?.int1?:0
			def new_balance = new Balance(balance:new TwoIntegers(int1:current_balance-outcomeInstance.amount.int1, int2: session.user.id), 
										  date:outcomeInstance.date, 
										  user:session.user, 
										  comment:new StringInteger(s:"Потрачено: "+outcomeInstance.amount.int1+" ("+outcomeInstance.comment.s+")", i:session.user.id))
			new_balance.save(failOnError: true/*flush:true*/)
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'outcome.label', default: 'Outcome'), outcomeInstance.id])
        redirect(action: "list")
    }

    def edit() {
        def outcomeInstance = Outcome.get(params.id)
        if (!outcomeInstance || outcomeInstance.user.id != session.user.id) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'outcome.label', default: 'Outcome'), params.id])
            redirect(action: "list")
            return
        }

        [outcomeInstance: outcomeInstance]
    }

    def update() {
        def outcomeInstance = Outcome.get(params.id)
        if (!outcomeInstance || outcomeInstance.user.id != session.user.id) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'outcome.label', default: 'Outcome'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (outcomeInstance.version > version) {
                outcomeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'outcome.label', default: 'Outcome')] as Object[],
                          "Another user has updated this Outcome while you were editing")
                render(view: "edit", model: [outcomeInstance: outcomeInstance])
                return
            }
        }

		def previous_outcome_amount = outcomeInstance.amount.int1
		def previous_outcome_status = outcomeInstance.status
        outcomeInstance.amount = new TwoIntegers(int1:params.amount as Integer, int2:session.user.id)
		outcomeInstance.comment = new StringInteger(s:params.comment, i:session.user.id)
		outcomeInstance.date = params.date
		outcomeInstance.status = params.status

        if (!outcomeInstance.save(flush: true)) {
            render(view: "edit", model: [outcomeInstance: outcomeInstance])
            return
        } else {
			// updating balance
			def balances = Balance.findAllByDateGreaterThanEquals(outcomeInstance.date)
			balances.each {
				if(previous_outcome_status == "accepted") it.balance.int1 += previous_outcome_amount
				if(outcomeInstance.status == "accepted") it.balance.int1 -= outcomeInstance.amount.int1
				it.comment.s += " ("+"Обновлен расход: "+outcomeInstance.comment+")"
				it.save(failOnError: true)
			}		
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'outcome.label', default: 'Outcome'), outcomeInstance.id])
        redirect(action: "list")
    }

    def delete() {
        def outcomeInstance = Outcome.get(params.id)
        if (!outcomeInstance || outcomeInstance.user.id != session.user.id) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'outcome.label', default: 'Outcome'), params.id])
            redirect(action: "list")
            return
        }

        try {
            outcomeInstance.delete(flush: true)
			
			// updating balance
			if(outcomeInstance.status == "accepted") {
				def balances = Balance.findAllByDateGreaterThanEquals(outcomeInstance.date)
				balances.each {
					it.balance.int1 += outcomeInstance.amount.int1
					it.comment.s += " ("+"Отменен расход: "+outcomeInstance.comment.s+")"
					it.save(failOnError: true)
				}
			}
			
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'outcome.label', default: 'Outcome'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'outcome.label', default: 'Outcome'), params.id])
            redirect(action: "list", id: params.id)
        }
    }
	
	def accept = {
		def outcomeInstance = Outcome.get(params.id)
		if (!outcomeInstance || outcomeInstance.user.id != session.user.id || outcomeInstance.status != "waiting") {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'outcome.label'), params.id])
			redirect(controller: "balance", action: "list")
			return
		}

		if (params.version) {
			def version = params.version.toLong()
			if (outcomeInstance.version > version) {
				outcomeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						  [message(code: 'outcome.label')] as Object[],
						  "Another user has updated this Outcome while you were editing")
				render(view: "edit", model: [outcomeInstance: outcomeInstance])
				return
			}
		}

		outcomeInstance.status = "accepted"

		if (!outcomeInstance.save(flush: true)) {
			render(view: "edit", model: [outcomeInstance: outcomeInstance])
			return
		} else {
			// updating balance
			def current_balance = Balance.findAllByUser(session.user, [sort:"id", order:"desc", max:1])?.find{it}?.balance?.int1?:0
			def new_balance = new Balance(balance:new TwoIntegers(int1:current_balance-outcomeInstance.amount.int1, int2:session.user.id),
										  date:outcomeInstance.date,
										  user:session.user,
										  comment:new StringInteger(s:"Поступление: "+outcomeInstance.amount.int1+" ("+outcomeInstance.comment.s+")", i:session.user.id))
			new_balance.save(failOnError: true/*flush:true*/)
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'outcome.label'), outcomeInstance.id])
		redirect(controller: "balance", action: "list")
	}
}
