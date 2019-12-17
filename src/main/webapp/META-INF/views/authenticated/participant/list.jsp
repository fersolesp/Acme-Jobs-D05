<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.participant.list.label.username" path="authenticated.userAccount.username" width="40%"/>
	<acme:list-column code="authenticated.participant.list.label.title" path="messageThread.title" width="40%"/>
</acme:list>

<acme:form>
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/authenticated/participant/create?messageThread.id=${param.id}')"
	class="btn btn-default">
		<acme:message code="authenticated.participant.form.label.create" />
	</button>
	<acme:form-return code="authenticated.participant.form.button.return"/>
</acme:form>