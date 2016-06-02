package storage;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

import model.*;

import org.postgresql.Driver;

//The DBManager class is responsible of creating a connection between the SQL database and the Controller.
//It contains methods in connection with inserting and editing user, position, candidate and user data.

//In the database we have several tables: Users (admins and voters), Candidates, Election and Positions

public class DBManager implements IDBManager
{

	private Voter voter;
	private Admin admin;
	private Driver driver;
	private Connection connection;


	//Initializing the fields in the constructor.
	public DBManager() throws RemoteException
	{
		voter = null;
		admin = null;
		driver = new Driver();
		try
		{
			DriverManager.registerDriver(driver);
		}
		catch (SQLException e)
		{
		}
	}

	//The logIn method returns a User object (Voter or Admin) or null to the Controller,
	//which it will use to determine what action to take next.
	@Override
	public User logIn(String username, String password)
	{
		Boolean adminBoolean = false;	//It will store a boolean whether the searched user in the database is an admin or not.
		Boolean voted = true;	//It will store a boolean whether the voter in the database has already voted or not.
		Boolean active = false;		//It will store a boolean whether the election is active or not.

		try
		{
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
		}
		catch (SQLException e)
		{
		}

		// Returning data from the database.
		
		try
		{
			PreparedStatement statement = connection.prepareStatement(
					"SELECT * FROM Users WHERE name = ? AND password = ?");
			statement.setString(1, username);
			statement.setString(2, password);

			ResultSet result = statement.executeQuery();

			while (result.next())
			{
				adminBoolean = result.getBoolean("admin");
				voted = result.getBoolean("voted");
			}
			
			statement = connection.prepareStatement("SELECT * FROM election");
			
			result = statement.executeQuery();
			result.next();
			
			active = result.getBoolean("active");
		}
		catch (SQLException e)
		{
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
			}
		}
		
