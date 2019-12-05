<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="worker.application.form.label.referenceNumber" path="referenceNumber"/>
	<acme:form-moment code="worker.application.form.label.creationMoment" path="creationMoment"/>
	<acme:form-textbox code="worker.application.form.label.status" path="status"/>
	<acme:form-textarea code="worker.application.form.label.statement" path="statement"/>
	<acme:form-textarea code="worker.application.form.label.skills" path="skills"/>
	<acme:form-textarea code="worker.application.form.label.qualifications" path="qualifications"/>
	
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/worker/job/show?id=${job.id}')"
		class="btn btn-default">
		<acme:message code="worker.application.form.label.jobOf" />
	</button>
	
	<acme:form-return code="worker.application.form.button.return"/>
</acme:form>

