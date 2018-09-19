package jpa.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jpa.Entites.StyleMusic;
import jpa.Entites.User;
import jpa.repository.StyleMusicRepository;
import jpa.repository.UserRepository;



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
  @RequestMapping("/user/create")
  @ResponseBody
  public String create(String username, String password, String email) {
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
  
  /**
   * GET /create  --> Create a new user with no email set and save it in the database.
   */
  @RequestMapping("/user/create")
  @ResponseBody
  public String create(String username, String password) {
    String userId = "";
    try {
      User user = new User(username, password);
      userRepository.save(user);
      userId = String.valueOf(user.getUser_id());
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "User succesfully created with id = " + userId;
  }
  
  /**
   * GET /delete  --> Delete the user having the passed id.
   */
  @RequestMapping("/user/delete")
  @ResponseBody
  public String delete(long id) {
    try {
      User user = new User(id);
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
  @RequestMapping("/user/get-by-email")
  @ResponseBody
  public String getByEmail(String email) {
    String userId = "";
    try {
      User user = userRepository.findByEmail(email);
      userId = String.valueOf(user.getUser_id());
    }
    catch (Exception ex) {
      return "User not found";
    }
    return "The user id is: " + userId;
  }
  
  
  
  /**
   * GET /update  --> Update the email and the name for the user in the 
   * database having the passed id.
   */
  @RequestMapping("/addToFav")
  @ResponseBody
  public String AddNewStyleMusic(long id, String newStyle) {
    try {
      User user = userRepository.findOne(id);
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