package info.db.dao;

import info.db.model.ApartmentsPE;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ApartmentsRepository extends CrudRepository<ApartmentsPE, Integer> {

    Optional<ApartmentsPE> findByID(Integer ID);

    List<ApartmentsPE> findAll();
}
