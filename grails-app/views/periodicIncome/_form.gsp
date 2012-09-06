<%@ page import="ru.moneyshark.PeriodicIncome" %>



<div class="fieldcontain ${hasErrors(bean: periodicIncomeInstance, field: 'amount', 'error')} required">
	<label for="amount">
		<g:message code="periodicIncome.amount.label" default="Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="amount" required="" value="${periodicIncomeInstance.amount}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: periodicIncomeInstance, field: 'comment', 'error')} ">
	<label for="comment">
		<g:message code="periodicIncome.comment.label" default="Comment" />
		
	</label>
	<g:textField name="comment" value="${periodicIncomeInstance?.comment}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: periodicIncomeInstance, field: 'periodicity', 'error')} required">
	<label for="periodicity">
		<g:message code="periodicIncome.periodicity.label" default="Periodicity" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="periodicity" required="" value="${periodicIncomeInstance.periodicity}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: periodicIncomeInstance, field: 'startMoment', 'error')} required">
	<label for="startMoment">
		<g:message code="periodicIncome.startMoment.label" default="Start Moment" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="startMoment" precision="day"  value="${periodicIncomeInstance?.startMoment}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: periodicIncomeInstance, field: 'stopMoment', 'error')} required">
	<label for="stopMoment">
		<g:message code="periodicIncome.stopMoment.label" default="Stop Moment" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="stopMoment" precision="day"  value="${periodicIncomeInstance?.stopMoment}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: periodicIncomeInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="periodicIncome.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${ru.moneyshark.User.list()}" optionKey="id" required="" value="${periodicIncomeInstance?.user?.id}" class="many-to-one"/>
</div>

