<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.cornacBulletin.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="anonymous.cornacBulletin.list.label.publisher" path="publisher" width="20%"/>
	<acme:list-column code="anonymous.cornacBulletin.list.label.publication" path="publication" width="60%"/>
</acme:list>
