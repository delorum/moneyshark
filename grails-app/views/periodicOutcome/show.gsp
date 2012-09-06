
<%@ page import="ru.moneyshark.PeriodicOutcome" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'periodicOutcome.label', default: 'PeriodicOutcome')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-periodicOutcome" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-periodicOutcome" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list periodicOutcome">
			
				<g:if test="${periodicOutcomeInstance?.amount}">
				<li class="fieldcontain">
					<span id="amount-label" class="property-label"><g:message code="periodicOutcome.amount.label" default="Amount" /></span>
					
						<span class="property-value" aria-labelledby="amount-label"><g:fieldValue bean="${periodicOutcomeInstance}" field="amount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${periodicOutcomeInstance?.comment}">
				<li class="fieldcontain">
					<span id="comment-label" class="property-label"><g:message code="periodicOutcome.comment.label" default="Comment" /></span>
					
						<span class="property-value" aria-labelledby="comment-label"><g:fieldValue bean="${periodicOutcomeInstance}" field="comment"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${periodicOutcomeInstance?.periodicity}">
				<li class="fieldcontain">
					<span id="periodicity-label" class="property-label"><g:message code="periodicOutcome.periodicity.label" default="Periodicity" /></span>
					
						<span class="property-value" aria-labelledby="periodicity-label"><g:fieldValue bean="${periodicOutcomeInstance}" field="periodicity"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${periodicOutcomeInstance?.startMoment}">
				<li class="fieldcontain">
					<span id="startMoment-label" class="property-label"><g:message code="periodicOutcome.startMoment.label" default="Start Moment" /></span>
					
						<span class="property-value" aria-labelledby="startMoment-label"><g:formatDate date="${periodicOutcomeInstance?.startMoment}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${periodicOutcomeInstance?.stopMoment}">
				<li class="fieldcontain">
					<span id="stopMoment-label" class="property-label"><g:message code="periodicOutcome.stopMoment.label" default="Stop Moment" /></span>
					
						<span class="property-value" aria-labelledby="stopMoment-label"><g:formatDate date="${periodicOutcomeInstance?.stopMoment}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${periodicOutcomeInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="periodicOutcome.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${periodicOutcomeInstance?.user?.id}">${periodicOutcomeInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${periodicOutcomeInstance?.id}" />
					<g:link class="edit" action="edit" id="${periodicOutcomeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
