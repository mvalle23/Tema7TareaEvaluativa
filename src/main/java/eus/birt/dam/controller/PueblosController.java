package eus.birt.dam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import eus.birt.dam.domain.Pueblo;
import eus.birt.dam.repository.PuebloRepository;

@CrossOrigin (origins= {"http://localhost:4200"})
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class PueblosController {

	
	@Autowired
	PuebloRepository puebloRepository;
	
	/**
	 * Endpoint principal de pueblos.
	 * @return
	 */
	@GetMapping("/pueblos")
	public ResponseEntity<List<Pueblo>> index() {
	    try {
	        List<Pueblo> pueblos = puebloRepository.findAll();
	        return new ResponseEntity<List<Pueblo>>(pueblos, HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<List<Pueblo>>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@GetMapping("/pueblos/{name}")
	public ResponseEntity<Pueblo> getPuebloByDocumentName(@PathVariable("name") String name) {
	    try {
	        Optional<Pueblo> optionalPueblo = puebloRepository.findByPropertiesDocumentname(name);
	        if (optionalPueblo.isPresent()) {
	            return new ResponseEntity<Pueblo>(optionalPueblo.get(), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<Pueblo>(HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<Pueblo>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	

    @GetMapping("id/{id}")
    public ResponseEntity<Pueblo> getPuebloById(@PathVariable("id") String id) {
        try {
            Optional<Pueblo> optionalPueblo = puebloRepository.findById(id);
            if (optionalPueblo.isPresent()) {
                return new ResponseEntity<Pueblo>(optionalPueblo.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<Pueblo>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Pueblo>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



	@GetMapping("/provinces")
	public ResponseEntity<List<String>> getAllTerritories() {
	    try {
	        List<Pueblo> pueblos = puebloRepository.findAll();
	        Set<String> uniqueTerritories = new HashSet<>();
	        for (Pueblo pueblo : pueblos) {
	            uniqueTerritories.add(pueblo.getProperties().getTerritory());
	        }
	        List<String> sortedTerritories = new ArrayList<>(uniqueTerritories);
	        Collections.sort(sortedTerritories);
	        return new ResponseEntity<List<String>>(sortedTerritories, HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<List<String>>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
		

	}
	@GetMapping("/provinces/{name}")
	public ResponseEntity<List<Pueblo>> getPueblosByProvinceName(@PathVariable("name") String name) {
	    try {
	        List<Pueblo> pueblos = puebloRepository.findByPropertiesTerritory(name);
	        if (!pueblos.isEmpty()) {
	            return new ResponseEntity<List<Pueblo>>(pueblos, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<List<Pueblo>>(HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<List<Pueblo>>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	



	

	
}