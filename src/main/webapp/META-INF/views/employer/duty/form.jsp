<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="${command!='create'}">
	<acme:form-textbox code="employer.duty.form.label.title" path="title"/>
	<acme:form-textbox code="employer.duty.form.label.description" path="description"/>
	<acme:form-textbox code="employer.duty.form.label.timeAmount" path="amountTime"/>
	<jstl:if test="${id==0}">
	<acme:form-submit test="${command == 'create'}" code="employer.duty.form.button.create" action="create?id=${descriptor.id}"/>
	</jstl:if>
	<jstl:if test="${id!=0}">
	<acme:form-submit test="${command == 'create'}" code="employer.duty.form.button.create" action="create?id=${id}"/>
	</jstl:if>
	<acme:form-submit test="${command == 'show'}" code="employer.duty.form.button.delete" action="delete?id=${id}"/>
	<acme:form-submit test="${command == 'delete'}" code="employer.duty.form.button.delete" action="delete?id=${id}"/>
	<acme:form-return code="employer.duty.form.button.return"/>
</acme:form>
