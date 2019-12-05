<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>

	<acme:list-column code="authenticated.companyRecord.list.label.companyName" path="companyName" width="40%"/>
	<acme:list-column code="authenticated.companyRecord.list.label.sector" path="sector" width="30%"/>
	<acme:list-column code="authenticated.companyRecord.list.label.ceo" path="ceo" width="30%"/>

</acme:list>