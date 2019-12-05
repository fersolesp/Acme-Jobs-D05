<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="employer.auditRecord.form.label.title" path="title"/>
	<acme:form-textbox code="employer.auditRecord.form.label.status" path="status"/>
	<acme:form-moment code="employer.auditRecord.form.label.moment" path="moment"/>
	<acme:form-textarea code="employer.auditRecord.form.label.body" path="body"/>
	<acme:form-textbox code="employer.auditRecord.form.label.auditor" path="auditor.userAccount.username"/>
	
	
	<acme:form-return code="employer.auditRecord.form.button.return"/>
</acme:form>