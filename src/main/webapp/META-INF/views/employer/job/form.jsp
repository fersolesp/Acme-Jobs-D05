<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="${status == 'PUBLISHED' && command!='update'}">

	<!-- ATRIBUTOS -->
	
	<acme:form-textbox code="employer.job.form.label.reference" path="reference"/>
	<jstl:if test="${command != 'create'}">
	<acme:form-select code="employer.job.form.label.status" path="status">
		<acme:form-option code="employer.job.form.label.status.draft" value="DRAFT" selected="${status == 'DRAFT'}"/>
		<acme:form-option code="employer.job.form.label.status.published" value="PUBLISHED" selected="${status == 'PUBLISHED'}"/>
	</acme:form-select>
	</jstl:if>
	<acme:form-textbox code="employer.job.form.label.title" path="title"/>
	<acme:form-moment code="employer.job.form.label.deadline" path="deadline"/>
	<acme:form-money code="employer.job.form.label.salary" path="salary"/>
	<acme:form-url code="employer.job.form.label.moreInfo" path="moreInfo"/>
	<acme:form-panel code="employer.job.form.panel.descriptor">
		<acme:form-textbox code="employer.job.form.label.description" path="descriptor.description"/>
	</acme:form-panel>
	
	<!-- BOTONES -->

	<jstl:if test="${command == 'show'}">
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/employer/duty/list-mine?id=${descriptor.id}')" 
		class="btn btn-default">
		<acme:message code="employer.job.form.label.descriptorMessage"/>
	</button>
	<jstl:if test="${status == 'DRAFT'}">
		<button type="button" onclick="javascript: clearReturnUrl(); redirect('/employer/duty/create?id=${descriptor.id}')" 
			class="btn btn-default">
			<acme:message code="employer.job.form.label.createDuty"/>
		</button>
	</jstl:if>
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/employer/audit-record/list?id=${id}')" 
		class="btn btn-default">
		<acme:message code="employer.job.form.label.auditRecords"/>
	</button>
	</jstl:if>
	
	<acme:form-submit test="${command == 'show' && status == 'DRAFT'}" code="employer.job.form.button.update" action="update"/>
	<acme:form-submit test="${command == 'show' && numberOfApplications == 0}" code="employer.job.form.button.delete" action="delete"/>
	<acme:form-submit test="${command == 'create'}" code="employer.job.form.button.create" action="create"/>
	<acme:form-submit test="${command == 'update'}" code="employer.job.form.button.update" action="update"/>
	<acme:form-submit test="${command == 'delete'}" code="employer.job.form.button.delete" action="delete"/>
	<acme:form-return code="employer.job.form.button.return"/>
	
	<jstl:if test="${command == 'show' && numberOfApplications != 0}">
		<acme:message code="employer.job.form.label.cantDelete"/>
	</jstl:if>
</acme:form>
