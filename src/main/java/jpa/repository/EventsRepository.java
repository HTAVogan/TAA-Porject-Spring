package jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jpa.Entites.Events;
import jpa.Entites.Location;

public interface EventsRepository extends JpaRepository<Events, Long>{

	Events findByTitle(String title);
	
	List<Events> findAllByLocation(Location location);
	
	
}