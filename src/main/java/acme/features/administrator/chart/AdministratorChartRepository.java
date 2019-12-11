
package acme.features.administrator.chart;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorChartRepository extends AbstractRepository {

	@Query("select c.sector,count(c) FROM CompanyRecord c where c.sector IN (select i.workSector FROM InvestorRecords i) group by c.sector order by c.sector")
	Object[] findCommonSectorsOfCompanies();

	@Query("select i.workSector,count(i) FROM InvestorRecords i where i.workSector IN (select c.sector FROM CompanyRecord c) group by i.workSector order by i.workSector")
	Object[] findCommonSectorsOfInvestors();

	@Query("select c.sector,count(c) FROM CompanyRecord c where c.sector NOT IN (select i.workSector FROM InvestorRecords i) group by c.sector order by c.sector")
	Object[] findCompanySector();

	@Query("select i.workSector,count(i) FROM InvestorRecords i where i.workSector NOT IN (select c.sector FROM CompanyRecord c) group by i.workSector order by i.workSector")
	Object[] findInvestorSector();

	//D04

	@Query("select 1.0*count(j)/(select count(b) from Job b) from Job j where j.status=1")
	Double findRatioPublishedJob();

	@Query("select 1.0*count(j)/(select count(b) from Job b) from Job j where j.status=0")
	Double findRatioDraftJob();

	@Query("select 1.0*count(a)/(select count(b) from Application b) from Application a where a.status=0")
	Double findRatioPendingApplications();

	@Query("select 1.0*count(a)/(select count(b) from Application b) from Application a where a.status=2")
	Double findRatioRejectedApplications();

	@Query("select 1.0*count(a)/(select count(b) from Application b) from Application a where a.status=1")
	Double findRatioAcceptedApplications();

	//D05

	@Query("select a.creationMoment,count(a) from Application a where a.status=0 and 365*YEAR(current_time())+30*MONTH(current_time())+DAY(current_time())-365*YEAR(a.creationMoment)-30*MONTH(a.creationMoment)-DAY(a.creationMoment)<28 group by DAY(a.creationMoment) order by a.creationMoment asc")
	Object[] findPendingAppsInFourWeeks();

	@Query("select a.creationMoment,count(a) from Application a where a.status=1 and 365*YEAR(current_time())+30*MONTH(current_time())+DAY(current_time())-365*YEAR(a.creationMoment)-30*MONTH(a.creationMoment)-DAY(a.creationMoment)<28 group by DAY(a.creationMoment) order by a.creationMoment asc")
	Object[] findAcceptedAppsInFourWeeks();

	@Query("select a.creationMoment,count(a) from Application a where a.status=2 and 365*YEAR(current_time())+30*MONTH(current_time())+DAY(current_time())-365*YEAR(a.creationMoment)-30*MONTH(a.creationMoment)-DAY(a.creationMoment)<28 group by DAY(a.creationMoment) order by a.creationMoment asc")
	Object[] findRejectedAppsInFourWeeks();
}
