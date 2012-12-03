<%@ page import="ru.moneyshark.PeriodicOutcome" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'periodicOutcome.label', default: 'PeriodicOutcome')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">

		</div>
		<div id="create-periodicOutcome" class="content scaffold-create" role="main">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${periodicOutcomeInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${periodicOutcomeInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save" >
				<table>
                        <tbody>                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="amount"><g:message code="amount.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: periodicOutcomeInstance, field: 'amount', 'errors')}">
                                    <g:textField name="amount" value="${periodicOutcomeInstance?.amount}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="comment"><g:message code="comment.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: periodicOutcomeInstance, field: 'comment', 'errors')}">
                                    <g:textArea name="comment" value="${periodicOutcomeInstance?.comment}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="startMoment"><g:message code="startmoment.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: periodicOutcomeInstance, field: 'startMoment', 'errors')}">
                                    <g:datePicker precision="minute" name="startMoment" value="${periodicOutcomeInstance?.startMoment}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="periodicity"><g:message code="periodicity.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: periodicOutcomeInstance, field: 'periodicity', 'errors')}">
                                	<label for="periodicity"><g:message code="interval.every.label" /></label>
                                    <g:textField name="periodamount" value="" />
                                    <g:select name="periodunit" 
                                    		  from="${[
                                    		  			[period_name:message(code:'interval.everyminute.label'), period_val:'minute'],
                                    		  			[period_name:message(code:'interval.everyhour.label'), period_val:'hour'],
                                    		  			[period_name:message(code:'interval.everyday.label'),  period_val:'day'],
                                    		  			[period_name:message(code:'interval.everyweek.label'), period_val:'week'],
												  		[period_name:message(code:'interval.everymonth.label'), period_val:'month']
                                    		  		  ]}" 
                                    		  value=""
                                    		  optionKey="period_val" 
                                    		  optionValue="period_name" 
                                    		  noSelection="${['':'-'+message(code:'interval.chooseperiodicity.label')+'-']}"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
