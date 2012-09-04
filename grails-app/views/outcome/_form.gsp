<%@ page import="ru.moneyshark.Outcome" %>



<div class="fieldcontain ${hasErrors(bean: outcomeInstance, field: 'amount', 'error')} required">
	<label for="amount">
		<g:message code="outcome.amount.label" default="Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="amount" required="" value="${outcomeInstance.amount}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: outcomeInstance, field: 'comment', 'error')} ">
	<label for="comment">
		<g:message code="outcome.comment.label" default="Comment" />
		
	</label>
	<g:textField name="comment" value="${outcomeInstance?.comment}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: outcomeInstance, field: 'date', 'error')} required">
	<label for="date">
		<g:message code="outcome.date.label" default="Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="date" precision="day"  value="${outcomeInstance?.date}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: outcomeInstance, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="outcome.status.label" default="Status" />
		
	</label>
	<g:textField name="status" value="${outcomeInstance?.status}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: outcomeInstance, field: 'user', 'error')} ">
	<label for="user">
		<g:message code="outcome.user.label" default="User" />
		
	</label>
	<g:select name="user" from="${ru.moneyshark.User.list()}" multiple="multiple" optionKey="id" size="5" value="${outcomeInstance?.user*.id}" class="many-to-many"/>
</div>

