<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.sola-bulletin.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="anonymous.sola-bulletin.list.label.cybernaut" path="cybernaut" width="20%"/>
	<acme:list-column code="anonymous.sola-bulletin.list.label.contribution" path="contribution" width="60%"/>
</acme:list>
