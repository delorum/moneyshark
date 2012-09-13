package ru.moneyshark

class User {
    String email
	String password
	
	static constraints = {
		email(blank:false, unique:true, email:true)
		password(blank:false, password:true)	// TODO: make the validator: length >= 6	
	}
	
	String toString() {
		email
	}
	
	def beforeInsert() {
		password = password.encodeAsSHA()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			password = password.encodeAsSHA()
		}
	}
}
