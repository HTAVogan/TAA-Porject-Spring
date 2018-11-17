package Bordier.Gaubert.TAASpring.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Bordier.Gaubert.TAASpring.Events;
import Bordier.Gaubert.TAASpring.Location;
import Bordier.Gaubert.TAASpring.StyleMusic;
import Bordier.Gaubert.TAASpring.User;
import Bordier.Gaubert.TAASpring.repository.EventsRepository;
import Bordier.Gaubert.TAASpring.repository.LocationRepository;
import Bordier.Gaubert.TAASpring.repository.StyleMusicRepository;
import Bordier.Gaubert.TAASpring.repository.UserRepository;
import io.swagger.annotations.Api;
@Api(value="eventcontroller", produces=MediaType.APPLICATION_JSON_VALUE)
@Controller
public class EventsController {

	@Autowired
	private EventsRepository eventsRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private StyleMusicRepository styleMusicRepository;
	/**
	 * POST /create  --> Create a new style music and save it in the database.
	 */
	
	@RequestMapping(value="/events/create",method=RequestMethod.POST)
	@ResponseBody
	public Events create(@RequestBody Events event) {
		String eventsCreatedId = "";
		long user_id = event.getCreator().getUser_id();
		String title= event.getTitle();
		
		try {
			List<Location> ll = new ArrayList<Location>();
			for(StyleMusic sm : event.getMusicstyles()) {
				//System.out.println("ICCCCIIIIIII Style"+ sm.getStyle()+ " / " + styleMusicRepository.findByStyle(sm.getStyle()) == null);
				if(styleMusicRepository.findByStyle(sm.getStyle()) == null) {
					styleMusicRepository.save(sm);
				}
			}
			for(Location l : event.getLocations()) {
				System.out.println("ICIIIIII LOCATION ");
				if(locationRepository.findByName(l.getName())==null) {
					locationRepository.save(l);
					Location current = locationRepository.findByName(l.getName());
					ll.add(current);
				}
				else {
					Location current = locationRepository.findByName(l.getName());
					ll.add(current);
				}
			}
			event.setLocations(ll);
			User foundUser = userRepository.findById(user_id);
			if(foundUser != null) {
				eventsRepository.save(event);	
				eventsCreatedId = Long.toString(event.getId());
				return event;
			}
			else {
				String ret = "Can't create Event : No User found with id : " + user_id + "!";
				System.err.println(ret);
				return null;
			}
		}
		catch(Exception ex) {
			System.err.println("Error at Events creation : " + ex.toString());
			return null;
		}
		
	}
	
	@RequestMapping(value="/event/update", method=RequestMethod.POST,headers= "content-type=multipart/form-data", produces="application/json")
	@ResponseBody
	public Events addImgToEvent(@RequestBody Events event,@RequestBody byte[] img) {
		try {
			event.setImg(img);
			eventsRepository.save(event);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error while updating img of event " + event.getId() + " : " + e.toString());
			return null;
		}
		return event;
	}

	@RequestMapping(value="/events", method=RequestMethod.GET,produces="application/json")
	@ResponseBody
	public List<Events> getAll(){
		return eventsRepository.findAll();
	}

	@RequestMapping(value="/event/{id}", method=RequestMethod.GET, produces ="application/json")
	@ResponseBody
	public Events getOne(@PathVariable("id") String id) {
	    try {
	        long ID=(long) Long.valueOf(id);
	       return  eventsRepository.findById(ID);
	       // eventId = String.valueOf(event.getId());
	       /* eventName = event.getTitle();
	        return "{\n id : " + eventId + ",\n" + "title : " + eventName +"\n}"; 
	        */
	      }
	     catch (Exception ex) {
	       return null;
	      }
	}

}
