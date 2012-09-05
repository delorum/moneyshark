package ru.moneyshark

class Income {

	Integer amount
	String comment
	Date date
	String status
	User user
	
    static constraints = {
		amount(blank:false)
    }
}
