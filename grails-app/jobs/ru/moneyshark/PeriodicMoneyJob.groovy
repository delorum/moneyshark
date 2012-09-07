package ru.moneyshark



class PeriodicMoneyJob {
    static triggers = {
      simple repeatInterval: 30*60*1000L // execute job every 30min
    }

    def execute() {
        println("counting money...")
		
		def curDate = new Date()
		PeriodicIncome.list().each {
			def pew = it.lastAdded ? curDate.getTime() - it.lastAdded.getTime() : curDate.getTime() - it.startMoment.getTime()
			if(pew >= it.periodicity) {
				def incomeInstance = new Income()
				incomeInstance.amount = it.amount
				incomeInstance.comment = "Периодическое пополнение: "+it.comment
				incomeInstance.status = "waiting"
				incomeInstance.user = it.user
				incomeInstance.date = curDate
				incomeInstance.save(flush: true)
				
				it.lastAdded = curDate
				it.save(flush: true)				
			}					
		}
		PeriodicOutcome.list().each {
			def pew = it.lastAdded ? curDate.getTime() - it.lastAdded.getTime() : curDate.getTime() - it.startMoment.getTime()
			if(pew >= it.periodicity) {
				def outcomeInstance = new Outcome()
				outcomeInstance.amount = it.amount
				outcomeInstance.comment = "Периодическое списание: "+it.comment
				outcomeInstance.status = "waiting"
				outcomeInstance.user = it.user
				outcomeInstance.date = curDate
				outcomeInstance.save(flush: true)
				
				it.lastAdded = curDate
				it.save(flush: true)
			}
		}
		
		println("finished")
    }
}
