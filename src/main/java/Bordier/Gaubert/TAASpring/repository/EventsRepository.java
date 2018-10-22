package Bordier.Gaubert.TAASpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Bordier.Gaubert.TAASpring.Events;
import Bordier.Gaubert.TAASpring.Location;

public interface EventsRepository extends JpaRepository<Events, Long>{

	Events findByTitle(String title);
	
	List<Events> findAllByLocations(Location locations);
	
	
}