import ru.moneyshark.User

class BootStrap {

    def init = { servletContext ->
		environments {
			development {
				if(!User.findByEmail("dunnolol.b@gmail.com")) {
					def user = new User(email:"dunnolol.b@gmail.com", password:"qweasd123")
					user.save(); if(user.hasErrors()) {println user.errors}
				}
				if(!User.findByEmail("zack@brutalforce.ru")) {
					def user = new User(email:"zack@brutalforce.ru", password:"qweasd123")
					user.save(); if(user.hasErrors()) {println user.errors}
				}
			}			
			production {
				if(!User.findByEmail("dunnolol.b@gmail.com")) {
					def user = new User(email:"dunnolol.b@gmail.com", password:"qweasd123")
					user.save(); if(user.hasErrors()) {println user.errors}
				}
				if(!User.findByEmail("zack@brutalforce.ru")) {
					def user = new User(email:"zack@brutalforce.ru", password:"qweasd123")
					user.save(); if(user.hasErrors()) {println user.errors}
				}
			}
		}
    }
	
    def destroy = {
    }
}
