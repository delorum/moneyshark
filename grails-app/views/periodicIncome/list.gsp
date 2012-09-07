
<%@ page import="ru.moneyshark.PeriodicIncome" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'periodicincome.label')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<span class="menuButton">
		   		<g:link class="create" controller="income" action="create">
		   			<g:message code="income.new.label" />
		   		</g:link>
		   	</span>
		   	<span class="menuButton">
		   		<g:link class="create" controller="periodicIncome" action="create">
		   			<g:message code="periodicincome.new.label" />
		   		</g:link>
		   	</span>
			<span class="menuButton">
		   		<g:link class="create" controller="outcome" action="create">
		   			<g:message code="outcome.new.label" />
		   		</g:link>
		   	</span>
		   	<span class="menuButton">
		   		<g:link class="create" controller="periodicOutcome" action="create">
		   			<g:message code="periodicoutcome.new.label" />
		   		</g:link>
		   	</span>
		</div>
		<div id="list-periodicIncome" class="content scaffold-list" role="main">
			<h1><g:message code="periodicincome.list.label" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="amount" title="${message(code: 'periodicincome.amount.label')}" />
					
						<g:sortableColumn property="comment" title="${message(code: 'periodicincome.comment.label')}" />
					
						<g:sortableColumn property="startMoment" title="${message(code: 'periodicincome.startmoment.label')}" />
					
						<g:sortableColumn property="stopMoment" title="${message(code: 'periodicincome.stopmoment.label')}" />
						
						<g:sortableColumn property="periodicity" title="${message(code: 'periodicincome.periodicity.label')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${periodicIncomeInstanceList}" status="i" var="periodicIncomeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="edit" id="${periodicIncomeInstance.id}">${fieldValue(bean: periodicIncomeInstance, field: "amount")}</g:link></td>
					
						<td><g:link action="edit" id="${periodicIncomeInstance.id}">${fieldValue(bean: periodicIncomeInstance, field: "comment")}</g:link></td>
					
						<td><g:link action="edit" id="${periodicIncomeInstance.id}"><g:formatDate date="${periodicIncomeInstance.startMoment}" /></g:link></td>
					
						<td><g:link action="edit" id="${periodicIncomeInstance.id}"><g:formatDate date="${periodicIncomeInstance.stopMoment}" /></g:link></td>
						
						<td><g:link action="edit" id="${periodicIncomeInstance.id}">${fieldValue(bean: periodicIncomeInstance, field: "periodicityString")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="paginateButtons">
				<g:paginate total="${periodicIncomeInstanceTotal}" />
				<g:gridrows max="10,100,500,${periodicIncomeInstanceTotal}" controller="periodicIncome" />
			</div>
		</div>
	</body>
</html>
