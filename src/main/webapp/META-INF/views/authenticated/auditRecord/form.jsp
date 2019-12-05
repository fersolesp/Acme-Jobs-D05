<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.auditRecord.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.auditRecord.form.label.status" path="status"/>
	<acme:form-moment code="authenticated.auditRecord.form.label.moment" path="moment"/>
	<acme:form-textarea code="authenticated.auditRecord.form.label.body" path="body"/>
	<acme:form-textbox code="authenticated.auditRecord.form.label.auditor" path="auditor.userAccount.username"/>
	
	
	<acme:form-return code="authenticated.auditRecord.form.button.return"/>
</acme:form>