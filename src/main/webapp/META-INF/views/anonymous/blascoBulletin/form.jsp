<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.blascoBulletin.form.label.userName" path="userName"/>
	<acme:form-textarea code="anonymous.blascoBulletin.form.label.description" path="description"/>
	
	<acme:form-submit code="anonymous.blascoBulletin.form.button.create" action="/anonymous/blascoBulletin/create"/>
	<acme:form-return code="anonymous.blascoBulletin.form.button.return"/> 
</acme:form>