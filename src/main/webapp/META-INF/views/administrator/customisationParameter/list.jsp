<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.customisationParameter.list.label.spamWordList" path="spamWordList" width="70%"/>
	<acme:list-column code="administrator.customisationParameter.list.label.spamThreshold" path="spamThreshold" width="30%"/>
</acme:list>