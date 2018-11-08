package Bordier.Gaubert.TAASpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Bordier.Gaubert.TAASpring.StyleMusic;

public interface StyleMusicRepository extends JpaRepository<StyleMusic, Long>{
	
	StyleMusic findByStyle(String style);
	
	StyleMusic findById(long id);
}
