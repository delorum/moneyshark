import ru.moneyshark.User

class BootStrap {

    def init = { servletContext ->
		environments {
			development {
				if(!User.findByEmail("dunnolol.b@gmail.com")) {
					def dunno = new User(email:"dunnolol.b@gmail.com", password:"qweasd123")
					dunno.save(); if(dunno.hasErrors()) {println dunno.errors}
				}
				if(!User.findByEmail("zack@brutalforce.ru")) {
					def dunno = new User(email:"zack@brutalforce.ru", password:"qweasd123")
					dunno.save(); if(dunno.hasErrors()) {println dunno.errors}
				}
			}			
			production {
				if(!User.findByEmail("dunnolol.b@gmail.com")) {
					def dunno = new User(email:"dunnolol.b@gmail.com", password:"qweasd123")
					dunno.save(); if(dunno.hasErrors()) {println dunno.errors}
				}
				if(!User.findByEmail("zack@brutalforce.ru")) {
					def dunno = new User(email:"zack@brutalforce.ru", password:"qweasd123")
					dunno.save(); if(dunno.hasErrors()) {println dunno.errors}
				}
			}
		}
    }
	
    def destroy = {
    }
}
