package ru.moneyshark

class Income {

	TwoIntegers amount
	StringInteger comment
	Date date
	String status		// maybe encrypt status too
	User user
	
    static constraints = {
		amount(blank:false)
		comment(nullable:true)
		status(blank:false)
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
