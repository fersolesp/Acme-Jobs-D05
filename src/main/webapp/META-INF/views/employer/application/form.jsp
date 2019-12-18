<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="${command == 'show' && status != 'PENDING'}">
	<acme:form-textbox code="employer.application.form.label.referenceNumber" path="referenceNumber" readonly="true"/>
	<acme:form-moment code="employer.application.form.label.creationMoment" path="creationMoment" readonly="true"/>
	<acme:form-textarea code="employer.application.form.label.statement" path="statement" readonly="true"/>
	<acme:form-textarea code="employer.application.form.label.skills" path="skills" readonly="true"/>
	<acme:form-textarea code="employer.application.form.label.qualifications" path="qualifications" readonly="true"/>
	<acme:form-select code="employer.application.form.label.status" path="status">
		<acme:form-option code="employer.application.form.label.status.pending" value="PENDING" selected="${status == 'PENDING'}"/>
		<acme:form-option code="employer.application.form.label.status.accepted" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
		<acme:form-option code="employer.application.form.label.status.rejected" value="REJECTED" selected="${status == 'REJECTED'}"/>
	</acme:form-select>
	<jstl:if test="${command == 'update'}">
	<acme:form-textarea code="employer.application.form.label.justification" path="justification" readonly="false"/>
	</jstl:if>
	<jstl:if test="${command == 'show'}">
	<acme:form-textarea code="employer.application.form.label.justification" path="justification" readonly="${status != 'PENDING'}"/>
	</jstl:if>
	<jstl:if test="${command != 'update'}">
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/employer/job/show?id=${job.id}')"
		class="btn btn-default">
		<acme:message code="employer.application.form.label.jobOf" />
	</button>
	</jstl:if>
	
	<acme:form-submit test="${command == 'show' && status == 'PENDING'}" code="employer.application.form.button.update" action="update"/>
	<acme:form-submit test="${command == 'update'}" code="employer.application.form.button.update" action="update"/>
	<acme:form-return code="employer.application.form.button.return"/>
</acme:form>

