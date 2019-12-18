<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.auditor-request.form.label.firm" path="firm" readonly="true"/>
	<acme:form-textarea code="administrator.auditor-request.form.label.responsabilityStatement" path="responsabilityStatement" readonly="true"/>
	<acme:form-select code="administrator.auditor-request.form.label.status" path="status">
        <acme:form-option code="administrator.auditor-request.form.label.status.pending" value="PENDING" selected="${status == 'PENDING'}"/>
        <acme:form-option code="administrator.auditor-request.form.label.status.accepted" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
        <acme:form-option code="administrator.auditor-request.form.label.status.rejected" value="REJECTED" selected="${status == 'REJECTED'}"/>
    </acme:form-select>
	
	<acme:form-submit code="administrator.auditor-request.form.button.update" action="update"/>
	
	<acme:form-return code="administrator.auditor-request.form.button.return"/>
</acme:form>