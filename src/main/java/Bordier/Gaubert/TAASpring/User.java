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
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User implements UserDetails,Serializable
{
	
	private long user_id;
	private String username;
	private String password;
	private String email;
	private List<Events> eventsFaved;
	
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
	public List<Events> getEventsFaved() {
		return eventsFaved;
	}
	public void setEventsFaved(List<Events> list) {
		this.eventsFaved=list;
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
	@Override
	@Transient 
	public java.util.Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	@Transient 
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	@Transient 
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	@Transient 
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	@Transient 
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}
