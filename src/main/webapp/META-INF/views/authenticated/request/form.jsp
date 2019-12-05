<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.request.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.request.form.label.moment" path="moment"/>
	<acme:form-moment code="authenticated.request.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.request.form.label.text" path="text"/>
	<acme:form-money code="authenticated.request.form.label.reward" path="reward"/>
	<acme:form-textarea code="authenticated.request.form.label.ticker" path="ticker"/>
		
	<acme:form-return code="authenticated.request.form.button.return"/>
</acme:form>