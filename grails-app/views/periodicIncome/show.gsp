
<%@ page import="ru.moneyshark.PeriodicIncome" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'periodicIncome.label', default: 'PeriodicIncome')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-periodicIncome" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-periodicIncome" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list periodicIncome">
			
				<g:if test="${periodicIncomeInstance?.amount}">
				<li class="fieldcontain">
					<span id="amount-label" class="property-label"><g:message code="periodicIncome.amount.label" default="Amount" /></span>
					
						<span class="property-value" aria-labelledby="amount-label"><g:fieldValue bean="${periodicIncomeInstance}" field="amount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${periodicIncomeInstance?.comment}">
				<li class="fieldcontain">
					<span id="comment-label" class="property-label"><g:message code="periodicIncome.comment.label" default="Comment" /></span>
					
						<span class="property-value" aria-labelledby="comment-label"><g:fieldValue bean="${periodicIncomeInstance}" field="comment"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${periodicIncomeInstance?.periodicity}">
				<li class="fieldcontain">
					<span id="periodicity-label" class="property-label"><g:message code="periodicIncome.periodicity.label" default="Periodicity" /></span>
					
						<span class="property-value" aria-labelledby="periodicity-label"><g:fieldValue bean="${periodicIncomeInstance}" field="periodicity"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${periodicIncomeInstance?.startMoment}">
				<li class="fieldcontain">
					<span id="startMoment-label" class="property-label"><g:message code="periodicIncome.startMoment.label" default="Start Moment" /></span>
					
						<span class="property-value" aria-labelledby="startMoment-label"><g:formatDate date="${periodicIncomeInstance?.startMoment}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${periodicIncomeInstance?.stopMoment}">
				<li class="fieldcontain">
					<span id="stopMoment-label" class="property-label"><g:message code="periodicIncome.stopMoment.label" default="Stop Moment" /></span>
					
						<span class="property-value" aria-labelledby="stopMoment-label"><g:formatDate date="${periodicIncomeInstance?.stopMoment}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${periodicIncomeInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="periodicIncome.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${periodicIncomeInstance?.user?.id}">${periodicIncomeInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${periodicIncomeInstance?.id}" />
					<g:link class="edit" action="edit" id="${periodicIncomeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
