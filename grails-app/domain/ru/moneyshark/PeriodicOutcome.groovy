package ru.moneyshark

import java.util.Date;

class PeriodicOutcome {
	Integer amount
	Date startMoment
	Date stopMoment
	Long periodicity
	String periodUnit
	String comment
	User user
	Date lastAdded
	
	static constraints = {
		amount(blank:false)
		stopMoment(nullable:true)
		comment(nullable:true)
		lastAdded(nullable:true)
	}
	
	/*static mapping = {
		amount type: EncryptedInteger
		comment type: EncryptedString
		periodicity type: EncryptedLong
		periodUnit type: EncryptedString
	}*/
	
	static transients = ['periodicityString']
	
	String getPeriodicityString() {
		switch(periodUnit) {
		case 'hour':  return periodicity/(60*60*1000)+" "+periodUnit+"(s)"
		case 'day':   return periodicity/(24*60*60*1000)+" "+periodUnit+"(s)"
		case 'week':  return periodicity/(7*24*60*60*1000)+" "+periodUnit+"(s)"
		case 'month': return periodicity/(30*24*60*60*1000)+" "+periodUnit+"(s)"
		default: return ""
		}
	}
}
