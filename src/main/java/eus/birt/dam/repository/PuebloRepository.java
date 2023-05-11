package eus.birt.dam.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import eus.birt.dam.domain.Pueblo;

public interface PuebloRepository extends MongoRepository<Pueblo, String>{



	Optional<Pueblo> findByPropertiesDocumentname(String name);

	List<Pueblo> findByPropertiesTerritory(String name);
	
}
