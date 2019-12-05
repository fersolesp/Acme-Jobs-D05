<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="auditor.job.form.label.reference" path="reference"/>
	<acme:form-textbox code="auditor.job.form.label.status" path="status"/>
	<acme:form-textbox code="auditor.job.form.label.title" path="title"/>
	<acme:form-moment code="auditor.job.form.label.deadline" path="deadline"/>
	<acme:form-money code="auditor.job.form.label.salary" path="salary"/>
	<acme:form-url code="auditor.job.form.label.moreInfo" path="moreInfo"/>
	
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/auditor/audit-record/list?id=${id}')"
            class="btn btn-default">
            <acme:message code="auditor.job.form.button.auditRecord"/>
    </button>
    <button type="button" onclick="javascript: clearReturnUrl(); redirect('/auditor/duty/list?id=${id}')"
            class="btn btn-default">
            <acme:message code="auditor.job.form.button.duty"/>
    </button>
	
	<acme:form-return code="auditor.job.form.button.return"/>
</acme:form>