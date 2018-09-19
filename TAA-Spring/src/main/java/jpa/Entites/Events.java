package jpa.Entites;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.mysql.jdbc.Blob;

@Entity
public class Events {
	private long id;
	private String title;
	private User creator;
	private String url;
	private List<Location> locations;
	private Date date_start;
	private Date date_end;
	private byte[] img;
	
	//private Departement departement;
	//private Ville ville;
	
	
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@OneToOne
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	/*
	public Departement getDepartement() {
		return departement;
	}
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	public Ville getVille() {
		return ville;
	}
	public void setVille(Ville ville) {
		this.ville = ville;
	}
	*/
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	public Events(String name, User u, Date start,Date end,Location l, String url) {
		this.title = name;
		this.creator = u;
		this.url = url;
		this.date_start = start;
		this.date_end = end;
		this.locations = new ArrayList<Location>();
		this.locations.add(l);
	}
	public Events(String name, User u, Date start, Date end, List<Location> ls, String url) {
		this.title = name;
		this.creator = u;
		this.url = url;
		this.date_start = start;
		this.date_end = end;
		this.locations = ls;
	}
	public Events(String name, String url) {
		this.title = name;
		this.creator = null;
		this.url = url;
		this.date_start = null;
		this.date_end = null;
		this.locations = new ArrayList<Location>();
	}
	
	public Events(Date start, Date end) {
		date_start=start;
		date_end=end;
	}
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDate_start() {
		return date_start;
	}
	public void setDate_start(Date date_start) {
		this.date_start = date_start;
	}
	public Date getDate_end() {
		return date_end;
	}
	public void setDate_end(Date date_end) {
		this.date_end = date_end;
	}
	public Events() {
		
	}
	@ManyToMany
	public List<Location> getLocations() {
		return locations;
	}
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
}
