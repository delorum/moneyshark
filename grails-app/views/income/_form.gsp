<%@ page import="ru.moneyshark.Income" %>



<div class="fieldcontain ${hasErrors(bean: incomeInstance, field: 'amount', 'error')} required">
	<label for="amount">
		<g:message code="income.amount.label" default="Amount" />
		<span class="required-indicator">*</span>
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: incomeInstance, field: 'comment', 'error')} ">
	<label for="comment">
		<g:message code="income.comment.label" default="Comment" />
		
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: incomeInstance, field: 'date', 'error')} required">
	<label for="date">
		<g:message code="income.date.label" default="Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="date" precision="day"  value="${incomeInstance?.date}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: incomeInstance, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="income.status.label" default="Status" />
		
	</label>
	<g:textField name="status" value="${incomeInstance?.status}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: incomeInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="income.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${ru.moneyshark.User.list()}" optionKey="id" required="" value="${incomeInstance?.user?.id}" class="many-to-one"/>
</div>

