<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.challenge.form.label.title" path="title"/>
	<acme:form-moment code="administrator.challenge.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="administrator.challenge.form.label.description" path="description"/>
	
	<acme:form-panel code="administrator.challenge.form.label.gold">
		<acme:form-textarea code="administrator.challenge.form.label.goldGoal" path="goldGoal"/>
		<acme:form-money code="administrator.challenge.form.label.goldReward" path="goldReward"/>
	</acme:form-panel>
	
	<acme:form-panel code="administrator.challenge.form.label.silver">
		<acme:form-textarea code="administrator.challenge.form.label.silverGoal" path="silverGoal"/>
		<acme:form-money code="administrator.challenge.form.label.silverReward" path="silverReward"/>
	</acme:form-panel>
	
	<acme:form-panel code="administrator.challenge.form.label.bronze">
		<acme:form-textarea code="administrator.challenge.form.label.bronzeGoal" path="bronzeGoal"/>
		<acme:form-money code="administrator.challenge.form.label.bronzeReward" path="bronzeReward"/>
	</acme:form-panel>
	
	<acme:form-submit test="${command == 'show'}" code="administrator.challenge.form.button.update" action="update"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.challenge.form.button.delete" action="delete"/>
	<acme:form-submit test="${command == 'create'}" code="administrator.challenge.form.button.create" action="create"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.challenge.form.button.update" action="update"/>
	<acme:form-submit test="${command == 'delete'}" code="administrator.challenge.form.button.delete" action="delete"/>
	
	<acme:form-return code="administrator.challenge.form.button.return"/>
</acme:form>