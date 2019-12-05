<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.sola-bulletin.form.label.cybernaut" path="cybernaut"/>
	<acme:form-textarea code="anonymous.sola-bulletin.form.label.contribution" path="contribution"/>
	
	<acme:form-submit code="anonymous.sola-bulletin.form.button.create" action="/anonymous/sola-bulletin/create"/>
	<acme:form-return code="anonymous.sola-bulletin.form.button.return"/>
</acme:form>
