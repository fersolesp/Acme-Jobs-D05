<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.pradas-bulletin.form.label.person" path="person"/>
	<acme:form-textarea code="anonymous.pradas-bulletin.form.label.information" path="information"/>
	
	<acme:form-submit code="anonymous.pradas-bulletin.form.button.create" action="/anonymous/pradas-bulletin/create"/>
	<acme:form-return code="anonymous.pradas-bulletin.form.button.return"/>
</acme:form>