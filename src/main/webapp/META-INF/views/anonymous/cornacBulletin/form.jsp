<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.cornacBulletin.form.label.publisher" path="publisher"/>
	<acme:form-textarea code="anonymous.cornacBulletin.form.label.publication" path="publication"/>
	
	<acme:form-submit code="anonymous.cornacBulletin.form.button.create" action="/anonymous/cornac-bulletin/create"/>
	<acme:form-return code="anonymous.cornacBulletin.form.button.return"/>
</acme:form>
