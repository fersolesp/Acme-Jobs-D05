<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="consumer.offer.form.label.title" path="title"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="consumer.offer.form.label.moment" path="moment" readonly="true"/>
	</jstl:if>
	<acme:form-moment code="consumer.offer.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="consumer.offer.form.label.description" path="description"/>
	<acme:form-textbox code="consumer.offer.form.label.ticker" path="ticker"/>
	<acme:form-panel code="consumer.offer.form.label.reward">
	<acme:form-money code="consumer.offer.form.label.minReward" path="minReward"/>
	<acme:form-money code="consumer.offer.form.label.maxReward" path="maxReward"/>
	</acme:form-panel>
	<jstl:if test="${command == 'create'}">
		<acme:form-checkbox code="consumer.offer.form.label.confirm" path="confirm"/>
		<acme:form-submit code="consumer.offer.form.button.create" action="create"/>	
	</jstl:if>
	<acme:form-return code="consumer.offer.form.button.return"/>
</acme:form>