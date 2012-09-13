<%@ page import="ru.moneyshark.PeriodicIncome" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'periodicIncome.label', default: 'PeriodicIncome')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			
		</div>
		<div id="edit-periodicIncome" class="content scaffold-edit" role="main">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${periodicIncomeInstance}">
	            <div class="errors">
	                <g:renderErrors bean="${periodicIncomeInstance}" as="list" />
	            </div>
            </g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${periodicIncomeInstance?.id}" />
				<g:hiddenField name="version" value="${periodicIncomeInstance?.version}" />
				<div class="form">
					<table>
	                        <tbody>
	                        	<tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="amount"><g:message code="amount.label" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: periodicIncomeInstance, field: 'amount', 'errors')}">
	                                    <g:textField name="amount" value="${periodicIncomeInstance?.amount}" />
	                                </td>
	                            </tr>
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="comment"><g:message code="comment.label" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: periodicIncomeInstance, field: 'comment', 'errors')}">
	                                    <g:textArea name="comment" value="${periodicIncomeInstance?.comment}" />
	                                </td>
	                            </tr>
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="startMoment"><g:message code="startmoment.label" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: periodicIncomeInstance, field: 'startMoment', 'errors')}">
	                                    <g:datePicker precision="minute" name="startMoment" value="${periodicIncomeInstance?.startMoment}" />
	                                </td>
	                            </tr>
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="periodicity"><g:message code="periodicity.label" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: periodicIncomeInstance, field: 'periodicity', 'errors')}">
	                                	<label for="periodicity"><g:message code="interval.every.label" /></label>
	                                    <g:textField name="periodamount" value="${periodAmount}" />
	                                    <g:select name="periodunit" 
	                                    		  from="${[
	                                    		  			[period_name:message(code:'interval.everyhour.label'), period_val:'hour'],
	                                    		  			[period_name:message(code:'interval.everyday.label'),  period_val:'day'],
	                                    		  			[period_name:message(code:'interval.everyweek.label'), period_val:'week'],
													  		[period_name:message(code:'interval.everymonth.label'), period_val:'month']
	                                    		  		  ]}" 
	                                    		  value="${periodicIncomeInstance?.periodUnit.s}"
	                                    		  optionKey="period_val" 
	                                    		  optionValue="period_name" 
	                                    		  noSelection="${['':'-'+message(code:'interval.chooseperiodicity.label')+'-']}"/>
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
