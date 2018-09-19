package jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jpa.Entites.StyleMusic;

public interface StyleMusicRepository extends JpaRepository<StyleMusic, Long>{
	
	StyleMusic findByStyle(String style);
	
	StyleMusic findByStyleMusicId(long id);
}
