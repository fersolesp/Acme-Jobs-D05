<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.messageThread.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="authenticated.messageThread.list.label.title" path="title" width="40%"/>
</acme:list>
<acme:form>
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/authenticated/message-thread/create')"
	class="btn btn-default">
		<acme:message code="authenticated.messageThread.form.button.create" />
	</button>
	<acme:form-return code="authenticated.message.list.button.return"/>
</acme:form>