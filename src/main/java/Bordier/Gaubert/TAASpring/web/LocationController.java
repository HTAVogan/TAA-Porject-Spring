package Bordier.Gaubert.TAASpring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Bordier.Gaubert.TAASpring.Location;
import Bordier.Gaubert.TAASpring.Region;
import Bordier.Gaubert.TAASpring.Departement;
import Bordier.Gaubert.TAASpring.Ville;
import Bordier.Gaubert.TAASpring.repository.LocationRepository;
import io.swagger.annotations.Api;
@Api(value="Locationcontroller", produces=MediaType.APPLICATION_JSON_VALUE)
@Controller
public class LocationController {
	
	@Autowired
	private LocationRepository locationRepository;
	
	
	@RequestMapping(value="/locations/departement/create" ,method=RequestMethod.POST)
	@ResponseBody
	public Departement createD(Departement name) {
		try {
			Location foundedcity = locationRepository.findByName(name.getName());
			if(foundedcity == null) {
				
				locationRepository.save(name);
				return name;
			}
			else {
				System.out.println("Departement deja présent");
				return null;
			}
		}
		catch(Exception ex){
			System.out.println("Problem a la creation du Departement");
			return null;
		}
	}
	@RequestMapping(value="/locations/ville/create" ,method=RequestMethod.POST)
	@ResponseBody
	public Ville createV(Ville name) {
		try {
			Location foundedcity = locationRepository.findByName(name.getName());
			if(foundedcity == null) {
				locationRepository.save(name);
				return name;
			}
			else {
				System.out.println("Ville deja présente");
				return null;
			}
		}
		catch(Exception ex){
			System.out.println("Problem a la creation de la ville");
			return null;
		}
	}
	/**
	 * GET /create  --> Create a new location and save it in the database.
	 */
	  @RequestMapping(value="/locations/create/{name}/{type}" ,method=RequestMethod.POST)
	  @ResponseBody
	  public Location create(@PathVariable("name")String name, @PathVariable("type")String type) {
		  String locationCreatedId = "";
		  Location newLocation;
		  System.out.println("Creation of location");
		  try {
			  Location foundLocation = locationRepository.findByName(name);
			  if(foundLocation == null) {
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
				  return null;
			  }
		  }
		  catch(Exception ex) {
			  System.err.println("Error at Location creation : " + ex.toString());
			  return null;
		  }
		  return newLocation;
	  }
	  
	  	/**
		 * GET /get  --> get location id with name.
		 */
		  @RequestMapping(value="/locations/get/{name}", method=RequestMethod.GET)
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
