package ru.moneyshark

import java.util.Date;

class Outcome {

    TwoIntegers amount
	StringInteger comment
	Date date
	String status = "accepted"
	User user
	
    static constraints = {
		amount(blank:false)
		comment(nullable:true)
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
	}
}
