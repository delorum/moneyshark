package ru.moneyshark

import org.springframework.dao.DataIntegrityViolationException

class OutcomeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [outcomeInstanceList: Outcome.list(params), outcomeInstanceTotal: Outcome.count()]
    }

    def create() {
        [outcomeInstance: new Outcome(params)]
    }

    def save() {
        def outcomeInstance = new Outcome(params)
        if (!outcomeInstance.save(flush: true)) {
            render(view: "create", model: [outcomeInstance: outcomeInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'outcome.label', default: 'Outcome'), outcomeInstance.id])
        redirect(action: "show", id: outcomeInstance.id)
    }

    def show() {
        def outcomeInstance = Outcome.get(params.id)
        if (!outcomeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'outcome.label', default: 'Outcome'), params.id])
            redirect(action: "list")
            return
        }

        [outcomeInstance: outcomeInstance]
    }

    def edit() {
        def outcomeInstance = Outcome.get(params.id)
        if (!outcomeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'outcome.label', default: 'Outcome'), params.id])
            redirect(action: "list")
            return
        }

        [outcomeInstance: outcomeInstance]
    }

    def update() {
        def outcomeInstance = Outcome.get(params.id)
        if (!outcomeInstance) {
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

        outcomeInstance.properties = params

        if (!outcomeInstance.save(flush: true)) {
            render(view: "edit", model: [outcomeInstance: outcomeInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'outcome.label', default: 'Outcome'), outcomeInstance.id])
        redirect(action: "show", id: outcomeInstance.id)
    }

    def delete() {
        def outcomeInstance = Outcome.get(params.id)
        if (!outcomeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'outcome.label', default: 'Outcome'), params.id])
            redirect(action: "list")
            return
        }

        try {
            outcomeInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'outcome.label', default: 'Outcome'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'outcome.label', default: 'Outcome'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
