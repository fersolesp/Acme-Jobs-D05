<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.companyRecord.form.label.companyName" path="companyName"/>
	<acme:form-textbox code="administrator.companyRecord.form.label.sector" path="sector"/>
	<acme:form-textbox code="administrator.companyRecord.form.label.ceo" path="ceo"/>
	<acme:form-textarea code="administrator.companyRecord.form.label.description" path="description"/>
	<acme:form-url code="administrator.companyRecord.form.label.url" path="url"/>
	<acme:form-textbox code="administrator.companyRecord.form.label.phone" path="phone" placeholder="+034(0922)954468166"/>
	<acme:form-textbox code="administrator.companyRecord.form.label.email" path="email"/>
	<acme:form-checkbox code="administrator.companyRecord.form.label.incorporated" path="incorporated"/>
	<acme:form-integer code="administrator.companyRecord.form.label.stars" path="stars"/>
	
	<acme:form-submit test="${command == 'show' }" code="administrator.companyRecord.form.button.update" action="update"/>
	<acme:form-submit test="${command == 'show' }" code="administrator.companyRecord.form.button.delete" action="delete"/>
	<acme:form-submit test="${command == 'create' }" code="administrator.companyRecord.form.button.create" action="create"/>
	<acme:form-submit test="${command == 'update' }" code="administrator.companyRecord.form.button.update" action="update"/>
	<acme:form-submit test="${command == 'delete' }" code="administrator.companyRecord.form.button.delete" action="delete"/>
	
	<acme:form-return code="administrator.companyRecord.form.button.return"/>
</acme:form>