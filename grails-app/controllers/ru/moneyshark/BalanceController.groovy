package ru.moneyshark

import org.springframework.dao.DataIntegrityViolationException
import static cr.co.arquetipos.crypto.Blowfish.*

class BalanceController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		def current_balance = 
			Balance.findAllByUser(session.user, [sort:"id", order:"desc", max:1])?.find{it}?.balance?.int1?:0
		def balanceInstanceList = Balance.findAllByUser(session.user, params)
		def balanceInstanceTotal = balanceInstanceList.size()
		
		def balancesPlotData = []
		balanceInstanceList.each {
			balancesPlotData.push([it.date.getTime(), it.balance.int1])
		}
		def maxDate = Balance.findAllByUser(session.user, [sort:"id", order:"desc", max:1])?.find{it}?.date?.getTime()?:new Date().getTime()
		def minDate = Balance.findAllByUser(session.user, [sort:"id", order:"asc", max:1])?.find{it}?.date?.getTime()?:new Date().getTime()
		
		def awaitingIncomes = Income.findAllByUserAndStatus(session.user, "waiting", [sort:"id", order:"desc", max:5])
		def awaitingOutcomes = Outcome.findAllByUserAndStatus(session.user, "waiting", [sort:"id", order:"desc", max:5])
        [
			currentBalance:current_balance, 
			balanceInstanceList: balanceInstanceList, 
			balanceInstanceTotal: balanceInstanceTotal,
			
			balancesPlotData: balancesPlotData.sort {t1, t2 -> t2[0] - t1[0]},
			maxDate: maxDate,
			minDate: minDate,
			
			awaitingIncomes: awaitingIncomes,
			awaitingOutcomes: awaitingOutcomes
		]
    }
	
	PeriodicService periodicService
	
	def countPeriodics = {
		periodicService.countMoney()
		flash.message = "Пересчет произведен"
		redirect(action: "list")
	}
}
