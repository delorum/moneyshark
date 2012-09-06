package ru.moneyshark

import org.springframework.dao.DataIntegrityViolationException

class PeriodicOutcomeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [periodicOutcomeInstanceList: PeriodicOutcome.list(params), periodicOutcomeInstanceTotal: PeriodicOutcome.count()]
    }

    def create() {
        [periodicOutcomeInstance: new PeriodicOutcome(params)]
    }

    def save() {
		def periodicity = 0L
		def period_amount = (params.periodamount != "") ? params.periodamount as Long : 0L
		def period_unit = params.periodunit
		switch(period_unit) {
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
        def periodicOutcomeInstance = new PeriodicOutcome(params)
		
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
		
		periodicOutcomeInstance.periodicity = periodicity
		periodicOutcomeInstance.periodUnit = period_unit
		periodicOutcomeInstance.user = session.user
		
        if (!periodicOutcomeInstance.save(flush: true)) {
            render(view: "create", model: [periodicOutcomeInstance: periodicOutcomeInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'periodicOutcome.label', default: 'PeriodicOutcome'), periodicOutcomeInstance.id])
        redirect(action: "list", id: periodicOutcomeInstance.id)
    }

    def show() {
        def periodicOutcomeInstance = PeriodicOutcome.get(params.id)
        if (!periodicOutcomeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'periodicOutcome.label', default: 'PeriodicOutcome'), params.id])
            redirect(action: "list")
            return
        }

        [periodicOutcomeInstance: periodicOutcomeInstance]
    }

    def edit() {
        def periodicOutcomeInstance = PeriodicOutcome.get(params.id)
        if (!periodicOutcomeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'periodicOutcome.label', default: 'PeriodicOutcome'), params.id])
            redirect(action: "list")
            return
        }
		def periodAmount = 0
		switch(periodicOutcomeInstance.periodUnit) {
			case 'hour':
				periodAmount = periodicOutcomeInstance.periodicity/(60*60*1000)
				break
			case 'day':
				periodAmount = periodicOutcomeInstance.periodicity/(24*60*60*1000)
				break
			case 'week':
				periodAmount = periodicOutcomeInstance.periodicity/(7*24*60*60*1000)
				break
			case 'month':
				periodAmount = periodicOutcomeInstance.periodicity/(30*24*60*60*1000)
				break
		}
        [
			periodAmount: periodAmount,
			periodicOutcomeInstance: periodicOutcomeInstance
		]
    }

    def update() {
		def periodicity = 0L
		def period_amount = (params.periodamount != "") ? params.periodamount as Long : 0L
		def period_unit = params.periodunit
		switch(period_unit) {
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
        def periodicOutcomeInstance = PeriodicOutcome.get(params.id)
        if (!periodicOutcomeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'periodicOutcome.label', default: 'PeriodicOutcome'), params.id])
            redirect(action: "list")
            return
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

        periodicOutcomeInstance.properties = params
		periodicOutcomeInstance.periodicity = periodicity
		periodicOutcomeInstance.periodUnit = period_unit

        if (!periodicOutcomeInstance.save(flush: true)) {
            render(view: "edit", model: [periodicOutcomeInstance: periodicOutcomeInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'periodicOutcome.label', default: 'PeriodicOutcome'), periodicOutcomeInstance.id])
        redirect(action: "list", id: periodicOutcomeInstance.id)
    }

    def delete() {
        def periodicOutcomeInstance = PeriodicOutcome.get(params.id)
        if (!periodicOutcomeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'periodicOutcome.label', default: 'PeriodicOutcome'), params.id])
            redirect(action: "list")
            return
        }

        try {
            periodicOutcomeInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'periodicOutcome.label', default: 'PeriodicOutcome'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'periodicOutcome.label', default: 'PeriodicOutcome'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
