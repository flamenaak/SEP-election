package model;

public class Admin extends User
{
  public Admin(String name, String password){
	  super(name, password);
  }

  public void changePassword(String password){
	  this.password=password;
  }
  
  public void setPassword(String pass){
	  password=pass;
  }
}
