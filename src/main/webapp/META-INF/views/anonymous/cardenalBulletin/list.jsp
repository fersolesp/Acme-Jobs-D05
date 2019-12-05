<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.cardenalBulletin.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="anonymous.cardenalBulletin.list.label.developer" path="developer" width="20%"/>
	<acme:list-column code="anonymous.cardenalBulletin.list.label.skills" path="skills" width="60%"/>
</acme:list>