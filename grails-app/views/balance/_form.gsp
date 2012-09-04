<%@ page import="ru.moneyshark.Balance" %>



<div class="fieldcontain ${hasErrors(bean: balanceInstance, field: 'balance', 'error')} required">
	<label for="balance">
		<g:message code="balance.balance.label" default="Balance" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="balance" required="" value="${balanceInstance.balance}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: balanceInstance, field: 'comment', 'error')} ">
	<label for="comment">
		<g:message code="balance.comment.label" default="Comment" />
		
	</label>
	<g:textField name="comment" value="${balanceInstance?.comment}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: balanceInstance, field: 'date', 'error')} required">
	<label for="date">
		<g:message code="balance.date.label" default="Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="date" precision="day"  value="${balanceInstance?.date}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: balanceInstance, field: 'user', 'error')} ">
	<label for="user">
		<g:message code="balance.user.label" default="User" />
		
	</label>
	<g:select name="user" from="${ru.moneyshark.User.list()}" multiple="multiple" optionKey="id" size="5" value="${balanceInstance?.user*.id}" class="many-to-many"/>
</div>

