package Bordier.Gaubert.TAASpring.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Bordier.Gaubert.TAASpring.Events;
import Bordier.Gaubert.TAASpring.StyleMusic;
import Bordier.Gaubert.TAASpring.User;
import Bordier.Gaubert.TAASpring.repository.StyleMusicRepository;
import Bordier.Gaubert.TAASpring.repository.UserRepository;
import io.swagger.annotations.Api;



@Controller
@Api(value="Usercontroller", produces=MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	 // Private fields

	  @Autowired
	  private UserRepository userRepository;

	  @Autowired
	  private StyleMusicRepository styleMusicRepository;
	

  @RequestMapping( value ="/user/create",method=RequestMethod.POST)
  @ResponseBody
  public User create(@RequestBody User user) {
	User foundUser = userRepository.getOne(user.getUser_id());
	System.out.println(foundUser.getUser_id() == 0 ? "no foundUser" : "Userfound : " + user.getUsername());
	if(foundUser.getUser_id() == 0) {
		try {
	      userRepository.save(user);
	      System.out.println("User create correctly : " + user.getUsername());
	    }
	    catch (Exception ex) {
	    	System.out.println("error encounter while saving new user to db : " + ex.toString());
	    	return null;
	    }
  	}
	else {
		System.out.println("User with username already exist in db ! plz retry with an other name");
	}
    return user;
  }
  @RequestMapping(value="/user", method=RequestMethod.GET,produces="application/json")
  @ResponseBody
  public List<User> allUser(){
	 
	 return userRepository.findAll();
  }
  
  @RequestMapping(value="/user/username/{username}")
  @ResponseBody
  public User getUserByUsername(@PathVariable("username") String username) {
	  return userRepository.findByUsername(username);	
  }
  
  
  public boolean userExistWithId(long id) {
	  boolean ret = false;
	  try{
		  User foundUser = userRepository.getOne(id);
		  if(foundUser != null) {
			  System.out.println("User with id " + id + " does exist : name is '" + foundUser.getUsername()+"'");
			  ret = true;
		  }
		  else {
			  System.out.println("User with id " + id + " does NOT exist !");
		  }
	  }
	  catch (Exception ex) {
		  System.err.println("Error while checking user with id : " + id + " exist or not : " + ex.toString());
	  }
	  return ret;
	  
  }
  @RequestMapping(value="/user/addEvent", method=RequestMethod.PUT)
  public @ResponseBody User addNewEvent(@RequestBody User u,@RequestBody Events e) {
	  List<Events>es =u.getEventsFaved();
	  es.add(e);
	  u.setEventsFaved(es);
	  userRepository.save(u);
	  return u;
  }
  
  
  
  @RequestMapping(value="/user/{id}",method=RequestMethod.GET)
  public @ResponseBody User getById(@PathVariable("id") String id) {
	  long ID=(long) Long.valueOf(id);
	  try {
		  return userRepository.getOne(ID);
	  }catch(Exception ex ) {
		  return null;
	  }
  }

  @RequestMapping(value= "/user/create/{username}/{password}", method= RequestMethod.POST)
  public @ResponseBody User create(@PathVariable("username")String username, @PathVariable("password")String password) {
    String userId = "";	
    User user = new User(username, password);
   try {
      
      userRepository.save(user);
      userId = String.valueOf(user.getUser_id());
    }
    catch (Exception ex) {
      return null;
    }
    return user;
  }
  
  /**
   * GET /delete  --> Delete the user having the passed id.
   */
  @RequestMapping(value= "/user/delete/{id}",method=RequestMethod.DELETE)
  @ResponseBody
  public String delete(@PathVariable("id")String id) {
    try {
      User user = new User(Long.valueOf(id));
      userRepository.delete(user);
    }
    catch (Exception ex) {
      return "Error deleting the user:" + ex.toString();
    }
    return "User succesfully deleted!";
  }
  
  /**
   * GET /get-by-email  --> Return the id for the user having the passed
   * email.
   */
  @RequestMapping(value ="/user/get-by-email/{email}", method=RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<List<User>> getByEmail(@PathVariable("email")String email) {
    String userId = "";
    try {
      List<User> foundUsers = userRepository.findByEmail(email);
      if(foundUsers.isEmpty()) {
    	  return new ResponseEntity<List<User>>(foundUsers, HttpStatus.NO_CONTENT);
      }
      else {
    	  return new ResponseEntity<List<User>>(foundUsers, HttpStatus.ACCEPTED);
      }
    }
    catch (Exception ex) {
      return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
    }
  }
  
  
  
  /**
   * GET /update  --> Update the email and the name for the user in the 
   * database having the passed id.
   */
  @RequestMapping(value="/addToFav/{id}/{newStyle}", method=RequestMethod.PUT)
  @ResponseBody
  public User AddNewStyleMusic(@PathVariable("id")String id, @PathVariable("newStyle")String newStyle) {
    try {
      User user = userRepository.getOne(Long.valueOf(id));
      List<StyleMusic> favList = user.getFavoriteStyles();
      StyleMusic foundMusic = styleMusicRepository.findByStyle(newStyle);
      favList.add(foundMusic);
      user.setFavoriteStyles(favList);
      userRepository.save(user);
      user = userRepository.getOne(Long.valueOf(id));
      return user;
    }
    catch (Exception ex) {
      return null;
    }
    
  }
  
}