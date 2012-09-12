package ru.moneyshark

import org.springframework.dao.DataIntegrityViolationException
import cr.co.arquetipos.crypto.Blowfish

class UserController {
	static allowedMethods = [save: "POST", update: "POST", delete: "GET"]
	
	def login = {
		if(session?.user) redirect(controller:"balance", action:"list")
	}
	
	PeriodicService periodicService
	
	def authenticate = {
		def email = params.email
		def password = params.password
		def encoded_password = password.encodeAsSHA()
		def user = User.findByEmailAndPassword(email, encoded_password)
		if(user) {
			session.user = user
			session.key = password.encodeAsMD5()
			SessionKeysJob.put(session.user.id, session.key)
			periodicService.countMoney()
			
			flash.message = "${message(code:'user.hello.message', args:[user.email])}"
			redirect(controller:"balance", action:"list")
		} else {
			flash.message = "${message(code:'user.notfound.message', args:[params.email])}"
			redirect(action:"login")
		}
	}
	
	private void countMoney() {
		println("counting money...")
		
		def curDate = new Date()
		PeriodicIncome.list().each {
			def pew = it.lastAdded ? curDate.getTime() - it.lastAdded.getTime() : curDate.getTime() - it.startMoment.getTime()
			if(pew >= it.periodicity) {
				def incomeInstance = new Income()
				incomeInstance.amount = it.amount
				incomeInstance.comment = "Периодическое пополнение: "+it.comment
				incomeInstance.status = "waiting"
				incomeInstance.user = it.user
				incomeInstance.date = curDate
				incomeInstance.save(flush: true)
				
				it.lastAdded = curDate
				it.save(flush: true)
			}
		}
		PeriodicOutcome.list().each {
			def pew = it.lastAdded ? curDate.getTime() - it.lastAdded.getTime() : curDate.getTime() - it.startMoment.getTime()
			if(pew >= it.periodicity) {
				def outcomeInstance = new Outcome()
				outcomeInstance.amount = it.amount
				outcomeInstance.comment = "Периодическое списание: "+it.comment
				outcomeInstance.status = "waiting"
				outcomeInstance.user = it.user
				outcomeInstance.date = curDate
				outcomeInstance.save(flush: true)
				
				it.lastAdded = curDate
				it.save(flush: true)
			}
		}
		
		println("finished")
	}
	
	def logout = {
		flash.message = "${message(code:'user.goodbye.message', args:[session.user.email])}"
		session.user = null
		redirect(action:"login")
	}

	def index = {
		if(session?.user) redirect(controller:"balance", action:"list")
		else redirect(action: "login", params: params)
	}
	
	def update = {
		def userInstance = User.get(session.user.id)
		if (userInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (userInstance.version > version) {
					userInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'user.label', default: 'User')] as Object[], "Another user has updated this User while you were editing")
					render(view: "edit", model: [userInstance: userInstance])
					return
				}
			}
			
			if(params.password == "") {
				//params.remove('password')
				userInstance.errors.rejectValue('password', "${message(code:'user.error.passwordempty')}")
				render(view: "edit", model: [userInstance: userInstance])
				return
			}
			else if(params.password != params.password_again) {
				userInstance.errors.rejectValue('password', "${message(code:'user.error.passwordsmatch')}")
				render(view: "edit", model: [userInstance: userInstance])
				return
			}
						
			userInstance.properties = params
			if (!userInstance.hasErrors() && userInstance.save(flush: true)) {
				flash.message = "${message(code: 'user.updated.message', args: [userInstance.email])}"
				redirect(action: "edit")
			}
			else {
				render(view: "edit", model: [userInstance: userInstance])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
			redirect(action: "edit")
		}
	}

	/*def show = {
		def userInstance = User.get(params.id)
		if (!userInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
			redirect(action: "list")
		}
		else {
			[userInstance: userInstance]
		}
	}*/

	def edit = {
		def userInstance = session.user
		if (!userInstance) {
			flash.message = "${message(code: 'user.notfound.message')}"
			redirect(action: "list")
		}
		else {
			return [userInstance: userInstance]
		}
	}

	/*def delete = {
		def userInstance = User.get(params.id)
		if (userInstance) {
			if(session.user && session.user.id == userInstance.id) {
				flash.message = "${message(code:'user.warning.deleteself')}";
				redirect(action: "list")
			}
			else {
				try {
					userInstance.delete(flush: true)
					flash.message = "${message(code: 'user.deleted.message', args: [userInstance.email])}"
					redirect(action: "list")
				}
				catch (org.springframework.dao.DataIntegrityViolationException e) {
					flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
					redirect(action: "list")
				}
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
			redirect(action: "list")
		}
	}*/
}
