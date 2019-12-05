<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="auditor.auditRecord.form.label.title" path="title"/>
	<acme:form-textbox code="auditor.auditRecord.form.label.status" path="status"/>
	<acme:form-moment code="auditor.auditRecord.form.label.moment" path="moment"/>
	<acme:form-textarea code="auditor.auditRecord.form.label.body" path="body"/>
	<acme:form-textbox code="auditor.auditRecord.form.label.auditor" path="auditor.userAccount.username"/>
	
	
	<acme:form-return code="auditor.auditRecord.form.button.return"/>
</acme:form>