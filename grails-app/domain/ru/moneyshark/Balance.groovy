package ru.moneyshark

import cr.co.arquetipos.crypto.Blowfish
import org.springframework.web.context.request.RequestContextHolder

class Balance {
	
	TwoIntegers balance
	Date date
	StringInteger comment
	User user
	
    static constraints = {
		balance(blank:false)
    }
	
	static mapping = {
		balance type: EncryptedInteger, {
			column name: "balance"
			column name: "pewpew1"	
		}
		comment type: EncryptedString, {
			column name: "comment"
			column name: "pewpew2"
		}
	}
	
	String toString() {
		balance+" "+comment
	}
}
