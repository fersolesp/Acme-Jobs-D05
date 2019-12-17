<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="authenticated.message.form.label.moment" path="moment" readonly="true"/>
	</jstl:if>
	<acme:form-textbox code="authenticated.message.form.label.title" path="title" readonly="${command!='create'}"/>
	<acme:form-textarea code="authenticated.message.form.label.tags" path="tags" readonly="${command!='create'}"/>
	<acme:form-textarea code="authenticated.message.form.label.body" path="body" readonly="${command!='create'}"/>
	<acme:form-textbox code="authenticated.message.form.label.author" path="authenticated.userAccount.username" readonly="true"/>
	<acme:form-textbox code="authenticated.messageThread.form.label.title" path="messageThread.title" readonly="true"/>
	
	<acme:form-hidden path="messageThread.id"/>
	
	<jstl:if test="${command == 'create'}">
		<acme:form-checkbox code="authenticated.message.form.label.checkbox" path="confirm"/>
		<acme:form-submit code="authenticated.message.form.button.create" action="create"/>	
	</jstl:if>
	<acme:form-return code="authenticated.message.form.button.return"/>
</acme:form>
