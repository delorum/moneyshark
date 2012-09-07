package ru.moneyshark

import org.springframework.dao.DataIntegrityViolationException

class BalanceController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		def current_balance = Balance.findAllByUser(session.user, [sort:"id", order:"desc", max:1])?.find{it}?.balance?:0
		def balanceInstanceList = Balance.findAllByUser(session.user, params)
		def balanceInstanceTotal = balanceInstanceList.size()
		
		def balancesPlotData = []
		balanceInstanceList.each {
			balancesPlotData.push([it.date.getTime(), it.balance])
		}
		def maxDate = Balance.findAllByUser(session.user, [sort:"id", order:"desc", max:1])?.find{it}?.date.getTime()?:new Date().getTime()
		def minDate = Balance.findAllByUser(session.user, [sort:"id", order:"asc", max:1])?.find{it}?.date.getTime()?:new Date().getTime()
		
		def awaitingIncomes = Income.findAllByUserAndStatus(session.user, "waiting", [sort:"id", order:"desc", max:5])
		def awaitingOutcomes = Outcome.findAllByUserAndStatus(session.user, "waiting", [sort:"id", order:"desc", max:5])
        [
			currentBalance:current_balance, 
			balanceInstanceList: balanceInstanceList, 
			balanceInstanceTotal: balanceInstanceTotal,
			
			balancesPlotData: balancesPlotData,
			maxDate: maxDate,
			minDate: minDate,
			
			awaitingIncomes: awaitingIncomes,
			awaitingOutcomes: awaitingOutcomes
		]
    }

    /*def create() {
        [balanceInstance: new Balance(params)]
    }

    def save() {
        def balanceInstance = new Balance(params)
        if (!balanceInstance.save(flush: true)) {
            render(view: "create", model: [balanceInstance: balanceInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'balance.label', default: 'Balance'), balanceInstance.id])
        redirect(action: "show", id: balanceInstance.id)
    }

    def show() {
        def balanceInstance = Balance.get(params.id)
        if (!balanceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'balance.label', default: 'Balance'), params.id])
            redirect(action: "list")
            return
        }

        [balanceInstance: balanceInstance]
    }

    def edit() {
        def balanceInstance = Balance.get(params.id)
        if (!balanceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'balance.label', default: 'Balance'), params.id])
            redirect(action: "list")
            return
        }

        [balanceInstance: balanceInstance]
    }

    def update() {
        def balanceInstance = Balance.get(params.id)
        if (!balanceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'balance.label', default: 'Balance'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (balanceInstance.version > version) {
                balanceInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'balance.label', default: 'Balance')] as Object[],
                          "Another user has updated this Balance while you were editing")
                render(view: "edit", model: [balanceInstance: balanceInstance])
                return
            }
        }

        balanceInstance.properties = params

        if (!balanceInstance.save(flush: true)) {
            render(view: "edit", model: [balanceInstance: balanceInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'balance.label', default: 'Balance'), balanceInstance.id])
        redirect(action: "show", id: balanceInstance.id)
    }

    def delete() {
        def balanceInstance = Balance.get(params.id)
        if (!balanceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'balance.label', default: 'Balance'), params.id])
            redirect(action: "list")
            return
        }

        try {
            balanceInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'balance.label', default: 'Balance'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'balance.label', default: 'Balance'), params.id])
            redirect(action: "show", id: params.id)
        }
    }*/
}
