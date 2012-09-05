<%@ page import="ru.moneyshark.Outcome" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'outcome.label', default: 'Outcome')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">

		</div>
		<div id="create-outcome" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${outcomeInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${outcomeInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save" >
				<div class="form">
					<table>
                        <tbody>                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="amount"><g:message code="balance.amount.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: incomeInstance, field: 'amount', 'errors')}">
                                    <g:textField name="amount" value="${incomeInstance?.amount}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="date"><g:message code="balance.date.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: incomeInstance, field: 'date', 'errors')}">
                                    <g:datePicker name="date" precision="minute" value="${incomeInstance?.date}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="comment"><g:message code="balance.comment.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: incomeInstance, field: 'comment', 'errors')}">
                                    <g:textArea name="comment" value="${incomeInstance?.comment}" />
                                </td>
                            </tr>
                        </tbody>
                	</table>
				</div>
				<div class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</div>
			</g:form>
		</div>
	</body>
</html>
