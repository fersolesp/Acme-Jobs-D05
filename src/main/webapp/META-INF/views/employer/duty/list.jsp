<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="employer.duty.list.label.title" path="title" width="30%"/>
	<acme:list-column code="employer.duty.list.label.description" path="description" width="70%"/>
</acme:list>

<acme:form>
	<acme:form-return code="employer.duty.list.button.return"/>
</acme:form>