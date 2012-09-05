package ru.moneyshark

import java.util.Date;

class Outcome {

    Integer amount
	String comment
	Date date
	String status = "accepted"
	User user
	
    static constraints = {
		amount(blank:false)
    }
	
	static mapping = {
		amount type: EncryptedInteger
		comment type: EncryptedString
	}
}
