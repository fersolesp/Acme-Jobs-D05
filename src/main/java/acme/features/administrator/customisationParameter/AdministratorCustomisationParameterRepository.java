
package acme.features.administrator.customisationParameter;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCustomisationParameterRepository extends AbstractRepository {

	@Query("select c from CustomisationParameter c where c.id = ?1")
	CustomisationParameter findOneCustomisationParameterById(int id);

	@Query("select c from CustomisationParameter c")
	Collection<CustomisationParameter> findManyCustomisationParameters();
}
