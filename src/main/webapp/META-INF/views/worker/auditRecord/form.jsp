<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="worker.auditRecord.form.label.title" path="title"/>
	<acme:form-textbox code="worker.auditRecord.form.label.status" path="status"/>
	<acme:form-moment code="worker.auditRecord.form.label.moment" path="moment"/>
	<acme:form-textarea code="worker.auditRecord.form.label.body" path="body"/>
	<acme:form-textbox code="worker.auditRecord.form.label.auditor" path="auditor.userAccount.username"/>
	
	
	<acme:form-return code="worker.auditRecord.form.button.return"/>
</acme:form>