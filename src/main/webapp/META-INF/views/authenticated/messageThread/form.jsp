<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-moment code="authenticated.messageThread.form.label.moment" path="moment"/>
	<acme:form-textbox code="authenticated.messageThread.form.label.title" path="title"/>
				
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/authenticated/message/list?id=${id}')"
			class="btn btn-default">
			<acme:message code="authenticated.messageThread.form.button.messages"/>
	</button>
	
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/authenticated/authenticated/list?id=${id}')"
			class="btn btn-default">
			<acme:message code="authenticated.messageThread.form.button.participants"/>
	</button>
	
	<acme:form-return code="authenticated.messageThread.form.button.return"/>
</acme:form>


