package model;

public class Voter extends User
{
	private boolean voted;
	
	public Voter(String name, String password){
		super(name, password);
		voted=false;
	}
	
	public void vote(){
		voted=true;
	}
}
