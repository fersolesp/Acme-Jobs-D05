<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.customisationParameter.form.label.spamWordList" path="spamWordList"/>
	<acme:form-double code="administrator.customisationParameter.form.label.spamThreshold" path="spamThreshold"/>
	
	<acme:form-submit test="${command == 'show'}" code="administrator.customisationParameter.form.button.update" action="update"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.customisationParameter.form.button.update" action="update"/>
	
	<acme:form-return code="administrator.customisationParameter.form.button.return"/>
</acme:form>