<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.cardenalBulletin.form.label.developer" path="developer"/>
	<acme:form-textarea code="anonymous.cardenalBulletin.form.label.skills" path="skills"/>
	
	<acme:form-submit code="anonymous.cardenalBulletin.form.button.create" action="/anonymous/cardenal-bulletin/create"/>
	<acme:form-return code="anonymous.cardenalBulletin.form.button.return"/>
</acme:form>