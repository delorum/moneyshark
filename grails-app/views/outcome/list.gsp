
<%@ page import="ru.moneyshark.Outcome" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'outcome.label', default: 'Outcome')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-outcome" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
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
					
						<g:sortableColumn property="status" title="${message(code: 'outcome.status.label', default: 'Status')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${outcomeInstanceList}" status="i" var="outcomeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${outcomeInstance.id}">${fieldValue(bean: outcomeInstance, field: "amount")}</g:link></td>
					
						<td>${fieldValue(bean: outcomeInstance, field: "comment")}</td>
					
						<td><g:formatDate date="${outcomeInstance.date}" /></td>
					
						<td>${fieldValue(bean: outcomeInstance, field: "status")}</td>
					
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
