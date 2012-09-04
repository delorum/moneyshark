package ru.moneyshark

import org.springframework.dao.DataIntegrityViolationException

class UserController {
	static allowedMethods = [save: "POST", update: "POST", delete: "GET"]
	
	def login = {
		if(session?.user) redirect(controller:"balance", action:"list")
	}
	def authenticate = {
		def email = params.email
		def password = params.password
		def encoded_password = password.encodeAsSHA()
		def user = User.findByEmailAndPassword(email, encoded_password)
		if(user) {
			session.user = user
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
		redirect(action: "list", params: params)
	}

	def list = {
		params.max = params.max ? params.int('max') : (session.setting_gridrows?:10)
		[userInstanceList: User.list(params), userInstanceTotal: User.count()]
	}

	def create = {
		def userInstance = new User()
		userInstance.properties = params
		return [userInstance: userInstance]
	}
	
	def save = {
		def userInstance = new User(params)
		
		if(userInstance.password != params.password_again) {
			userInstance.errors.rejectValue('password', "${message(code:'user.error.passwordsmatch')}")
			render(view: "create", model: [userInstance: userInstance])
			return
		}
		else if(userInstance.password != "") userInstance.password = userInstance.password.encodeAsSHA()
				
		if (!userInstance.hasErrors() && userInstance.save(flush: true)) {
			flash.message = "${message(code: 'user.created.message', args: [userInstance.email])}"
			redirect(action: "list")
		}
		else {
			userInstance.password = ""
			render(view: "create", model: [userInstance: userInstance])
		}
	}
	
	def update = {
		def userInstance = User.get(params.id)
		if (userInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (userInstance.version > version) {
					userInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'user.label', default: 'User')] as Object[], "Another user has updated this User while you were editing")
					render(view: "edit", model: [userInstance: userInstance])
					return
				}
			}
			
			if(params.password == "") params.remove('password')
			else if(params.password != params.password_again) {
				userInstance.errors.rejectValue('password', "${message(code:'user.error.passwordsmatch')}")
				render(view: "edit", model: [userInstance: userInstance])
				return
			}
			else params.password = params.password.encodeAsSHA()
						
			userInstance.properties = params
			if (!userInstance.hasErrors() && userInstance.save(flush: true)) {
				flash.message = "${message(code: 'user.updated.message', args: [userInstance.email])}"
				redirect(action: "list")
			}
			else {
				render(view: "edit", model: [userInstance: userInstance])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
			redirect(action: "list")
		}
	}

	def show = {
		def userInstance = User.get(params.id)
		if (!userInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
			redirect(action: "list")
		}
		else {
			[userInstance: userInstance]
		}
	}

	def edit = {
		def userInstance = User.get(params.id)
		if (!userInstance) {
			flash.message = "${message(code: 'user.notfound.message')}"
			redirect(action: "list")
		}
		else {
			return [userInstance: userInstance]
		}
	}

	def delete = {
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
	}
}
