package Bordier.Gaubert.TAASpring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Bordier.Gaubert.TAASpring.StyleMusic;
import Bordier.Gaubert.TAASpring.User;
import Bordier.Gaubert.TAASpring.repository.StyleMusicRepository;

@Controller
public class StyleMusicController {
	
	@Autowired
	private StyleMusicRepository styleMusicRepository;
	
	/**
	 * GET /create  --> Create a new style music and save it in the database.
	 */
	  @RequestMapping("/music/create")
	  @ResponseBody
	  public String create(String style) {
	    String styleMusicId = "";
	    try {
	      StyleMusic newMusic = styleMusicRepository.findByStyle(style);
	      if(newMusic == null) {
			  newMusic = new StyleMusic(style);
			  styleMusicRepository.save(newMusic);
			  styleMusicId = String.valueOf(newMusic.getStyleMusic_id());
	      }
	      else {
	    	  System.err.println("Can't create new Style music : already exist with id : " + newMusic.getStyleMusic_id());
	      }
	    }
	    catch (Exception ex) {
	      return "Error creating the style Music: " + ex.toString();
	    }
	    return "User succesfully created with id = " + styleMusicId;
	  }
	  
	  /**
		 * GET /get  --> get style music by id
		 */
		  @RequestMapping("/music/get")
		  @ResponseBody
		  public String getById(long id) {
			  String styleMusicId =  "", styleMusicStyle = "";
			  try {
				  StyleMusic music = styleMusicRepository.findByStyleMusicId(id);
				  styleMusicId = String.valueOf(music.getStyleMusic_id());
				  styleMusicStyle = music.getStyle();
				  
			  }catch(Exception ex) {
				  return "Error : No style music with id : " + id + " | " + ex.toString();
			  }
			  return "Style music => id : " + styleMusicId + " | style : " + styleMusicStyle;
		  }
}
