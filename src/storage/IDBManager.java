package storage;

import model.User;

public interface IDBManager {

	public User logIn(String username, String password);
	public void changePassword(String password);
	
	
}
