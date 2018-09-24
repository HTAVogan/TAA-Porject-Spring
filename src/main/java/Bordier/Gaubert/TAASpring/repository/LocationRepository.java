package Bordier.Gaubert.TAASpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Bordier.Gaubert.TAASpring.Departement;
import Bordier.Gaubert.TAASpring.Location;
import Bordier.Gaubert.TAASpring.Region;

public interface LocationRepository extends JpaRepository<Location, Long>{
	
	List<Location> findAll();
	
	Location findByName(String name);
	
	/*
	List<Location> findAllRegion();
	
	List<Location> findAllVilleByRegion(Region r);
	
	List<Location> findAllVilleByDepartement(Departement d);
	
	List<Location> findAllDepartementByRegion(Region r);*/
}