		// Creating a User object based on the state of the boolean objects.
		try
		{
			if (adminBoolean)
			{
				admin = new Admin(username, password);
				return admin;
			}
			else if (!voted && active)
			{
				voter = new Voter(username, password, voted);
				return voter;
			}
		}
		catch (Exception e)
		{
		}
		return null;
	}
	
	// The startElection method is responsible for rendering an election active, thus enabling voters to log in.

	@Override
	public void startElection()
	{
		try
		{
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
		}
		catch (SQLException e)
		{
		}

		try
		{
			PreparedStatement statement = connection
					.prepareStatement("UPDATE Election SET active = 'true'");

			statement.executeUpdate();

		}
		catch (SQLException e)
		{
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
			}
		}
	}
	
	// The startElection method is responsible for rendering an election inactive, thus forbidding voters to log in.

	@Override
	public void stopElection()
	{
		try
		{
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
			
			PreparedStatement statement = connection
					.prepareStatement("UPDATE election SET active = 'false'");

			statement.executeUpdate();
		}
		catch (SQLException e)
		{
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
			}
		}
	}

	// With this method we insert data into the Positions table (the name of a new position).
	@Override
	public void addPosition(Position position)
	{
		try
		{
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
		}
		catch (SQLException e)
		{
		}

		try
		{
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO Positions (name) VALUES(?)");

			statement.setString(1, position.getPositionName());
			statement.executeUpdate();
		}
		catch (SQLException e)
		{

		}
		finally
		{
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
			}
		}
	}

	// With this method we insert data into the Candidates table (the name and position of a new candidate, with their vote count set to 0).
	@Override
	public void addCandidate(String position, Candidate candidate)
	{
		try
		{
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
		}
		catch (SQLException e)
		{
		}

		try
		{
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO Candidates(name, position, voteCount) VALUES(?,?,?)");

			statement.setString(1, candidate.getName());
			statement.setString(2, position);
			statement.setInt(3, 0);

			statement.executeUpdate();
		}
		catch (SQLException e)
		{
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
			}
		}
	}

	// This method will update a user's password according to the user's username and the password that the user has chosen.
	@Override
	public void changePassword(String username, String password)
	{

		try
		{
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
		}
		catch (SQLException e)
		{
		}

		try
		{
			PreparedStatement statement = connection.prepareStatement(
					"UPDATE Users SET password = ? WHERE name = ?");

			statement.setString(1, password);
			statement.setString(2, username);
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
			}
		}
	}

	// This method will increment a certain candidate's vote count after a voter has voted.
	//The voter's boolean object "voted" will be set to true, thus forbidding them from logging in again.
	@Override
	public void vote(Candidate candidate)
	{

		try
		{
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
		}
		catch (SQLException e)
		{
		}

		try
		{
			PreparedStatement statement = connection.prepareStatement(
					"UPDATE Candidates SET voteCount = ? WHERE name = ?");

			candidate.giveVote();
			statement.setInt(1, candidate.getVotes());
			statement.setString(2, candidate.getName());
			statement.executeUpdate();

			statement = connection
					.prepareStatement("UPDATE Users SET voted = 'true' WHERE name = ?");

			statement.setString(1, voter.getName());
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
			}
		}
	}

	// With this method we return a candidate object (name, position and vote count) using data from the database.
	@Override
	public Candidate getCandidate(String name, String positionName)
	{
		Candidate candidate = null;
		try
		{
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
		}
		catch (SQLException e)
		{
		}

		try
		{
			PreparedStatement statement = connection.prepareStatement(
					"SELECT * FROM Candidates WHERE name = ? AND position = ?");
			statement.setString(1, name);

			statement.setString(2, positionName);
			ResultSet result = statement.executeQuery();

			while (result.next())
			{
				Position position = new Position(positionName);

				candidate = new Candidate(result.getString(1), position);
				candidate.setVotes(result.getInt(3));
			}
		}
		catch (SQLException e)
		{
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
			}
		}
		return candidate;
	}

	// With this method we return a candidate type arraylist containing every candidate from the database (name, position and vote count).
	@Override
	public ArrayList<Candidate> getCandidates(Position position)
	{
		try
		{
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
		}
		catch (SQLException e)
		{
		}

		ArrayList<Candidate> candidates = new ArrayList<>();

		try
		{
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM Candidates WHERE position = ?");

			statement.setString(1, position.getPositionName());
			ResultSet result = statement.executeQuery();

			while (result.next())
			{
				Candidate temp = new Candidate(result.getString(1), position);
				temp.setVotes(result.getInt(3));
				candidates.add(temp);
			}
		}
		catch (SQLException e)
		{
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
			}
		}

		return candidates;
	}

	// With this method we return a position type arraylist containing every position from the database (name of the position).
	@Override
	public ArrayList<Position> getPositions()
	{
		try
		{
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
		}
		catch (SQLException e)
		{
		}

		ArrayList<Position> positions = new ArrayList<>();

		try
		{
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM Positions");

			ResultSet result = statement.executeQuery();

			while (result.next())
			{

				Position temp = new Position(result.getString(1));
				positions.add(temp);
			}
		}
		catch (SQLException e)
		{
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
			}
		}
		return positions;
	}

	// The reset method deletes data from the Candidates and Positions table.
	//In the Users table the voters' boolean object "voted" is set to false, thus enabling the voters to vote again.
	@Override
	public void reset()
	{
		try
		{
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
		}
		catch (SQLException e)
		{
		}

		try
		{
			PreparedStatement statement1 = connection
					.prepareStatement("DELETE FROM Candidates");
			PreparedStatement statement2 = connection
					.prepareStatement("DELETE FROM Positions");
			PreparedStatement statement3 = connection
					.prepareStatement("UPDATE Users SET voted = 'false' WHERE voted = 'true'");

			statement1.executeUpdate();
			statement2.executeUpdate();
			statement3.executeUpdate();
		}
		catch (SQLException e)
		{
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
			}
		}
	}

	// This method deletes a position from the database based on the name of the position.
	@Override
	public void deletePosition(String position)
	{
		try
		{
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
		}
		catch (SQLException e)
		{
		}

		try
		{
			PreparedStatement statement = connection
					.prepareStatement("DELETE FROM Positions WHERE name = ?");

			statement.setString(1, position);
			statement.executeUpdate();

			statement = connection.prepareStatement("DELETE FROM Candidates WHERE position = ?");

			statement.setString(1, position);
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
			}
		}
	}

	// This method deletes a candidate from the database based on the name of the candidate and their position.
	@Override
	public void deleteCandidate(String candidate, String position)
	{

		try
		{
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
		}
		catch (SQLException e)
		{
		}

		try
		{
			PreparedStatement statement = connection.prepareStatement(
					"DELETE FROM Candidates WHERE name = ? AND position = ?");

			statement.setString(1, candidate);
			statement.setString(2, position);
			statement.executeUpdate();
		}
		catch (Exception e)
		{
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
			}
		}
	}

	// With this method we return the state of the election (whether if it's active or not).
	@Override
	public Election getElection() throws RemoteException
	{
		Election election = null;
		try
		{
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");

			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM Election");
			ResultSet result = statement.executeQuery();
			result.next();
			election = new Election(result.getBoolean("active"));
		}
		catch (SQLException e)
		{
		}
		return election;
	}
}