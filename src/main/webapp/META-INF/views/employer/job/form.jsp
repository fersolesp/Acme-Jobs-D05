<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="employer.job.form.label.reference" path="reference"/>
	<acme:form-textbox code="employer.job.form.label.status" path="status"/>
	<acme:form-textbox code="employer.job.form.label.title" path="title"/>
	<acme:form-moment code="employer.job.form.label.deadline" path="deadline"/>
	<acme:form-money code="employer.job.form.label.salary" path="salary"/>
	<acme:form-url code="employer.job.form.label.moreInfo" path="moreInfo"/>
	
	<acme:form-panel code="employer.job.form.panel.descriptor">
		<acme:form-textbox code="employer.job.form.label.description" path="descriptor.description"/>
	</acme:form-panel>
	
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/employer/duty/list-mine?id=${descriptor.id}')" 
		class="btn btn-default">
		<acme:message code="employer.job.form.label.descriptorMessage"/>
	</button>
		
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/employer/audit-record/list?id=${id}')" 
		class="btn btn-default">
		<acme:message code="employer.job.form.label.auditRecords"/>
	</button>
	
	<acme:form-return code="employer.job.form.button.return"/>
</acme:form>
