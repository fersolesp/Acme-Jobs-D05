<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
	<acme:message code="administrator.chart.form.title.totalSectors"/>
</h2>


<div>
	<canvas id="canvas"></canvas>
<h2>
	<acme:message code="administrator.chart.form.title.ratioByStatus"/>
</h2>
	<canvas id="canvas2"></canvas>
<h2>
	<acme:message code="administrator.chart.form.title.applicationsPerDay"/>
</h2>
	<canvas id="canvas3"></canvas>	
</div>
		   		

<script type="text/javascript">
	$(document).ready(function(){
		var data = {
			labels : [	<jstl:forEach var="i" items="${CommonSectorsOfInvestors}">
							"<jstl:out value='${i[0]}'/>",
						</jstl:forEach>
							
						<jstl:forEach var="i" items="${investorSector}">
							"<jstl:out value='${i[0]}'/>",
						</jstl:forEach>
							
						<jstl:forEach var="i" items="${companySector}">
							"<jstl:out value='${i[0]}'/>",
						</jstl:forEach>
					],
			datasets : [
				{
					backgroundColor:"rgba(74, 189, 172, 0.7)",
					label:"<acme:message code='administrator.chart.form.label.investor'/>",
					
					data :[	<jstl:forEach var="i" items="${CommonSectorsOfInvestors}">
								<jstl:out value='${i[1]}'/>,
							</jstl:forEach>
								
							<jstl:forEach var="i" items="${investorSector}">
								<jstl:out value='${i[1]}'/>,
							</jstl:forEach>
								
							<jstl:forEach var="i" items="${companySector}">
								<jstl:out value='0'/>,
							</jstl:forEach>
				 	]
				},
				{
					backgroundColor:"rgba(247, 183, 51, 0.7)",
					label:"<acme:message code='administrator.chart.form.label.companies'/>",
					
					data :[	<jstl:forEach var="i" items="${CommonSectorsOfCompanies}">
								<jstl:out value='${i[1]}'/>,
							</jstl:forEach>
								
							<jstl:forEach var="i" items="${investorSector}">
								<jstl:out value='0'/>,
							</jstl:forEach>
								
							<jstl:forEach var="i" items="${companySector}">
								<jstl:out value='${i[1]}'/>,
							</jstl:forEach>
						]
				}
			]
		};
		
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 1.0
						}
					}
				]
			},
			legend : {
				display: true
			}
		};
	
		var canvas, context;
		canvas=document.getElementById("canvas");
		context=canvas.getContext("2d");
		
		new Chart( context, {
			type : "bar",
			data : data,
			options : options
		});
	});
	
</script>

<script type="text/javascript">
	$(document).ready(function(){
		var data = {
			labels : [	"PUBLISHED","DRAFT","ACCEPTED","PENDING","REJECTED"
					],
			datasets : [
				{
					backgroundColor:"rgba(74, 189, 172, 0.7)",
					label:"<acme:message code='administrator.chart.form.label.jobs'/>",
					
					data :[	<jstl:out value='${ratioPublishedJobs}'/>,<jstl:out value='${ratioDraftJobs}'/>,0,0,0
						
				 	]
				},
				{
					backgroundColor:"rgba(247, 183, 51, 0.7)",
					label:"<acme:message code='administrator.chart.form.label.applications'/>",
					
					data :[	0,0,<jstl:out value='${ratioAcceptedApplications}'/>,<jstl:out value='${ratioPendingApplications}'/>,
						<jstl:out value='${ratioRejectedApplications}'/>
						]
				}
			]
		};
		
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 1.0
						}
					}
				]
			},
			legend : {
				display: true
			}
		};
	
		var canvas2, context;
		canvas2=document.getElementById("canvas2");
		context=canvas2.getContext("2d");
		
		new Chart( context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>

<script type="text/javascript">



	$(document).ready(function(){
		var data = {	
				datasets : [
					{
						borderColor:"rgba(74, 189, 172, 0.7)",
						backgroundColor:"rgba(255, 255, 255, 0)",
						label:"<acme:message code='administrator.chart.form.label.acceptedPerDay'/>",
						
						data :[	
							<jstl:forEach var="e" items="${zerosToActualDate}">
							<jstl:set var="valueDate" value="0"/>
								{
									t: new Date("<jstl:out value='${e}'/>"),
								<jstl:forEach var="i" items="${acceptedApplicationsInFourWeeks}">
									<jstl:if test="${e==i[0]}">
										<jstl:set var="valueDate" value="${i[1]}"/>
									</jstl:if>
								</jstl:forEach>
									y:<jstl:out value='${valueDate}'/>
								},
							</jstl:forEach>
					 	]
					},
					{
						borderColor:"rgba(255, 102, 102,0.7)",
						backgroundColor:"rgba(255, 255, 255, 0)",
						label:"<acme:message code='administrator.chart.form.label.rejectedPerDay'/>",
						
						data :[	
							<jstl:forEach var="e" items="${zerosToActualDate}">
							<jstl:set var="valueDate" value="0"/>
							
							{
							t: new Date("<jstl:out value='${e}'/>"),
							
							<jstl:forEach var="i" items="${rejectedApplicationsInFourWeeks}">
								<jstl:if test="${e==i[0]}">
									<jstl:set var="valueDate" value="${i[1]}"/>
								</jstl:if>
							</jstl:forEach>
							
							y:<jstl:out value='${valueDate}'/>
							},
						</jstl:forEach>
					 	]
					},
					{
						borderColor:"rgba(247, 183, 51, 0.7)",
						backgroundColor:"rgba(255, 255, 255, 0)",
						label:"<acme:message code='administrator.chart.form.label.pendingPerDay'/>",
						
						data :[
							<jstl:forEach var="e" items="${zerosToActualDate}">
							<jstl:set var="valueDate" value="0"/>
							
							{
							t: new Date("<jstl:out value='${e}'/>"),
							
							<jstl:forEach var="i" items="${pendingApplicationsInFourWeeks}">
								<jstl:if test="${e==i[0]}">
									<jstl:set var="valueDate" value="${i[1]}"/>
								</jstl:if>
							</jstl:forEach>
							
							y:<jstl:out value='${valueDate}'/>
							},
						</jstl:forEach>
					 	]
					},					
				]
			};
		
		var options = {
				scales: {
		            xAxes: [{
		                type: 'time',
		                distribution: 'series',
		                time:{
		                	unit:'day'
		                }
		                
		            }],
		            yAxes : [
						{
							ticks : {
								suggestedMin : 0.0,
								suggestedMax : 1.0
							}
						}
					]
		        },
			legend : {
				display: true
			}
		};
	
		var canvas3, context;
		canvas3=document.getElementById("canvas3");
		context=canvas3.getContext("2d");
		
		new Chart( context, {
			type : "line",
			data : data,
			options : options
		});
	});
	
</script>