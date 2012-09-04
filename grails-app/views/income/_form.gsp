<%@ page import="ru.moneyshark.Income" %>



<div class="fieldcontain ${hasErrors(bean: incomeInstance, field: 'amount', 'error')} required">
	<label for="amount">
		<g:message code="income.amount.label" default="Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="amount" required="" value="${incomeInstance.amount}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: incomeInstance, field: 'comment', 'error')} ">
	<label for="comment">
		<g:message code="income.comment.label" default="Comment" />
		
	</label>
	<g:textField name="comment" value="${incomeInstance?.comment}"/>
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

<div class="fieldcontain ${hasErrors(bean: incomeInstance, field: 'user', 'error')} ">
	<label for="user">
		<g:message code="income.user.label" default="User" />
		
	</label>
	<g:select name="user" from="${ru.moneyshark.User.list()}" multiple="multiple" optionKey="id" size="5" value="${incomeInstance?.user*.id}" class="many-to-many"/>
</div>

