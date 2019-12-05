<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="worker.auditRecord.list.label.title" path="title" width="20%"/>
	<acme:list-column code="worker.auditRecord.list.label.auditor" path="auditor.userAccount.username" width="20%"/>
	<acme:list-column code="worker.auditRecord.list.label.moment" path="moment" width="20%"/>
</acme:list>
<acme:form>
	<acme:form-return code="worker.auditRecord.list.button.return"/>
</acme:form>