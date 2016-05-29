package storage;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;
import model.*;
import org.postgresql.Driver;

public class DBManager implements IDBManager{

	private Voter voter;
	private Admin admin;
	private Driver driver;
	private Connection connection;

	public DBManager() throws SQLException {
		voter = null;
		admin = null;
		driver = new Driver();
		DriverManager.registerDriver(driver);
	}

	public User logIn(String username, String password) throws SQLException {

		connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "password");

		admin = null;
		voter = null;

		try {
			PreparedStatement statement = 
					connection.prepareStatement("SELECT * FROM Users WHERE name = ? AND password = ?");
			statement.setString(1, username);
			statement.setString(2, password);

			ResultSet result= statement.executeQuery();

			while (result.next()) {

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

	@Override
	public void startElection() throws SQLException {
		connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "password");

		try {
			PreparedStatement statement = 
					connection.prepareStatement("UPDATE Election SET active = true WHERE active = false");

			statement.executeUpdate();

		} finally {
			connection.close();
		}
	}

	@Override
	public void stopElection() throws SQLException {
		connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "password");

		try {
			PreparedStatement statement = 
					connection.prepareStatement("UPDATE Election SET active = false WHERE active = true");

			statement.executeUpdate();

		} finally {
			connection.close();
		}
	}

	@Override
	public void addPosition(Position position) throws SQLException {
		connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "password");

		try{
			PreparedStatement statement = connection.prepareStatement("INSERT INTO Positions (name) VALUES(?)");

			statement.setString(1, position.getPositionName());

		} finally {
			connection.close();
		}

	}

	@Override
	public void addCandidate(Position position, Candidate candidate) throws SQLException {
		connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "password");

		try{
			PreparedStatement statement = connection.prepareStatement("INSERT INTO Candidates(name, position, voteCount, ID, description) VALUES(?,?,?,?,?)");

			statement.setString(1, candidate.getName());
			statement.setString(2, position.getPositionName());
			statement.setInt(3, 0);
			statement.setInt(4, candidate.getID());
			statement.setString(5, candidate.getDescription());

		} finally {
			connection.close();
		}
	}

	@Override
	public void changePassword(String username, String password) throws SQLException {

		connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "password");

		try {
			PreparedStatement statement = 
					connection.prepareStatement("UPDATE Users SET password = ? WHERE username = ?");

			statement.setString(1, password);
			statement.setString(2, username);
			statement.executeUpdate();

		} finally {
			connection.close();
		}

	}

	public void vote(Candidate candidate) throws SQLException {

		connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "password");

		try {
			PreparedStatement statement = connection.prepareStatement("UPDATE Candidate SET voteCount = ? WHERE name = ?");

			candidate.giveVote();
			statement.setInt(1, candidate.getVotes());
			statement.setString(2, candidate.getName());
			statement.executeUpdate();

		} finally {
			connection.close();
		}
	}

	public Candidate getCandidate(String name, Position position) throws SQLException {

		Candidate candidate = null;
		connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "password");

		try{
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Candidates WHERE name = ? AND position = ?");
			statement.setString(1, name);
			statement.setString(2, position.getPositionName());
			ResultSet result= statement.executeQuery();
			
			while (result.next()) {
				candidate = new Candidate(result.getString(1), position, result.getInt(4), result.getString(5));
				candidate.setVotes(result.getInt(3));
			}
			
		} finally {
			
			connection.close();
		}
		return candidate;
	}

	@Override
	public ArrayList<Candidate> getCandidates(Position position) throws SQLException {

		ArrayList<Candidate> candidates = new ArrayList<>();

		try {
			PreparedStatement statement = 
					connection.prepareStatement("SELECT * FROM Candidates WHERE position = ?");

			statement.setString(1, position.getPositionName());
			ResultSet result= statement.executeQuery();

			while (result.next()) {

				Candidate temp = new Candidate(result.getString(1), position, result.getInt(4), result.getString(5));
				temp.setVotes(result.getInt(3));
				candidates.add(temp);

			}

		} finally {
			connection.close();
		}

		return candidates;

	}

	public ArrayList<Position> getPositions() throws SQLException {

		ArrayList<Position> positions = new ArrayList<>();

		try {
			PreparedStatement statement = 
					connection.prepareStatement("SELECT * FROM Positions");

			ResultSet result= statement.executeQuery();

			while (result.next()) {

				Position temp = new Position(result.getString(1));
				positions.add(temp);

			}

		} finally {
			connection.close();
		}

		return positions;

	}

	public void reset() throws SQLException{

		connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "password");

		try{
			PreparedStatement statement1 = connection.prepareStatement("DROP TABLE Users");
			PreparedStatement statement2 = connection.prepareStatement("DROP TABLE Candidate");
			PreparedStatement statement3 = connection.prepareStatement("DROP TABLE Election");
			PreparedStatement statement4 = connection.prepareStatement("DROP TABLE Position");

			statement1.executeUpdate();
			statement2.executeUpdate();
			statement3.executeUpdate();
			statement4.executeUpdate();

		} finally {
			connection.close();
		}
	}
	
	public void deletePosition(String position) throws SQLException {
		
		connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "password");

		try {
			PreparedStatement statement = connection.prepareStatement("DELETE FROM Positions WHERE name = ?");
		
			statement.setString(1, position);
			statement.executeUpdate();
		
		} finally {
			connection.close();
		}
		
	}
	
	public void deleteCandidate(String candidate, String position) throws SQLException {
		
		connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "password");

		try {
			PreparedStatement statement = connection.prepareStatement("DELETE FROM Candidates WHERE name = ? AND position = ?");
		
			statement.setString(1, candidate);
			statement.setString(2, position);
			statement.executeUpdate();
		
		} finally {
			connection.close();
		}
		
	}

   @Override
   public Election getElection() throws SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

}
