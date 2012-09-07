package ru.moneyshark

import org.springframework.dao.DataIntegrityViolationException

class PeriodicIncomeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [periodicIncomeInstanceList: PeriodicIncome.list(params), periodicIncomeInstanceTotal: PeriodicIncome.count()]
    }

    def create() {
        [periodicIncomeInstance: new PeriodicIncome(params)]
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
        def periodicIncomeInstance = new PeriodicIncome(params)
		
		if(period_amount == 0L) {
			periodicIncomeInstance.errors.rejectValue("periodicity", message(code: "periodicincome.error.periodamountempty"))
			render(view: "create", model: [periodicIncomeInstance: periodicIncomeInstance])
			return
		}
		
		if(period_unit == "") {
			periodicIncomeInstance.errors.rejectValue("periodicity", message(code: "periodicincome.error.periodunitempty"))
			render(view: "create", model: [periodicIncomeInstance: periodicIncomeInstance])
			return
		}
		
		periodicIncomeInstance.periodicity = periodicity
		periodicIncomeInstance.periodUnit = period_unit
		periodicIncomeInstance.user = session.user
		
		
		
        if (!periodicIncomeInstance.save(flush: true)) {
            render(view: "create", model: [periodicIncomeInstance: periodicIncomeInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'periodicIncome.label', default: 'PeriodicIncome'), periodicIncomeInstance.id])
        redirect(action: "list", id: periodicIncomeInstance.id)
    }

    /*def show() {
        def periodicIncomeInstance = PeriodicIncome.get(params.id)
        if (!periodicIncomeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'periodicIncome.label', default: 'PeriodicIncome'), params.id])
            redirect(action: "list")
            return
        }

        [periodicIncomeInstance: periodicIncomeInstance]
    }*/

    def edit() {
        def periodicIncomeInstance = PeriodicIncome.get(params.id)
        if (!periodicIncomeInstance || periodicIncomeInstance.user.id != session.user.id) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'periodicIncome.label', default: 'PeriodicIncome'), params.id])
            redirect(action: "list")
            return
        }
		def periodAmount = 0
		switch(periodicIncomeInstance.periodUnit) {
			case 'hour':
				periodAmount = periodicIncomeInstance.periodicity/(60*60*1000)
				break
			case 'day':
				periodAmount = periodicIncomeInstance.periodicity/(24*60*60*1000)
				break
			case 'week':
				periodAmount = periodicIncomeInstance.periodicity/(7*24*60*60*1000)
				break
			case 'month':
				periodAmount = periodicIncomeInstance.periodicity/(30*24*60*60*1000)
				break
		}
        [
			periodAmount: periodAmount,
			periodicIncomeInstance: periodicIncomeInstance
		]
    }

    def update() {
        def periodicIncomeInstance = PeriodicIncome.get(params.id)
        if (!periodicIncomeInstance || periodicIncomeInstance.user.id != session.user.id) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'periodicIncome.label', default: 'PeriodicIncome'), params.id])
            redirect(action: "list")
            return
        }
		
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
		
		if(period_amount == 0L) {
			periodicIncomeInstance.errors.rejectValue("periodicity", message(code: "periodicincome.error.periodamountempty"))
			render(view: "edit", model: [periodicIncomeInstance: periodicIncomeInstance])
			return
		}
		
		if(period_unit == "") {
			periodicIncomeInstance.errors.rejectValue("periodicity", message(code: "periodicincome.error.periodunitempty"))
			render(view: "edit", model: [periodicIncomeInstance: periodicIncomeInstance])
			return
		}

        if (params.version) {
            def version = params.version.toLong()
            if (periodicIncomeInstance.version > version) {
                periodicIncomeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'periodicIncome.label', default: 'PeriodicIncome')] as Object[],
                          "Another user has updated this PeriodicIncome while you were editing")
                render(view: "edit", model: [periodicIncomeInstance: periodicIncomeInstance])
                return
            }
        }

        periodicIncomeInstance.properties = params
		periodicIncomeInstance.periodicity = periodicity
		periodicIncomeInstance.periodUnit = period_unit

        if (!periodicIncomeInstance.save(flush: true)) {
            render(view: "edit", model: [periodicIncomeInstance: periodicIncomeInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'periodicIncome.label', default: 'PeriodicIncome'), periodicIncomeInstance.id])
        redirect(action: "list", id: periodicIncomeInstance.id)
    }

    def delete() {
        def periodicIncomeInstance = PeriodicIncome.get(params.id)
        if (!periodicIncomeInstance || periodicIncomeInstance.user.id != session.user.id) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'periodicIncome.label', default: 'PeriodicIncome'), params.id])
            redirect(action: "list")
            return
        }

        try {
            periodicIncomeInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'periodicIncome.label', default: 'PeriodicIncome'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'periodicIncome.label', default: 'PeriodicIncome'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
