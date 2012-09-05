<%@ page import="ru.moneyshark.Income" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'income.label', default: 'Income')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">

		</div>
		<div id="edit-income" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${incomeInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${incomeInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${incomeInstance?.id}" />
				<g:hiddenField name="version" value="${incomeInstance?.version}" />
				<div class="form">
					<table>
                        <tbody>
                        	<tr class="prop">
                                <td valign="top" class="name">
                                  <label for="amount"><g:message code="income.amount.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: incomeInstance, field: 'amount', 'errors')}">
                                    <g:textField name="amount" value="${incomeInstance?.amount}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="date"><g:message code="income.date.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: incomeInstance, field: 'date', 'errors')}">
                                    <g:datePicker name="date" precision="minute" value="${incomeInstance?.date}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="comment"><g:message code="income.comment.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: incomeInstance, field: 'comment', 'errors')}">
                                    <g:textArea name="comment" value="${incomeInstance?.comment}" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
				</div>
				<div class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</div>
			</g:form>
		</div>
	</body>
</html>
