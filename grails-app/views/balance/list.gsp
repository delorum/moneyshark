
<%@ page import="ru.moneyshark.Balance" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:javascript src="flotr2.min.js" />
		<g:set var="entityName" value="${message(code: 'balance.label', default: 'Balance')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<span class="menuButton">
		   		<g:link class="create" controller="income" action="create">
		   			<g:message code="income.new.label" />
		   		</g:link>
		   	</span>
		   	<span class="menuButton">
		   		<g:link class="create" controller="periodicIncome" action="create">
		   			<g:message code="periodicincome.new.label" />
		   		</g:link>
		   	</span>
			<span class="menuButton">
		   		<g:link class="create" controller="outcome" action="create">
		   			<g:message code="outcome.new.label" />
		   		</g:link>
		   	</span>
		   	<span class="menuButton">
		   		<g:link class="create" controller="periodicOutcome" action="create">
		   			<g:message code="periodicoutcome.new.label" />
		   		</g:link>
		   	</span>
		</div>
		<div id="list-balance" class="content scaffold-list" role="main">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr class="prop">
						<td valign="top" class="name">
							Текущий баланс:	
						</td>
						<td valign="top" class="name">
							${currentBalance}	
						</td>		
					</tr>
				</thead>
			</table>
			<g:if test="${awaitingIncomes.size() > 0}">
				<h1><g:message code="balance.awaitingincomes.list.label" /></h1>
				<table>
					<thead>
						<tr>
						
							<g:sortableColumn property="amount" title="${message(code: 'income.amount.label')}" />
						
							<g:sortableColumn property="comment" title="${message(code: 'income.comment.label')}" />
						
							<g:sortableColumn property="date" title="${message(code: 'income.date.label')}" />
							
							<th class="sortable">${message(code: 'default.actions.label', default: 'Actions')}</th>
						
						</tr>
					</thead>
					<tbody>
					<g:each in="${awaitingIncomes}" status="i" var="incomeInstance">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						
							<td><g:link controller="income" action="edit" id="${incomeInstance.id}">${fieldValue(bean: incomeInstance, field: "amount")}</g:link></td>
						
							<td><g:link controller="income" action="edit" id="${incomeInstance.id}">${fieldValue(bean: incomeInstance, field: "comment")}</g:link></td>
						
							<td><g:link controller="income" action="edit" id="${incomeInstance.id}"><g:formatDate date="${incomeInstance.date}" /></g:link></td>
						
							<td>
		                        	<div align="center">
		                        		<span class="menuButton" onclick="return confirm('\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
			                        		<g:link class="accept"
			                        				controller="income"
			                        				title="Accept"
			                        				action="accept" 
			                        				id="${incomeInstance?.id}" />		                        		
		                        		</span>
		                        		<span class="menuButton" onclick="return confirm('\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
			                        		<g:link class="delete" 
			                        				controller="income"
			                        				title="Delete"
			                        				action="delete" 
			                        				id="${incomeInstance?.id}" />		                        		
		                        		</span>
	                        		</div>
	                        </td>
	                        
						</tr>
					</g:each>
					</tbody>
				</table>
			</g:if>
			<g:if test="${awaitingOutcomes.size() > 0}">
				<h1><g:message code="balance.awaitingoutcomes.list.label" /></h1>
				<table>
					<thead>
						<tr>
						
							<g:sortableColumn property="amount" title="${message(code: 'outcome.amount.label')}" />
						
							<g:sortableColumn property="comment" title="${message(code: 'outcome.comment.label')}" />
						
							<g:sortableColumn property="date" title="${message(code: 'outcome.date.label')}" />
							
							<th class="sortable">${message(code: 'default.actions.label', default: 'Actions')}</th>
						
						</tr>
					</thead>
					<tbody>
					<g:each in="${awaitingOutcomes}" status="i" var="outcomeInstance">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						
							<td><g:link controller="outcome" action="edit" id="${outcomeInstance.id}">${fieldValue(bean: outcomeInstance, field: "amount")}</g:link></td>
						
							<td><g:link controller="outcome" action="edit" id="${outcomeInstance.id}">${fieldValue(bean: outcomeInstance, field: "comment")}</g:link></td>
						
							<td><g:link controller="outcome" action="edit" id="${outcomeInstance.id}"><g:formatDate date="${outcomeInstance.date}" /></g:link></td>
						
							<td>
		                        	<div align="center">
		                        		<span class="menuButton" onclick="return confirm('\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
			                        		<g:link class="accept"
			                        				controller="outcome"
			                        				title="Accept"
			                        				action="accept" 
			                        				id="${outcomeInstance?.id}" />		                        		
		                        		</span>
		                        		<span class="menuButton" onclick="return confirm('\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
			                        		<g:link class="delete" 
			                        				controller="outcome"
			                        				title="Delete"
			                        				action="delete" 
			                        				id="${outcomeInstance?.id}" />		                        		
		                        		</span>
	                        		</div>
	                        </td>
	                        
						</tr>
					</g:each>
					</tbody>
				</table>
			</g:if>
			<h1><g:message code="balance.history.label" /></h1>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="balance" title="${message(code: 'balance.balance.label')}" />
					
						<g:sortableColumn property="comment" title="${message(code: 'balance.comment.label')}" />
					
						<g:sortableColumn property="date" title="${message(code: 'balance.date.label')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${balanceInstanceList}" status="i" var="balanceInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: balanceInstance, field: "balance")}</td>
					
						<td>${fieldValue(bean: balanceInstance, field: "comment")}</td>
					
						<td><g:formatDate date="${balanceInstance.date}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="paginateButtons">
				<g:paginate total="${balanceInstanceTotal}" />
				<g:gridrows max="10,100,500,${balanceInstanceTotal}" controller="balance" />
			</div>
			<h1><g:message code="balance.plot.label" /></h1>
			<div id="balances-plot" style="margin: 0 auto; position: relative; width: 846px; height: 100px;"></div>
             	<script type="text/javascript">                                	
             		(function basic_bars(container) {
 					  var pew = ${balancesPlotData};                                		              
             		  // Draw the graph
             		  var graph = Flotr.draw(
             		    container,
             		    [pew],
             		    {
             		    	xaxis: {
                		    	min: ${minDate-1000},
                		    	max: ${maxDate+1000},
                		    	mode: 'time',            // => can be 'time' or 'normal'
                		        timeMode:'local',        // => For UTC time ('local' for local time).
                		        timeUnit:'millisecond',  // => Unit for time (millisecond, second, minute, hour, day, month, year)
                		        /*noTicks: 36,*/ 
            		    	    showLabels: false,
             		    		minorTickFreq: 4
            		    	}/*,
            		    	yaxis: {
            		    		showLabels: false,
								max: 2
                		    }*/, 
        				    grid: {
        					      minorVerticalLines: true
        					    },
                		    mouse : {
                		        track : true,
                		        relative : true,
                		        trackFormatter: function(a) {return "(" + new Date(Math.ceil(a.x)) + ")"}
                		    }
             		    }
             		  );
             		})(document.getElementById("balances-plot"));	
             	</script>
		</div>
	</body>
</html>
