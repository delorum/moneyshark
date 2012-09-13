
<%@ page import="ru.moneyshark.PeriodicOutcome" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'periodicOutcome.label', default: 'PeriodicOutcome')}" />
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
		<div id="list-periodicOutcome" class="content scaffold-list" role="main">
			<h1><g:message code="periodicoutcome.list.label" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="amount" title="${message(code: 'amount.label')}" />
					
						<g:sortableColumn property="comment" title="${message(code: 'comment.label')}" />
					
						<g:sortableColumn property="startMoment" title="${message(code: 'startmoment.label')}" />
					
						<g:sortableColumn property="stopMoment" title="${message(code: 'stopmoment.label')}" />
						
						<g:sortableColumn property="periodicity" title="${message(code: 'periodicity.label')}" />
						
						<th class="sortable">${message(code: 'default.deletion.label', default: 'Delete')}</th>						
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${periodicOutcomeInstanceList}" status="i" var="periodicOutcomeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="edit" id="${periodicOutcomeInstance.id}">${fieldValue(bean: periodicOutcomeInstance, field: "amount")}</g:link></td>
					
						<td><g:link action="edit" id="${periodicOutcomeInstance.id}">${fieldValue(bean: periodicOutcomeInstance, field: "comment")}</g:link></td>
					
						<td><g:link action="edit" id="${periodicOutcomeInstance.id}"><g:formatDate date="${periodicOutcomeInstance.startMoment}" /></g:link></td>
					
						<td><g:link action="edit" id="${periodicOutcomeInstance.id}"><g:formatDate date="${periodicOutcomeInstance.stopMoment}" /></g:link></td>
						
						<td><g:link action="edit" id="${periodicOutcomeInstance.id}">${fieldValue(bean: periodicOutcomeInstance, field: "periodicityString")}</g:link></td>
						
						<td>
	                        	<div>
	                        		<span class="menuButton" onclick="return confirm('\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
		                        		<g:link class="delete" 
		                        				action="delete"
		                        				title="Delete" 
		                        				id="${periodicOutcomeInstance?.id}" />		                        		
	                        		</span>
                        		</div>
                        </td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="paginateButtons">
				<g:paginate total="${periodicOutcomeInstanceTotal}" />
				<g:gridrows max="10,100,500,${periodicOutcomeInstanceTotal}" controller="periodicOutcome" />
			</div>
		</div>
	</body>
</html>
