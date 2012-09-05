package ru.moneyshark

class Balance {
	
	Integer balance
	Date date
	String comment
	User user
	
    static constraints = {
		balance(blank:false)
    }
}
