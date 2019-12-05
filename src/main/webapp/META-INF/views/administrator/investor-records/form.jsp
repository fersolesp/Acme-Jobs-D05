<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	
	<acme:form-textbox code="administrator.investor-records.form.label.investorName" path="investorName"/>
	<acme:form-textbox code="administrator.investor-records.form.label.workSector" path="workSector"/>
	<acme:form-textarea code="administrator.investor-records.form.label.investingStatement" path="investingStatement"/>
	<acme:form-integer code="administrator.investor-records.form.label.stars" path="stars" placeholder=""/>
	
	<acme:form-submit test="${command == 'show'}" code="administrator.investor-records.form.button.update" action="update"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.investor-records.form.button.delete" action="delete"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.investor-records.form.button.update" action="update"/>
	<acme:form-submit test="${command == 'delete'}" code="administrator.investor-records.form.button.delete" action="delete"/>
	<acme:form-submit test="${command == 'create'}" code="administrator.investor-records.form.button.create" action="create"/>
	<acme:form-return code="administrator.investor-records.form.button.return"/>
</acme:form>
