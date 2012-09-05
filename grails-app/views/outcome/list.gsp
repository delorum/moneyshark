
<%@ page import="ru.moneyshark.Outcome" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'outcome.label', default: 'Outcome')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">

		</div>
		<div id="list-outcome" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="amount" title="${message(code: 'outcome.amount.label', default: 'Amount')}" />
					
						<g:sortableColumn property="comment" title="${message(code: 'outcome.comment.label', default: 'Comment')}" />
					
						<g:sortableColumn property="date" title="${message(code: 'outcome.date.label', default: 'Date')}" />
					
						<th class="sortable">${message(code: 'default.deletion.label', default: 'Delete')}</th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${outcomeInstanceList}" status="i" var="outcomeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="edit" id="${outcomeInstance.id}">${fieldValue(bean: outcomeInstance, field: "amount")}</g:link></td>
					
						<td><g:link action="edit" id="${outcomeInstance.id}">${fieldValue(bean: outcomeInstance, field: "comment")}</g:link></td>
					
						<td><g:link action="edit" id="${outcomeInstance.id}"><g:formatDate date="${outcomeInstance.date}" /></g:link></td>
					
						<td>
	                        	<div align="center">
	                        		<span class="menuButton" onclick="return confirm('\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
		                        		<g:link class="delete" 
		                        				action="delete" 
		                        				id="${outcomeInstance?.id}" />		                        		
	                        		</span>
                        		</div>
                        </td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${outcomeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
