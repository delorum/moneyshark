<g:if test="${session && session.user}">
	<div class="nav">
		<span class="menuButton">
	   		<g:link class="list" controller="balance" action="list">
	   			<g:message code="balance.label"/>
	   		</g:link>
	   	</span>
	   	<span class="menuButton">
	   		<g:link class="list" controller="income" action="list">
	   			<g:message code="income.label"/>
	   		</g:link>
	   	</span>
	    <span class="menuButton">
	   		<g:link class="list" controller="periodicIncome" action="list">
	   			<g:message code="periodicincome.label"/>
	   		</g:link>
	   	</span>	   	
	    <span class="menuButton">
	    	<g:link class="list" controller="outcome" action="list">
	    		<g:message code="outcome.label" />
	    	</g:link>
	    </span>
	    <span class="menuButton">
	    	<g:link class="list" controller="periodicOutcome" action="list">
	    		<g:message code="periodicoutcome.label" />
	    	</g:link>
	    </span>	    
		<span class="menuButton">
	    	<g:link class="list" controller="user" action="edit" >
	    		<g:message code="user.edit.label" />
	    	</g:link>
	    </span>
	</div>
</g:if>