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
			
			def previous_user_key = SessionKeysJob.get(userInstance.id)
			def new_user_key      = params.password.encodeAsMD5()
			
			def balances          = Balance.findAllByUser(userInstance)
			def incomes           = Income.findAllByUser(userInstance)
			def outcomes          = Outcome.findAllByUser(userInstance)
			def periodic_incomes  = PeriodicIncome.findAllByUser(userInstance)
			def periodic_outcomes = PeriodicOutcome.findAllByUser(userInstance)
			
			session.key = params.password.encodeAsMD5()
			SessionKeysJob.put(userInstance.id, session.key)
			
			balances.each          {it.save(flush:true)}
			incomes.each           {it.save(flush:true)}
			outcomes.each          {it.save(flush:true)}
			periodic_incomes.each  {it.save(flush:true)}
			periodic_outcomes.each {it.save(flush:true)}
						
			userInstance.properties = params
			if (!userInstance.hasErrors() && userInstance.save(flush: true)) {
				session.user = userInstance
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
}
