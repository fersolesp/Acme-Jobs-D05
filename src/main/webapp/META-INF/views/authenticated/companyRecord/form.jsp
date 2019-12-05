<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.companyRecord.form.label.companyName" path="companyName"/>
	<acme:form-textbox code="authenticated.companyRecord.form.label.sector" path="sector"/>
	<acme:form-textbox code="authenticated.companyRecord.form.label.ceo" path="ceo"/>
	<acme:form-textarea code="authenticated.companyRecord.form.label.description" path="description"/>
	<acme:form-url code="authenticated.companyRecord.form.label.url" path="url"/>
	<acme:form-textbox code="authenticated.companyRecord.form.label.phone" path="phone"/>
	<acme:form-textbox code="authenticated.companyRecord.form.label.email" path="email"/>
	<acme:form-checkbox code="authenticated.companyRecord.form.label.incorporated" path="incorporated"/>
	<acme:form-integer code="authenticated.companyRecord.form.label.stars" path="stars"/>
	
	
	<acme:form-return code="authenticated.companyRecord.form.button.return"/>
</acme:form>