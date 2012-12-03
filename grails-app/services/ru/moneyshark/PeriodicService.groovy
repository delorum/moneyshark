package ru.moneyshark

class PeriodicService {
    def countMoney() {		
		def curDate = new Date()
		PeriodicIncome.where {
			stopMoment == null	
		}.each {
			def pew = it.lastAdded ? curDate.getTime() - it.lastAdded.getTime() : curDate.getTime() - it.startMoment.getTime()
			if(pew >= it.periodicity.l && SessionKeysJob.contains(it.user.id as Integer)) {
				def tms = pew/it.periodicity.l as Integer
				while(tms-- > 0) {
					def incomeInstance = new Income(
						amount: new TwoIntegers(int1:it.amount.int1, int2:it.user.id),
						comment: new StringInteger(s:"Периодическое пополнение: "+it.comment.s, i:it.user.id),
						status: "waiting",
						user: it.user,
						date: curDate
					)
					
					incomeInstance.save(flush: true)
					//println("new income: "+incomeInstance)
					it.lastAdded = curDate
					it.save(flush: true)
				}
			}
		}
		PeriodicOutcome.where {
			stopMoment == null	
		}.each {
			def pew = it.lastAdded ? curDate.getTime() - it.lastAdded.getTime() : curDate.getTime() - it.startMoment.getTime()
			if(pew >= it.periodicity.l && SessionKeysJob.contains(it.user.id)) {
				def tms = pew/it.periodicity.l as Integer
				while(tms-- > 0) {
					def outcomeInstance = new Outcome(
						amount: new TwoIntegers(int1:it.amount.int1, int2:it.user.id),
						comment: new StringInteger(s:"Периодическое списание: "+it.comment.s, i:it.user.id),
						status: "waiting",
						user: it.user,
						date: curDate
					)
					outcomeInstance.save(flush: true)
					//println("new outcome: "+outcomeInstance)
					it.lastAdded = curDate
					it.save(flush: true)
				}
			}
		}
    }
}
