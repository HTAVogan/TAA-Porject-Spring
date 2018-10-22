package Bordier.Gaubert.TAASpring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Bordier.Gaubert.TAASpring.Location;
import Bordier.Gaubert.TAASpring.Region;
import Bordier.Gaubert.TAASpring.Departement;
import Bordier.Gaubert.TAASpring.Ville;
import Bordier.Gaubert.TAASpring.repository.LocationRepository;

@Controller
public class LocationController {
	
	@Autowired
	private LocationRepository locationRepository;
	
	/**
	 * GET /create  --> Create a new location and save it in the database.
	 */
	  @RequestMapping("/locations/create/{name}/{type}")
	  @ResponseBody
	  public String create(@PathVariable("name")String name, @PathVariable("type")String type) {
		  String locationCreatedId = "";
		  try {
			  Location foundLocation = locationRepository.findByName(name);
			  if(foundLocation == null) {
				  Location newLocation;
				  switch(Integer.valueOf(type)) {
					  case 0:
						newLocation = new Region(name);
						break;
					  case 1:
						newLocation = new Departement(name);
						break;
					  case 2:
						newLocation = new Ville(name);
						break;
					  default:
						newLocation = new Region(name);	
				  }
				  locationRepository.save(newLocation);				  
			  }else {
				  String ret = "Can't create Location : A location with same name found with id : " + foundLocation.getId() + "!";
				  System.err.println(ret);
				  return ret;
			  }
		  }
		  catch(Exception ex) {
			  System.err.println("Error at Location creation : " + ex.toString());
			  return "Couldn't create new Location";
		  }
		  return locationCreatedId;
	  }
	  
	  	/**
		 * GET /get  --> get location id with name.
		 */
		  @RequestMapping("/locations/get/{name}")
		  @ResponseBody
		  public String get(@PathVariable("name")String name) {
			  String locationId = "";
			  try {
				  Location foundLocation = locationRepository.findByName(name);
				  if(foundLocation != null) {
					  locationId = String.valueOf(foundLocation.getId());
				  }else {
					  String ret = "Can't found Location with name : " + name;
					  System.err.println(ret);
					  return ret;
				  }
			  }
			  catch(Exception ex) {
				  System.err.println("Error at Location creation : " + ex.toString());
				  return "Couldn't create new Location";
			  }
			  return locationId;
		  }
}
