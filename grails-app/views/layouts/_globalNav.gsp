<g:if test="${session && session.user}">
	<div class="nav">
		<span class="menuButton">
	   		<g:link class="list" controller="balance" action="list">
	   			<g:message code="balance.list.label"/>
	   		</g:link>
	   	</span>
	   	<span class="menuButton">
	   		<g:link class="list" controller="income" action="list">
	   			<g:message code="income.list.label"/>
	   		</g:link>
	   	</span>
	    <%--<span class="menuButton">
	   		<g:link class="list" controller="PeriodicIncome" action="list">
	   			<g:message code="periodicincome.list.label"/>
	   		</g:link>
	   	</span>	   	
	    --%><span class="menuButton">
	    	<g:link class="list" controller="outcome" action="list">
	    		<g:message code="outcome.list.label" />
	    	</g:link>
	    </span>
	    <%--<span class="menuButton">
	    	<g:link class="list" controller="PeriodicOutcome" action="list">
	    		<g:message code="periodicoutcome.list.label" />
	    	</g:link>
	    </span>	    
	--%><span class="menuButton">
	    	<g:link class="list" controller="user" action="edit" >
	    		<g:message code="user.edit.label" />
	    	</g:link>
	    </span>
	</div>
</g:if>