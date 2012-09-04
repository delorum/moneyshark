package ru.moneyshark

class Balance {
	
	Integer balance
	Date date
	String comment
	
    static constraints = {
		balance(blank:false)
    }
	
	static hasMany = [user:User]
}
