<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.investor-records.list.label.investorName" path="investorName" width="40%"/>
	<acme:list-column code="authenticated.investor-records.list.label.workSector" path="workSector" width="40%"/>
	<acme:list-column code="authenticated.investor-records.list.label.stars" path="stars" width="20%"/>
</acme:list>
