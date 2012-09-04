package ru.moneyshark

class Income {

	Integer amount
	String comment
	Date date
	String status
	
    static constraints = {
		amount(blank:false)
    }
	
	static hasMany = [user:User]
}
