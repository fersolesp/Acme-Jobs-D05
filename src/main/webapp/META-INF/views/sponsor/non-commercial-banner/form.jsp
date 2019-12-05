<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-url code="sponsor.non-commercial-banner.form.label.picture" path="picture"/>
	<acme:form-textarea code="sponsor.non-commercial-banner.form.label.slogan" path="slogan"/>
	<acme:form-url code="sponsor.non-commercial-banner.form.label.targetURL" path="targetURL"/>
	<acme:form-url code="sponsor.non-commercial-banner.form.label.jingle" path="jingle"/>
	
	<acme:form-return code="sponsor.commercial-banner.form.button.return"/>
</acme:form>