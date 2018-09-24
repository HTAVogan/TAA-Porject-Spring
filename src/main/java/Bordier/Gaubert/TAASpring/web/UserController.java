package Bordier.Gaubert.TAASpring.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Bordier.Gaubert.TAASpring.StyleMusic;
import Bordier.Gaubert.TAASpring.User;
import Bordier.Gaubert.TAASpring.repository.StyleMusicRepository;
import Bordier.Gaubert.TAASpring.repository.UserRepository;



@Controller
public class UserController {

	 // Private fields

	  @Autowired
	  private UserRepository userRepository;

	  @Autowired
	  private StyleMusicRepository styleMusicRepository;
	
  /**
   * GET /create  --> Create a new user and save it in the database.
   */
  @GetMapping("/user/create/{username}/{password}/{email}")
  
  public @ResponseBody String create(@PathVariable("username")String username, @PathVariable("password")String password, @PathVariable("email")String email) {
    String userId = "";
    try {
      User user = new User(username, password, email);
      userRepository.save(user);
      userId = String.valueOf(user.getUser_id());
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "User succesfully created with id = " + userId;
  }
  @RequestMapping(value="/user", method=RequestMethod.GET,produces="application/json")
  @ResponseBody
  public List<User> allUser(){
	 
	 return userRepository.findAll();
  }
  
  public boolean userExistWithId(long id) {
	  boolean ret = false;
	  try{
		  User foundUser = userRepository.findOne(id);
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
  
  /**
   * GET /create  --> Create a new user with no email set and save it in the database.
   */
  @GetMapping("/user/create/{username}/{password}")
  public @ResponseBody String create(@PathVariable("username")String username, @PathVariable("password")String password) {
    String userId = "";	
    return "TA MERE EN SHORT";
  }
   /* try {
      User user = new User(username, password);
      userRepository.save(user);
      userId = String.valueOf(user.getUser_id());
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "User succesfully created with id = " + userId;
  }**/
  
  /**
   * GET /delete  --> Delete the user having the passed id.
   */
  @RequestMapping("/user/delete/{id}")
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
  @RequestMapping("/user/get-by-email/{email}")
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
      //userId = String.valueOf(user.getUser_id());
    }
    catch (Exception ex) {
      return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
    }
  }
  
  
  
  /**
   * GET /update  --> Update the email and the name for the user in the 
   * database having the passed id.
   */
  @RequestMapping("/addToFav/{id}/{newStyle}")
  @ResponseBody
  public String AddNewStyleMusic(@PathVariable("id")String id, @PathVariable("newStyle")String newStyle) {
    try {
      User user = userRepository.findOne(Long.valueOf(id));
      List<StyleMusic> favList = user.getFavoriteStyles();
      StyleMusic foundMusic = styleMusicRepository.findByStyle(newStyle);
      favList.add(foundMusic);
      user.setFavoriteStyles(favList);
      userRepository.save(user);
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "User succesfully updated!";
  }
  
}