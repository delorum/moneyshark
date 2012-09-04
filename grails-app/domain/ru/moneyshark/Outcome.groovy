package ru.moneyshark

import java.util.Date;

class Outcome {

    Integer amount
	String comment
	Date date
	String status
	
    static constraints = {
		amount(blank:false)
    }
	
	static hasMany = [user:User]
}
