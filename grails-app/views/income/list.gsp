
<%@ page import="ru.moneyshark.Income" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'income.label', default: 'Income')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			
		</div>
		<div id="list-income" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="amount" title="${message(code: 'income.amount.label', default: 'Amount')}" />
					
						<g:sortableColumn property="comment" title="${message(code: 'income.comment.label', default: 'Comment')}" />
					
						<g:sortableColumn property="date" title="${message(code: 'income.date.label', default: 'Date')}" />
						
						<th class="sortable">${message(code: 'default.deletion.label', default: 'Delete')}</th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${incomeInstanceList}" status="i" var="incomeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="edit" id="${incomeInstance.id}">${fieldValue(bean: incomeInstance, field: "amount")}</g:link></td>
					
						<td><g:link action="edit" id="${incomeInstance.id}">${fieldValue(bean: incomeInstance, field: "comment")}</g:link></td>
					
						<td><g:link action="edit" id="${incomeInstance.id}"><g:formatDate date="${incomeInstance.date}" /></g:link></td>
					
						<td>
	                        	<div align="center">
	                        		<span class="menuButton" onclick="return confirm('\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
		                        		<g:link class="delete" 
		                        				action="delete" 
		                        				id="${incomeInstance?.id}" />		                        		
	                        		</span>
                        		</div>
                        </td>
                        
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${incomeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
