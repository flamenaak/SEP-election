package model;

public class User
{
	private String name;
	protected String password;
	
	public User (String name, String password){
		this.name=name;
		this.password=password;
	}
	
	public String getPassword(){
		return password;
	}
	
	
}
