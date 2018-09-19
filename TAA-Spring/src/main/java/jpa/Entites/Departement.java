package jpa.Entites;

import javax.persistence.Id;

public class Departement extends Location{
	public Departement() {
		
	}
	
	public Departement(String name)
	{
		super(name);
	}
	/*
	
	private long id;
	@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String name;
	public Departement(){
		
	}
	public Departement(long id,String name) {
		this.id=id;
		this.name=name;
	}*/
}
