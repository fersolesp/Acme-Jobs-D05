<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.pradas-bulletin.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="anonymous.pradas-bulletin.list.label.person" path="person" width="20%"/>
	<acme:list-column code="anonymous.pradas-bulletin.list.label.information" path="information" width="60%"/>
</acme:list>
