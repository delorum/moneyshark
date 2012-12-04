package ru.moneyshark

import org.springframework.dao.DataIntegrityViolationException

class PromoCodeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [promoCodeInstanceList: PromoCode.list(params), promoCodeInstanceTotal: PromoCode.count()]
    }

    def create() {
        [promoCodeInstance: new PromoCode(params)]
    }

    def save() {
        def promoCodeInstance = new PromoCode(params)
        if (!promoCodeInstance.save(flush: true)) {
            render(view: "create", model: [promoCodeInstance: promoCodeInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'promoCode.label', default: 'PromoCode'), promoCodeInstance.id])
        redirect(action: "show", id: promoCodeInstance.id)
    }

    def show() {
        def promoCodeInstance = PromoCode.get(params.id)
        if (!promoCodeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'promoCode.label', default: 'PromoCode'), params.id])
            redirect(action: "list")
            return
        }

        [promoCodeInstance: promoCodeInstance]
    }

    def edit() {
        def promoCodeInstance = PromoCode.get(params.id)
        if (!promoCodeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'promoCode.label', default: 'PromoCode'), params.id])
            redirect(action: "list")
            return
        }

        [promoCodeInstance: promoCodeInstance]
    }

    def update() {
        def promoCodeInstance = PromoCode.get(params.id)
        if (!promoCodeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'promoCode.label', default: 'PromoCode'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (promoCodeInstance.version > version) {
                promoCodeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'promoCode.label', default: 'PromoCode')] as Object[],
                          "Another user has updated this PromoCode while you were editing")
                render(view: "edit", model: [promoCodeInstance: promoCodeInstance])
                return
            }
        }

        promoCodeInstance.properties = params

        if (!promoCodeInstance.save(flush: true)) {
            render(view: "edit", model: [promoCodeInstance: promoCodeInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'promoCode.label', default: 'PromoCode'), promoCodeInstance.id])
        redirect(action: "show", id: promoCodeInstance.id)
    }

    def delete() {
        def promoCodeInstance = PromoCode.get(params.id)
        if (!promoCodeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'promoCode.label', default: 'PromoCode'), params.id])
            redirect(action: "list")
            return
        }

        try {
            promoCodeInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'promoCode.label', default: 'PromoCode'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'promoCode.label', default: 'PromoCode'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
