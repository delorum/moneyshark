
<%@ page import="ru.moneyshark.Balance" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'balance.label', default: 'Balance')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-balance" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-balance" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="balance" title="${message(code: 'balance.balance.label', default: 'Balance')}" />
					
						<g:sortableColumn property="comment" title="${message(code: 'balance.comment.label', default: 'Comment')}" />
					
						<g:sortableColumn property="date" title="${message(code: 'balance.date.label', default: 'Date')}" />
					
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
			</div>
		</div>
	</body>
</html>
