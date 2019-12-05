<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-url code="administrator.commercial-banner.form.label.picture" path="picture"/>
	<acme:form-textarea code="administrator.commercial-banner.form.label.slogan" path="slogan"/>
	<acme:form-url code="administrator.commercial-banner.form.label.targetURL" path="targetURL"/>
	<acme:form-textbox code="administrator.commercial-banner.form.label.creditCard" path="creditCard"/>
	
	<acme:form-submit test="${command == 'show'}" code="administrator.commercial-banner.form.button.update" action="update"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.commercial-banner.form.button.delete" action="delete"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.commercial-banner.form.button.update" action="update"/>
	<acme:form-submit test="${command == 'delete'}" code="administrator.commercial-banner.form.button.delete" action="delete"/>
	<acme:form-submit test="${command == 'create'}" code="administrator.commercial-banner.form.button.create" action="create"/>
	<acme:form-return code="administrator.commercial-banner.form.button.return"/>
</acme:form>