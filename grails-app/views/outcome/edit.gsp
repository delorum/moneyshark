<%@ page import="ru.moneyshark.Outcome" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'outcome.label', default: 'Outcome')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
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
		<div id="edit-outcome" class="content scaffold-edit" role="main">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${outcomeInstance}">
	            <div class="errors">
	                <g:renderErrors bean="${outcomeInstance}" as="list" />
	            </div>
            </g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${outcomeInstance?.id}" />
				<g:hiddenField name="version" value="${outcomeInstance?.version}" />
				<fieldset class="form">
					<table>
                        <tbody>
                        	<tr class="prop">
                                <td valign="top" class="name">
                                  <label for="amount"><g:message code="income.amount.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: outcomeInstance, field: 'amount', 'errors')}">
                                    <g:textField name="amount" value="${outcomeInstance?.amount}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="comment"><g:message code="income.comment.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: outcomeInstance, field: 'comment', 'errors')}">
                                    <g:textArea name="comment" value="${outcomeInstance?.comment}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="date"><g:message code="income.date.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: outcomeInstance, field: 'date', 'errors')}">
                                    <g:datePicker name="date" precision="minute" value="${outcomeInstance?.date}" />
                                </td>
                            </tr>                            
                        </tbody>
                    </table>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
