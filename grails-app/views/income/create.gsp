<%@ page import="ru.moneyshark.Income" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'income.label', default: 'Income')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
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
		<div id="create-income" class="content scaffold-create" role="main">
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${incomeInstance}">
	            <div class="errors">
	                <g:renderErrors bean="${incomeInstance}" as="list" />
	            </div>
            </g:hasErrors>
			<g:form action="save" >
				<div class="dialog">
                    <table>
                        <tbody>                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="amount"><g:message code="amount.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: incomeInstance, field: 'amount', 'errors')}">
                                    <g:textField name="amount" value="${incomeInstance?.amount}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="comment"><g:message code="comment.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: incomeInstance, field: 'comment', 'errors')}">
                                    <g:textArea name="comment" value="${incomeInstance?.comment}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="date"><g:message code="date.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: incomeInstance, field: 'date', 'errors')}">
                                    <g:datePicker name="date" precision="minute" value="${incomeInstance?.date}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="status"><g:message code="status.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: incomeInstance, field: 'status', 'errors')}">
                                    <g:select name="status" 
                                    		  from="${[
												  		[status_name:message(code:'status.accepted'), status_code:'accepted'], 
												  		[status_name:message(code:'status.waiting'),  status_code:'waiting']
												  	]}"
                                    		  value="${fieldValue(bean: incomeInstance, field: 'status')}"
                                    		  optionKey="status_code" 
                                    		  optionValue="status_name" 
                                    		  noSelection="${['':'-'+message(code:'status.choosestatus.label')+'-']}" />
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
