<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly = "${command != 'create'}" >
	<acme:form-textbox code="worker.application.form.label.referenceNumber" path="referenceNumber"/>
	<jstl:if test="${command == 'show'}">
	<acme:form-moment code="worker.application.form.label.creationMoment" path="creationMoment" />
	</jstl:if>
	<jstl:if test="${command == 'show'}">
	<acme:form-textbox code="worker.application.form.label.status" path="status"/>
	</jstl:if>
	<acme:form-textarea code="worker.application.form.label.statement" path="statement"/>
	<acme:form-textarea code="worker.application.form.label.skills" path="skills"/>
	<acme:form-textarea code="worker.application.form.label.qualifications" path="qualifications"/>
	<jstl:if test="${status != 'PENDING' && command != 'create'}">
	<acme:form-textarea code="worker.application.form.label.justification" path="justification"/>
	</jstl:if>
	
	<jstl:if test="${command == 'show'}">
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/worker/job/show?id=${job.id}')"
		class="btn btn-default">
		<acme:message code="worker.application.form.label.jobOf" />
	</button>
	</jstl:if>
	
	<acme:form-submit test="${command == 'create'}" code="worker.application.form.button.create" action="create?id=${job.id}"/>
	
	
	<acme:form-return code="worker.application.form.button.return"/>
</acme:form>

