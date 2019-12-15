<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="authenticated.messageThread.form.label.moment" path="moment"/>
	</jstl:if>
	<acme:form-textbox code="authenticated.messageThread.form.label.title" path="title" readonly="${command!='create'}"/>
				
	
	<jstl:if test="${command != 'create'}">
		<button type="button" onclick="javascript: clearReturnUrl(); redirect('/authenticated/message/list?id=${id}')"
				class="btn btn-default">
				<acme:message code="authenticated.messageThread.form.button.messages"/>
		</button>
		<jstl:if test="${creator}">
			<button type="button" onclick="javascript: clearReturnUrl(); redirect('/authenticated/participant/list?id=${id}')"
					class="btn btn-default">
					<acme:message code="authenticated.messageThread.form.button.participants"/>
			</button>
		</jstl:if>
	</jstl:if>
	
	<acme:form-submit test="${command=='create'}" code="authenticated.messageThread.form.button.create" action="create"/>
	<acme:form-return code="authenticated.messageThread.form.button.return"/>
</acme:form>


