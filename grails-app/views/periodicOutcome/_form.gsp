<%@ page import="ru.moneyshark.PeriodicOutcome" %>



<div class="fieldcontain ${hasErrors(bean: periodicOutcomeInstance, field: 'amount', 'error')} required">
	<label for="amount">
		<g:message code="periodicOutcome.amount.label" default="Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="amount" required="" value="${periodicOutcomeInstance.amount}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: periodicOutcomeInstance, field: 'comment', 'error')} ">
	<label for="comment">
		<g:message code="periodicOutcome.comment.label" default="Comment" />
		
	</label>
	<g:textField name="comment" value="${periodicOutcomeInstance?.comment}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: periodicOutcomeInstance, field: 'periodicity', 'error')} required">
	<label for="periodicity">
		<g:message code="periodicOutcome.periodicity.label" default="Periodicity" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="periodicity" required="" value="${periodicOutcomeInstance.periodicity}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: periodicOutcomeInstance, field: 'startMoment', 'error')} required">
	<label for="startMoment">
		<g:message code="periodicOutcome.startMoment.label" default="Start Moment" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="startMoment" precision="day"  value="${periodicOutcomeInstance?.startMoment}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: periodicOutcomeInstance, field: 'stopMoment', 'error')} required">
	<label for="stopMoment">
		<g:message code="periodicOutcome.stopMoment.label" default="Stop Moment" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="stopMoment" precision="day"  value="${periodicOutcomeInstance?.stopMoment}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: periodicOutcomeInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="periodicOutcome.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${ru.moneyshark.User.list()}" optionKey="id" required="" value="${periodicOutcomeInstance?.user?.id}" class="many-to-one"/>
</div>

