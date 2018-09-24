package Bordier.Gaubert.TAASpring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	 * GET /create  --> Create a new style music and save it in the database.
	 */
	  @RequestMapping("/events/create/{title}/{user_id}")
	  @ResponseBody
	  public String create(@PathVariable("title")String title, @PathVariable("user_id")String user_id) {
		  String eventsCreatedId = "";
		  try {
			  // Other Method to get User by Id
			  //User foundUser = new User();
			  //foundUser.setUser_id(user_id);

			  User foundUser = userRepository.findOne(Long.valueOf(user_id));
			  if(foundUser != null) {
				  Events newEvents = new Events();
				  newEvents.setTitle(title);
				  newEvents.setCreator(foundUser);
				  eventsRepository.save(newEvents);				  
			  }else {
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
}
