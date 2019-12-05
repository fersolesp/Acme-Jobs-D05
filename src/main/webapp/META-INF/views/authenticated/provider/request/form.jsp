<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="provider.request.form.label.title" path="title"/>
	<acme:form-moment code="provider.request.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="provider.request.form.label.text" path="text"/>
	<acme:form-money code="provider.request.form.label.reward" path="reward"/>
	<acme:form-textarea code="provider.request.form.label.ticker" path="ticker"/>
	
	<acme:form-checkbox code="provider.request.form.label.accept" path="accept"/>
	
	<acme:form-submit  code="provider.request.form.button.create" action="create"/>	
	<acme:form-return code="provider.request.form.button.return"/>
</acme:form>