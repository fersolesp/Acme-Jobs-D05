<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="anonymous.investor-records.form.label.investorName" path="investorName"/>
	<acme:form-textbox code="anonymous.investor-records.form.label.workSector" path="workSector"/>
	<acme:form-textarea code="anonymous.investor-records.form.label.investingStatement" path="investingStatement"/>
	<acme:form-integer code="anonymous.investor-records.form.label.stars" path="stars" placeholder=""/>
	
	<acme:form-return code="anonymous.investor-records.form.button.return"/>
</acme:form>
