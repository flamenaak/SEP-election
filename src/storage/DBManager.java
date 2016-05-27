package storage;

import java.sql.*;

import model.*;

import org.postgresql.Driver;

public class DBManager implements IDBManager{

	private String username;
	private String password;
	private Voter voter;
	private Admin admin;
	
	public User logIn(String username, String password) throws SQLException{
		
		admin = null;
		voter = null;
		
		Driver driver = new Driver();
		DriverManager.registerDriver(new org.postgresql.Driver());
		Connection connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
		try {
			PreparedStatement statement = 
					connection.prepareStatement("SELECT * FROM Users WHERE name = ? AND password = ?");
			statement.setString(1, username);
			statement.setString(2, password);

			ResultSet result= statement.executeQuery();
			
			while (result.next()) {
				this.username = result.getString("name");
				this.password = result.getString("password");
				int Id = result.getInt("ID");
				Boolean adminBoolean = result.getBoolean("admin");
				Boolean voted = result.getBoolean("voted");
				if (adminBoolean)
					admin = new Admin(username, password);
				else {
					voter = new Voter(username, password);
					if (voted)
						voter.vote();
				}
			}
			
		} finally {
			connection.close();
		}
		if (admin == null)
			return voter;
		if (voter == null)
			return admin;
		return null;
	}

   public Election getElection()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public void startElection()
   {
      // TODO Auto-generated method stub
      
   }

   public void stopElection()
   {
      // TODO Auto-generated method stub
      
   }

   public void addPosition(Position position)
   {
      // TODO Auto-generated method stub
      
   }

   public void addCandidate(Position position, Candidate candidate)
   {
      // TODO Auto-generated method stub
      
   }
	
	
	
}
