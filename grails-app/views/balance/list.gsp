
<%@ page import="ru.moneyshark.Balance" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'balance.label', default: 'Balance')}" />
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
		   		<g:link class="create" controller="outcome" action="create">
		   			<g:message code="outcome.new.label" />
		   		</g:link>
		   	</span>
		</div>
		<div id="list-balance" class="content scaffold-list" role="main">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr class="prop">
						<td valign="top" class="name">
							Текущий баланс:	
						</td>
						<td valign="top" class="name">
							${currentBalance}	
						</td>		
					</tr>
				</thead>
			</table>
			<g:if test="${awaitingIncomes.size() > 0}">
				<table>
					<thead>
						<tr>
						
							<g:sortableColumn property="amount" title="${message(code: 'income.amount.label')}" />
						
							<g:sortableColumn property="comment" title="${message(code: 'income.comment.label')}" />
						
							<g:sortableColumn property="date" title="${message(code: 'income.date.label')}" />
							
							<th class="sortable">${message(code: 'default.deletion.label', default: 'Delete')}</th>
						
						</tr>
					</thead>
					<tbody>
					<g:each in="${awaitingIncomes}" status="i" var="incomeInstance">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						
							<td><g:link controller="income" action="edit" id="${incomeInstance.id}">${fieldValue(bean: incomeInstance, field: "amount")}</g:link></td>
						
							<td><g:link controller="income" action="edit" id="${incomeInstance.id}">${fieldValue(bean: incomeInstance, field: "comment")}</g:link></td>
						
							<td><g:link controller="income" action="edit" id="${incomeInstance.id}"><g:formatDate date="${incomeInstance.date}" /></g:link></td>
						
							<td>
		                        	<div align="center">
		                        		<span class="menuButton" onclick="return confirm('\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
			                        		<g:link class="delete" 
			                        				controller="income"
			                        				action="delete" 
			                        				id="${incomeInstance?.id}" />		                        		
		                        		</span>
	                        		</div>
	                        </td>
	                        
						</tr>
					</g:each>
					</tbody>
				</table>
			</g:if>
			<h1><g:message code="balance.history.label" /></h1>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="balance" title="${message(code: 'balance.balance.label')}" />
					
						<g:sortableColumn property="comment" title="${message(code: 'balance.comment.label')}" />
					
						<g:sortableColumn property="date" title="${message(code: 'balance.date.label')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${balanceInstanceList}" status="i" var="balanceInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${balanceInstance.id}">${fieldValue(bean: balanceInstance, field: "balance")}</g:link></td>
					
						<td>${fieldValue(bean: balanceInstance, field: "comment")}</td>
					
						<td><g:formatDate date="${balanceInstance.date}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${balanceInstanceTotal}" />
				<g:gridrows max="10,100,500,${balanceInstanceTotal}" controller="balance" />
			</div>
		</div>
	</body>
</html>
