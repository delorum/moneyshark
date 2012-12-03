package ru.moneyshark

import java.util.Date;

class PeriodicOutcome {
	TwoIntegers amount
	Date startMoment
	Date stopMoment
	LongInteger periodicity
	StringInteger periodUnit
	StringInteger comment
	User user
	Date lastAdded
	
	static constraints = {
		amount(blank:false)
		stopMoment(nullable:true)
		comment(nullable:true)
		lastAdded(nullable:true)
	}
	
	static mapping = {
		amount type: EncryptedInteger, {
			column name: "amount"
			column name: "pewpew1"	
		}
		comment type: EncryptedString, {
			column name: "comment"
			column name: "pewpew2"	
		}
		periodicity type: EncryptedLong, {
			column name: "periodicity"
			column name: "pewpew3"	
		}
		periodUnit type: EncryptedString, {
			column name: "periodunit"
			column name: "pewpew4"	
		}
	}
	
	static transients = ['periodicityString']
	
	String getPeriodicityString() {
		switch(periodUnit.s) {			
			case 'minute': return periodicity.l/(60*1000)+" "+periodUnit.s+"(s)"
			case 'hour':   return periodicity.l/(60*60*1000)+" "+periodUnit.s+"(s)"
			case 'day':    return periodicity.l/(24*60*60*1000)+" "+periodUnit.s+"(s)"
			case 'week':   return periodicity.l/(7*24*60*60*1000)+" "+periodUnit.s+"(s)"
			case 'month':  return periodicity.l/(30*24*60*60*1000)+" "+periodUnit.s+"(s)"
			default:       return ""
		}
	}
}
