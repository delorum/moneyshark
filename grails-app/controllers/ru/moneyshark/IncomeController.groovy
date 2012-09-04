package ru.moneyshark

import org.springframework.dao.DataIntegrityViolationException

class IncomeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

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
        if (!incomeInstance.save(flush: true)) {
            render(view: "create", model: [incomeInstance: incomeInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'income.label', default: 'Income'), incomeInstance.id])
        redirect(action: "show", id: incomeInstance.id)
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

        incomeInstance.properties = params

        if (!incomeInstance.save(flush: true)) {
            render(view: "edit", model: [incomeInstance: incomeInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'income.label', default: 'Income'), incomeInstance.id])
        redirect(action: "show", id: incomeInstance.id)
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
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'income.label', default: 'Income'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'income.label', default: 'Income'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
