<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<jstl:if test="${command == 'create' && sponsor.creditCard == ''}">
	<acme:message code="sponsor.commercial-banner.form.error.creditCard"/>
</jstl:if>

<acme:form>
	<acme:form-url code="sponsor.commercial-banner.form.label.picture" path="picture"/>
	<acme:form-textarea code="sponsor.commercial-banner.form.label.slogan" path="slogan"/>
	<acme:form-url code="sponsor.commercial-banner.form.label.targetURL" path="targetURL"/>
	<acme:form-textbox code="sponsor.commercial-banner.form.label.creditCard" path="creditCard" placeholder="${sponsor.creditCard}"/>
	
	
	<acme:form-submit test="${command == 'show'}" code="sponsor.commercial-banner.form.button.update" action="update"/>
	<acme:form-submit test="${command == 'show'}" code="sponsor.commercial-banner.form.button.delete" action="delete"/>
	<acme:form-submit test="${command == 'update'}" code="sponsor.commercial-banner.form.button.update" action="update"/>
	<acme:form-submit test="${command == 'delete'}" code="sponsor.commercial-banner.form.button.delete" action="delete"/>
	<acme:form-submit test="${command == 'create' && sponsor.creditCard != ''}" code="sponsor.commercial-banner.form.button.create" action="create"/>
	<acme:form-return code="sponsor.commercial-banner.form.button.return"/>
</acme:form>
