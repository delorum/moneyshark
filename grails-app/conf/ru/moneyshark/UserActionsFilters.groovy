package ru.moneyshark

class UserActionsFilters {

	def messageSource
    def filters = {
        all(controller:'(user)', action:"(login|authenticate)", invert:true) {
            before = {
            	if(!session || !session.user) {
					flash.message = messageSource.getMessage('user.loginfirst', 
															 null, 
															 "Please, login first", 
															 session.'org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE')
					redirect(controller:"user", action:"login")
					return false;
				}    
            }
            /*after = {
                
            }
            afterView = {
                
            }*/
        }
    }
    
}
