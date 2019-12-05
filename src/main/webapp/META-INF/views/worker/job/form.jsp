<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="worker.job.form.label.reference" path="reference"/>
	<acme:form-textbox code="worker.job.form.label.status" path="status"/>
	<acme:form-textbox code="worker.job.form.label.title" path="title"/>
	<acme:form-moment code="worker.job.form.label.deadline" path="deadline"/>
	<acme:form-money code="worker.job.form.label.salary" path="salary"/>
	<acme:form-url code="worker.job.form.label.moreInfo" path="moreInfo"/>
	
	<acme:form-panel code="worker.job.form.panel.descriptor">
		<acme:form-textbox code="worker.job.form.label.description" path="descriptor.description"/>
	</acme:form-panel>
	
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/worker/duty/list-mine?id=${descriptor.id}')" 
	class="btn btn-default">
		<acme:message code="worker.job.form.label.descriptorMessage"/>
	</button>
	
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/worker/audit-record/list?id=${id}')" 
		class="btn btn-default">
		<acme:message code="worker.job.form.label.AuditRecordMessage"/>
	</button>
	
	<acme:form-return code="worker.job.form.button.return"/>
</acme:form>
