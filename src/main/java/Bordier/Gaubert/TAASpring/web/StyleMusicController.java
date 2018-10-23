package Bordier.Gaubert.TAASpring.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	  @RequestMapping(value="/music/create", method=RequestMethod.POST)
	  @ResponseBody
	  public String create(@RequestBody String style) {
	    String styleMusicId = "";
	    try {
	      StyleMusic newMusic = styleMusicRepository.findByStyle(style);
	      if(newMusic == null) {
			  newMusic = new StyleMusic(style);
			  styleMusicRepository.save(newMusic);
			  styleMusicId = String.valueOf(newMusic.getStyleMusic_id());
			  return "Music created";
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
		  @RequestMapping("/music/get/{id}")
		  @ResponseBody
		  public String getById(@PathVariable("id")String id) {
			  String styleMusicId =  "", styleMusicStyle = "";
			  long idl = (long) Long.valueOf(id);
			  try {
				  StyleMusic music = styleMusicRepository.findById(idl);
				  styleMusicId = String.valueOf(music.getStyleMusic_id());
				  styleMusicStyle = music.getStyle();
				  
			  }catch(Exception ex) {
				  return "Error : No style music with id : " + id + " | " + ex.toString();
			  }
			  return "Style music => id : " + styleMusicId + " | style : " + styleMusicStyle;
		  }
		  
		  /**
			 * GET /get  --> get style music by id
			 */
			  @RequestMapping(value="/musics/",method=RequestMethod.GET)
			  @ResponseBody
		  public List<StyleMusic> allMusic(){
			  return styleMusicRepository.findAll();
		  }
			  
			  @RequestMapping(value= "/music/delete/{id}",method=RequestMethod.DELETE)
			  @ResponseBody
			  public String deleteStyle(@PathVariable("id")String id) {
				  long idl = (long) Long.valueOf(id);
				  try {
					  StyleMusic music = styleMusicRepository.findById(idl);
					  styleMusicRepository.delete(music);
				  }
				  catch(Exception ex) {
					  return "Error : No style music with id : " + id + " | " + ex.toString();
				  }
				  return "Delete has been done";
			  }
}
