<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="employer.application.form.label.referenceNumber" path="referenceNumber"/>
	<acme:form-moment code="employer.application.form.label.creationMoment" path="creationMoment"/>
	<acme:form-textbox code="employer.application.form.label.status" path="status"/>
	<acme:form-textarea code="employer.application.form.label.statement" path="statement"/>
	<acme:form-textarea code="employer.application.form.label.skills" path="skills"/>
	<acme:form-textarea code="employer.application.form.label.qualifications" path="qualifications"/>
	
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/employer/job/show?id=${job.id}')"
		class="btn btn-default">
		<acme:message code="employer.application.form.label.jobOf" />
	</button>
	
	<acme:form-return code="employer.application.form.button.return"/>
</acme:form>

