<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.duty.list.label.title" path="title" width="30%"/>
	<acme:list-column code="authenticated.duty.list.label.description" path="description" width="70%"/>
</acme:list>

<acme:form>
	<acme:form-return code="authenticated.duty.list.button.return"/>
</acme:form>