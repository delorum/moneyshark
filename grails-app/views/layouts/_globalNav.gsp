<g:if test="${session && session.user}">
	<div class="nav">
	   	<span class="menuButton">
	   		<g:link class="list" controller="Income" action="list">
	   			<g:message code="income.list.label"/>
	   		</g:link>
	   	</span>
	    <%--<span class="menuButton">
	   		<g:link class="list" controller="PeriodicIncome" action="list">
	   			<g:message code="periodicincome.list.label"/>
	   		</g:link>
	   	</span>	   	
	    --%><span class="menuButton">
	    	<g:link class="list" controller="Outcome" action="list">
	    		<g:message code="outcome.list.label" />
	    	</g:link>
	    </span>
	    <%--<span class="menuButton">
	    	<g:link class="list" controller="PeriodicOutcome" action="list">
	    		<g:message code="periodicoutcome.list.label" />
	    	</g:link>
	    </span>	    
	--%></div>
</g:if>