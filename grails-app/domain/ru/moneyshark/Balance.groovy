package ru.moneyshark

import cr.co.arquetipos.crypto.Blowfish
import org.springframework.web.context.request.RequestContextHolder

class Balance {
	
	String balance
	Date date
	String comment
	User user
	
    static constraints = {
		balance(blank:false)
    }
	
	/*static transients = ['decryptedBalance', 'decryptedComment']
	
	def beforeInsert() {
		def session = RequestContextHolder.currentRequestAttributes().getSession()
		balance = Blowfish.encryptBase64(balance, session.key)
		comment = Blowfish.encryptBase64(comment, session.key)
	}

	def beforeUpdate() {
		if (isDirty('balance') || isDirty('comment')) {
			def session = RequestContextHolder.currentRequestAttributes().getSession()
			balance = Blowfish.encryptBase64(balance, session.key)
			comment = Blowfish.encryptBase64(comment, session.key)
		}
	}
	
	Integer getDecryptedBalance() {
		def session = RequestContextHolder.currentRequestAttributes().getSession()
		Blowfish.decryptBase64(balance, session.key) as Integer
	}
	
	String getDecryptedComment() {
		def session = RequestContextHolder.currentRequestAttributes().getSession()
		Blowfish.decryptBase64(comment, session.key)
	}*/
	
	/*def afterLoad() {
		println("after load!")
		def session = RequestContextHolder.currentRequestAttributes().getSession()
		balance = Blowfish.decryptBase64(balance, session.key)
		comment = Blowfish.decryptBase64(comment, session.key)
	}*/
	
	String toString() {
		balance+" "+comment
	}
}
