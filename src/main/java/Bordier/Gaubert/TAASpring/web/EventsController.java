package Bordier.Gaubert.TAASpring.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Bordier.Gaubert.TAASpring.Events;
import Bordier.Gaubert.TAASpring.User;
import Bordier.Gaubert.TAASpring.repository.EventsRepository;
import Bordier.Gaubert.TAASpring.repository.UserRepository;

@Controller
public class EventsController {

	@Autowired
	private EventsRepository eventsRepository;

	@Autowired
	private UserRepository userRepository;

	/**
	 * POST /create  --> Create a new style music and save it in the database.
	 */
	@RequestMapping(value="/events/create",method=RequestMethod.POST)
	@ResponseBody
	public String create(@RequestBody Events event) {
		String eventsCreatedId = "";
		long user_id = event.getCreator().getUser_id();
		String title= event.getTitle();
		System.out.println(title + user_id);
		try {
			// Other Method to get User by Id
			//User foundUser = new User();
			//foundUser.setUser_id(user_id);
			User foundUser = userRepository.getOne(Long.valueOf(user_id));

			if(foundUser != null) {
				eventsRepository.save(event);	
				eventsCreatedId = Long.toString(event.getId());
			}
			else {
				String ret = "Can't create Event : No User found with id : " + user_id + "!";
				System.err.println(ret);
				return ret;
			}
		}
		catch(Exception ex) {
			System.err.println("Error at Events creation : " + ex.toString());
			return "Couldn't create new event";
		}
		return eventsCreatedId;
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
	        String eventId =""; String eventName = "";
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
