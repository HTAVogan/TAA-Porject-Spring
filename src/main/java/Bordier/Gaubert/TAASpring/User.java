package Bordier.Gaubert.TAASpring;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User {
	
	private long user_id;
	private String username;
	private String password;
	private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private List<StyleMusic> FavoriteStyles ;
	@Id
	@GeneratedValue
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	//@Column(nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@ManyToMany
	@JoinTable(name="FavoriteStyles",
		joinColumns = {@JoinColumn ( name =  "user_id")},
		inverseJoinColumns = { @JoinColumn(name = "syleMusic_id") }
	)
	public List<StyleMusic> getFavoriteStyles() {
		return FavoriteStyles;
	}
	public void setFavoriteStyles(List<StyleMusic> favList) {
		FavoriteStyles = favList;
	}
	
	public User() {
		
	}
	
	public User(long id) {
		this.user_id = id;
	}
	
	public User(String username) {
		this.username = username;
		this.FavoriteStyles = new ArrayList<StyleMusic>();
	}
	
	public User(String username,String password,List<StyleMusic> lm) {
		this.username = username;
		this.FavoriteStyles = lm;
		this.password = password;
	}
	
	public User(String username, String password) {
		this.username = username;
		this.FavoriteStyles = new ArrayList<StyleMusic>();
		this.password = password;
	}
	
	public User(String username, String password, String email) {
		this.username = username;
		this.FavoriteStyles = new ArrayList<StyleMusic>();
		this.password = password;
		this.email = email;
	}
}