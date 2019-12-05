<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">

	<acme:form-panel code="administrator.dashboard.form.label.totals">
		<acme:form-integer code="administrator.dashboard.form.label.totalAnnouncements" path="totalNumberOfAnnouncements"/>
		<acme:form-integer code="administrator.dashboard.form.label.totalCompanyRecords" path="totalNumberOfCompanyRecords"/>
		<acme:form-integer code="administrator.dashboard.form.label.totalInvestorRecords" path="totalNumberOfInvestorRecords"/>
	</acme:form-panel>
	
	<acme:form-panel code="administrator.dashboard.form.label.requests">
		<acme:form-double code="administrator.dashboard.form.label.maxRewardOfActiveRequests" path="maxRewardOfActiveRequests"/>
		<acme:form-double code="administrator.dashboard.form.label.minRewardOfActiveRequests" path="minRewardOfActiveRequests"/>
		<acme:form-double code="administrator.dashboard.form.label.avgRewardOfActiveRequests" path="avgRewardOfActiveRequests"/>
		<acme:form-double code="administrator.dashboard.form.label.standardDeviationRewardOfActiveRequests" path="standardDeviationRewardOfActiveRequests"/>
	</acme:form-panel>
	
	<acme:form-panel code="administrator.dashboard.form.label.offers">
		<acme:form-double code="administrator.dashboard.form.label.maxRewardOfActiveOffers" path="maxRewardOfActiveOffers"/>
		<acme:form-double code="administrator.dashboard.form.label.minRewardOfActiveOffers" path="minRewardOfActiveOffers"/>
		<acme:form-double code="administrator.dashboard.form.label.avgRewardOfActiveOffers" path="avgRewardOfActiveOffers"/>
		<acme:form-double code="administrator.dashboard.form.label.standardDeviationRewardOfActiveOffers" path="standardDeviationRewardOfActiveOffers"/>
	</acme:form-panel>

	<acme:form-panel code="administrator.dashboard.form.label.appsAndJobs">
		<acme:form-double code="administrator.dashboard.form.label.avgJobsPerEmployer" path="avgJobsPerEmployer"/>
		<acme:form-double code="administrator.dashboard.form.label.avgApplicationsPerEmployer" path="avgApplicationsPerEmployer"/>
		<acme:form-double code="administrator.dashboard.form.label.avgApplicationsPerWorker" path="avgApplicationsPerWorker"/>
	</acme:form-panel>
</acme:form>