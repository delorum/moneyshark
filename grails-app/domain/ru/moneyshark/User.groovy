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
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = password.encodeAsSHA()	
	}
}
