package ru.moneyshark

class AdTagLib {
	// footer tags
	def copyright = {attrs, body ->
		def start_year = attrs.startYear
		def this_year = new Date().format("yyyy").toString()
		
		if(start_year != this_year) {
			out << "&copy; " + attrs.startYear + " - " + this_year + ", " + body()
		}
		else {
			out << "&copy; " + attrs.startYear + ", " + body()
		}
	}
	
	def appversion = {
		out << "v${grailsApplication.metadata['app.version']}"
	}
	
	// header tags
	def loginControl = {
		if(request.getSession(false) && session.user){
			out << "${session.user.email} "
			out << """[${link(action:"logout", controller:"user"){message(code:'header.logout.message')}}] """
		}/* else {
			out << """[${link(action:"login", controller:"user"){"Login"}}]"""
		}*/
	}
	
	def langSwitch = { attr, body ->
		def lang = attr['lang']
		out << """<a href="${resource(dir:'')}?lang=${lang}">${lang}</a>"""
	}
	
	// grid max rows select
	def gridrows = {attr, body ->
		def controller_name = attr['controller']?:"default"
		def current_maxrows = request.getParameter("max")?:(session[controller_name+'_gridrows']?:10)
		session[controller_name+'_gridrows'] = current_maxrows
		def max_rows = attr['max'].split(",")		
		out << """
		<script type="text/javascript">
			function changeMaxRows() {
			  var new_maxrows = document.getElementById('maxrows_select').value;
			  window.location.replace('${request.forwardURI}?max='+new_maxrows);
			}
		</script>
		"""
		out << "<select id='maxrows_select' onchange='changeMaxRows()'>"
		def output = ""
		max_rows.each {
			if(!it.is(max_rows.last())) {
				output += """<option ${(it == current_maxrows)?"selected":""} value="${it}">${it}</option>"""
			}
			else output += """<option ${(it == current_maxrows)?"selected":""} value="${it}">Все</option>"""
		}
		out << output
		out << "</select>"
	}
	
	def balance = {attr, body ->
		def current_balance = Balance.findAllByUser(session.user, [sort:"id", order:"desc", max:1])?.find{it}?.balance?:0
		out << """[${link(action:"list", controller:"balance"){message(code:'balance.current.message', args:[current_balance])}}] """
	}
}
