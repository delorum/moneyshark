package ru.moneyshark

import org.springframework.dao.DataIntegrityViolationException

class PeriodicOutcomeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		def periodicOutcomeInstanceList = PeriodicOutcome.findAll(params) {
			user == session.user &&
			(stopMoment >= new Date() || stopMoment == null)
		}
        [
			periodicOutcomeInstanceList: periodicOutcomeInstanceList, 
			periodicOutcomeInstanceTotal: PeriodicOutcome.count()
		]
    }

    def create() {
        [periodicOutcomeInstance: new PeriodicOutcome(params)]
    }

    def save() {
		def periodicity = 0L
		def period_amount = (params.periodamount != "") ? params.periodamount as Long : 0L
		def period_unit = params.periodunit
		switch(period_unit) {
			case 'minute':
				periodicity += period_amount*(60*1000)
				break
			case 'hour':
				periodicity += period_amount*(60*60*1000)
				break
			case 'day':
				periodicity += period_amount*(24*60*60*1000)
				break
			case 'week':
				periodicity += period_amount*(7*24*60*60*1000)
				break
			case 'month':
				periodicity += period_amount*(30*24*60*60*1000)
				break
		}
        def periodicOutcomeInstance = new PeriodicOutcome(
			amount:new TwoIntegers(int1:params.amount as Integer, int2:session.user.id),
			comment:new StringInteger(s:params.comment, i:session.user.id),
			startMoment:params.startMoment
		)
		
		if(period_amount == 0L) {
			periodicOutcomeInstance.errors.rejectValue("periodicity", message(code: "periodicoutcome.error.periodamountempty"))
			render(view: "create", model: [periodicOutcomeInstance: periodicOutcomeInstance])
			return
		}
		
		if(period_unit == "") {
			periodicOutcomeInstance.errors.rejectValue("periodicity", message(code: "periodicoutcome.error.periodunitempty"))
			render(view: "create", model: [periodicOutcomeInstance: periodicOutcomeInstance])
			return
		}
		
		periodicOutcomeInstance.periodicity = new LongInteger(l:periodicity, i:session.user.id)
		periodicOutcomeInstance.periodUnit = new StringInteger(s:period_unit, i:session.user.id)
		periodicOutcomeInstance.user = session.user
		
        if (!periodicOutcomeInstance.save(flush: true)) {
            render(view: "create", model: [periodicOutcomeInstance: periodicOutcomeInstance])
            return
        }

		flash.message = message(code: 'periodicoutcome.created.message')
        redirect(action: "list", id: periodicOutcomeInstance.id)
    }

    def edit() {
        def periodicOutcomeInstance = PeriodicOutcome.get(params.id)
        if (!periodicOutcomeInstance || periodicOutcomeInstance.user.id != session.user.id) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'periodicOutcome.label', default: 'PeriodicOutcome'), params.id])
            redirect(action: "list")
            return
        }
		def periodAmount = 0
		switch(periodicOutcomeInstance.periodUnit.s) {
			case 'minute':
				periodAmount = periodicOutcomeInstance.periodicity.l/(60*1000)
				break
			case 'hour':
				periodAmount = periodicOutcomeInstance.periodicity.l/(60*60*1000)
				break
			case 'day':
				periodAmount = periodicOutcomeInstance.periodicity.l/(24*60*60*1000)
				break
			case 'week':
				periodAmount = periodicOutcomeInstance.periodicity.l/(7*24*60*60*1000)
				break
			case 'month':
				periodAmount = periodicOutcomeInstance.periodicity.l/(30*24*60*60*1000)
				break
		}
        [
			periodAmount: periodAmount,
			periodicOutcomeInstance: periodicOutcomeInstance
		]
    }

    def update() {
        def periodicOutcomeInstance = PeriodicOutcome.get(params.id)
        if (!periodicOutcomeInstance || periodicOutcomeInstance.user.id != session.user.id) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'periodicOutcome.label', default: 'PeriodicOutcome'), params.id])
            redirect(action: "list")
            return
        }
		
		def periodicity = 0L
		def period_amount = (params.periodamount != "") ? params.periodamount as Long : 0L
		def period_unit = params.periodunit
		switch(period_unit) {
			case 'minute':
				periodicity += period_amount*(60*1000)
				break
			case 'hour':
				periodicity += period_amount*(60*60*1000)
				break
			case 'day':
				periodicity += period_amount*(24*60*60*1000)
				break
			case 'week':
				periodicity += period_amount*(7*24*60*60*1000)
				break
			case 'month':
				periodicity += period_amount*(30*24*60*60*1000)
				break
		}
		
		if(period_amount == 0L) {
			periodicOutcomeInstance.errors.rejectValue("periodicity", message(code: "periodicoutcome.error.periodamountempty"))
			render(view: "edit", model: [periodicOutcomeInstance: periodicOutcomeInstance])
			return
		}
		
		if(period_unit == "") {
			periodicOutcomeInstance.errors.rejectValue("periodicity", message(code: "periodicoutcome.error.periodunitempty"))
			render(view: "edit", model: [periodicOutcomeInstance: periodicOutcomeInstance])
			return
		}

        if (params.version) {
            def version = params.version.toLong()
            if (periodicOutcomeInstance.version > version) {
                periodicOutcomeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'periodicOutcome.label', default: 'PeriodicOutcome')] as Object[],
                          "Another user has updated this PeriodicOutcome while you were editing")
                render(view: "edit", model: [periodicOutcomeInstance: periodicOutcomeInstance])
                return
            }
        }
		
		periodicOutcomeInstance.amount = new TwoIntegers(int1:params.amount as Integer, int2:session.user.id)
		periodicOutcomeInstance.comment = new StringInteger(s:params.comment, i:session.user.id)
		periodicOutcomeInstance.startMoment = params.startMoment
		periodicOutcomeInstance.periodicity = new LongInteger(l:periodicity, i:session.user.id)
		periodicOutcomeInstance.periodUnit = new StringInteger(s:period_unit, i:session.user.id)

        if (!periodicOutcomeInstance.save(flush: true)) {
            render(view: "edit", model: [periodicOutcomeInstance: periodicOutcomeInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'periodicOutcome.label', default: 'PeriodicOutcome'), periodicOutcomeInstance.id])
        redirect(action: "list", id: periodicOutcomeInstance.id)
    }

    def delete() {
        def periodicOutcomeInstance = PeriodicOutcome.get(params.id)
        if (!periodicOutcomeInstance || periodicOutcomeInstance.user.id != session.user.id) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'periodicOutcome.label', default: 'PeriodicOutcome'), params.id])
            redirect(action: "list")
            return
        }

        try {
            periodicOutcomeInstance.stopMoment = new Date()
            periodicOutcomeInstance.save(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'periodicOutcome.label', default: 'PeriodicOutcome'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'periodicOutcome.label', default: 'PeriodicOutcome'), params.id])
            redirect(action: "list", id: params.id)
        }
    }
}
