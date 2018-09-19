package jpa.Entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class StyleMusic {
	private long styleMusic_id;
	public String getStyle() {
		return style;
	}
	@Id
	@GeneratedValue
	public long getStyleMusic_id() {
		return styleMusic_id;
	}
	public void setStyleMusic_id(long styleMusic_id) {
		this.styleMusic_id = styleMusic_id;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	private String style;
	public StyleMusic() {
		
	}
	public StyleMusic(String style) {
		this.style=style;
	}
}
