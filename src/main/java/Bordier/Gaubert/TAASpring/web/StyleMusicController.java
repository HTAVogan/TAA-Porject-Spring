package Bordier.Gaubert.TAASpring.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Bordier.Gaubert.TAASpring.StyleMusic;
import Bordier.Gaubert.TAASpring.User;
import Bordier.Gaubert.TAASpring.repository.StyleMusicRepository;
import io.swagger.annotations.Api;

@Controller
@Api(value="StyleMusiccontroller", produces=MediaType.APPLICATION_JSON_VALUE)
public class StyleMusicController {

	@Autowired
	private StyleMusicRepository styleMusicRepository;

	/**
	 * GET /create  --> Create a new style music and save it in the database.
	 */
	@RequestMapping(value="/music/create", method=RequestMethod.POST)
	@ResponseBody
	public StyleMusic create(@RequestBody String style) {
		StyleMusic newMusic = new StyleMusic("ALREADY_EXIST");
		try {
			StyleMusic foundMusic = styleMusicRepository.findByStyle(style);
			System.out.println("foundMusic : " + foundMusic == null ? "null !" : foundMusic.toString());
			if(foundMusic == null) {
				newMusic = new StyleMusic(style);
				styleMusicRepository.save(newMusic);
			}
			else {
				System.err.println("Can't create new Style music : already exist with id : " + newMusic.getStyleMusic_id());
			}
		}
		catch (Exception ex) {
			return new StyleMusic("ERROR");
		}
		return newMusic;
	}

	/**
	 * GET /get  --> get style music by id
	 */
	@RequestMapping(value="/music/get/{id}", method=RequestMethod.GET)
	@ResponseBody
	public StyleMusic getById(@PathVariable("id")String id) {
		String styleMusicId =  "", styleMusicStyle = "";
		long idl = (long) Long.valueOf(id);

		return  styleMusicRepository.findById(idl);

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
