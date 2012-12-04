package ru.moneyshark

class PromoCode {
	String promocode
	User   generatedBy
	User   usedFor
	
	
    static constraints = {
		promocode blank:false, unique:true
		usedFor   nullable:true
    }
}
