<%@ page import="ru.moneyshark.User" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title>Login</title>
    </head>
    <body>
        <div class="body">
            <h1>${message(code:'user.login.title')}</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>            
            <g:form action="authenticate" method="post" >
				<div class="dialog">
					<table>
						<tbody>
							<tr class="prop">
								<td valign="top" class="name">
									<label for="email">${message(code:'user.email.label')}</label>
								</td>
								<td valign="top">
									<input type="text" id="email" name="email"/>
								</td>
							</tr>
							<tr class="prop">
								<td valign="top" class="name">
									<label for="password">${message(code:'user.password.label')}</label>
								</td>
								<td valign="top">
									<input type="password" id="password" name="password"/>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="buttons">
					<span class="button">
						<input type="submit" value="${message(code:'user.enter.label')}" />
					</span>
				</div>
			</g:form>
			
			<h1>${message(code:'user.register.title')}</h1>
			<g:hasErrors bean="${userInstance}">
	            <div class="errors">
	                <g:renderErrors bean="${userInstance}" as="list" />
	            </div>
            </g:hasErrors>
			<g:form action="register" method="post" >
				<div class="dialog">
					<table>
						<tbody>
							<tr class="prop">
								<td valign="top" class="name">
									<label for="email">${message(code:'user.email.label')}</label>
								</td>
								<td valign="top">
									<g:textField name="email" value="${userInstance.email}" />
								</td>
							</tr>
							<tr class="prop">
								<td valign="top" class="name">
									<label for="password">${message(code:'user.password.label')}</label>
								</td>
								<td valign="top" class="value ${hasErrors(bean: userInstance, field: 'password', 'errors')}">
									<g:passwordField name="password" value="" />
								</td>
							</tr>
							<tr class="prop">
                                <td valign="top" class="name">
                                  <label for="password.again"><g:message code="user.passwordagain.label" default="Password Again" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'password', 'errors')}">
                                    <g:passwordField name="password_again" value="" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="promocode"><g:message code="user.promocode.label"  /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'invitedBy', 'errors')}">
                                    <g:textField name="promocode" value="" />
                                </td>
                            </tr>							
						</tbody>
					</table>
				</div>
				<div class="buttons">
					<span class="button">
						<input type="submit" value="${message(code:'user.create.label')}" />
					</span>
				</div>
			</g:form>
        </div>
    </body>
</html>
