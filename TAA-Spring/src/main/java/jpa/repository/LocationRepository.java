package jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jpa.Entites.Departement;
import jpa.Entites.Location;
import jpa.Entites.Region;

public interface LocationRepository extends JpaRepository<Location, Long>{
	
	List<Location> findAll();
	
	Location findByName(String name);
	
	/*
	List<Location> findAllRegion();
	
	List<Location> findAllVilleByRegion(Region r);
	
	List<Location> findAllVilleByDepartement(Departement d);
	
	List<Location> findAllDepartementByRegion(Region r);*/
}
