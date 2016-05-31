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

   public DBManager() throws RemoteException {
      voter = null;
      admin = null;
      driver = new Driver();
      try
      {
         DriverManager.registerDriver(driver);
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public User logIn(String username, String password){

      try
      {
         connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

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
            }
         }

      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      finally {
         try
         {
            connection.close();
         }
         catch (SQLException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }

      if (admin == null)
         return voter;
      if (voter == null)
         return admin;
      return null;
   }

   @Override
   public void startElection(){
      try
      {
         connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      try {
         PreparedStatement statement = 
               connection.prepareStatement("UPDATE Election SET active = 'true'");

         statement.executeUpdate();

      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      finally {
         try
         {
            connection.close();
         }
         catch (SQLException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }

   @Override
   public void stopElection(){
      try
      {
         connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
         PreparedStatement statement = 
               connection.prepareStatement("UPDATE election SET active = 'false'");

         statement.executeUpdate();
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      finally {
         try
         {
            connection.close();
         }
         catch (SQLException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }

   @Override
   public void addPosition(Position position){
      try
      {
         connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      try{
         PreparedStatement statement = connection.prepareStatement("INSERT INTO Positions (name) VALUES(?)");

         statement.setString(1, position.getPositionName());
         statement.executeUpdate();

      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      finally {
         try
         {
            connection.close();
         }
         catch (SQLException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }

   }

	public void addCandidate(String position, Candidate candidate){
		try
      {
         connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      try{
         PreparedStatement statement = connection.prepareStatement("INSERT INTO Candidates(name, position, voteCount, ID, description) VALUES(?,?,?,?,?)");

			statement.setString(1, candidate.getName());
			statement.setString(2, position);
			statement.setInt(3, 0);
			statement.setInt(4, candidate.getID());
			statement.setString(5, candidate.getDescription());
			statement.executeUpdate();
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      finally {
         try
         {
            connection.close();
         }
         catch (SQLException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }

   @Override
   public void changePassword(String username, String password){

      try
      {
         connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      try {
         PreparedStatement statement = 
               connection.prepareStatement("UPDATE Users SET password = ? WHERE name = ?");

         statement.setString(1, password);
         statement.setString(2, username);
         statement.executeUpdate();

      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      finally {
         try
         {
            connection.close();
         }
         catch (SQLException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }

   }

   public void vote(Candidate candidate){

      try
      {
         connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      try {
         PreparedStatement statement = connection.prepareStatement("UPDATE Candidates SET voteCount = ? WHERE name = ?");

         candidate.giveVote();
         statement.setInt(1, candidate.getVotes());
         statement.setString(2, candidate.getName());
         statement.executeUpdate();
         
         statement = connection.prepareStatement("UPDATE Users SET voted = ? WHERE name = ?");
         statement.setBoolean(1, true);
         statement.setString(2, voter.getName());
         statement.executeUpdate();
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      finally {
         try
         {
            connection.close();
         }
         catch (SQLException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }

	public Candidate getCandidate(String name, String positionName){
		Candidate candidate = null;
		try
      {
         connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

		try{
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Candidates WHERE name = ? AND position = ?");
			statement.setString(1, name);

			statement.setString(2, positionName);
			ResultSet result= statement.executeQuery();
			
			while (result.next()) {
				Position position = new Position(positionName);
			
				candidate = new Candidate(result.getString(1), position, result.getInt(4), result.getString(5));
				candidate.setVotes(result.getInt(3));
			}
		}
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
		finally {
			
			try
         {
            connection.close();
         }
         catch (SQLException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
		}
		return candidate;
	}
	
	@Override
   public ArrayList<Candidate> getCandidates(Position position){
      try
      {
         connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

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
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      finally {
         try
         {
            connection.close();
         }
         catch (SQLException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }

      return candidates;

   }

   public ArrayList<Position> getPositions(){
      try
      {
         connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      ArrayList<Position> positions = new ArrayList<>();

      try {
         PreparedStatement statement = 
               connection.prepareStatement("SELECT * FROM Positions");

         ResultSet result= statement.executeQuery();

         while (result.next()) {

            Position temp = new Position(result.getString(1));
            positions.add(temp);

         }

      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      finally {
         try
         {
            connection.close();
         }
         catch (SQLException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }

      return positions;

   }

   public void reset(){

      try
      {
         connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      try{
         PreparedStatement statement1 = connection.prepareStatement("DELETE FROM Candidates");
         PreparedStatement statement2 = connection.prepareStatement("DELETE FROM Positions");

         statement1.executeUpdate();
         statement2.executeUpdate();
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try
         {
            connection.close();
         }
         catch (SQLException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }
   
   public void deletePosition(String position){
      
      try
      {
         connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      try {
         PreparedStatement statement = connection.prepareStatement("DELETE FROM Positions WHERE name = ?");
      
         statement.setString(1, position);
         statement.executeUpdate();
         
         statement = connection.prepareStatement("DELETE FROM Candidates WHERE position = ?");
         
         statement.setString(1, position);
         statement.executeUpdate();
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      finally {
         try
         {
            connection.close();
         }
         catch (SQLException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      
   }
   
   public void deleteCandidate(String candidate, String position) {
      
      try
      {
         connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      try {
         PreparedStatement statement = connection.prepareStatement("DELETE FROM Candidates WHERE name = ? AND position = ?");
      
         statement.setString(1, candidate);
         statement.setString(2, position);
         statement.executeUpdate();
      
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally {
         try
         {
            connection.close();
         }
         catch (SQLException e)
         {
            e.printStackTrace();
         }
      }
      
   }

   @Override
   public Election getElection() throws RemoteException
   {
      Election election = null;
      try
      {
         connection = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");
         
         PreparedStatement statement = connection.prepareStatement("SELECT * FROM Election");
         ResultSet result = statement.executeQuery();
         result.next();
         election = new Election(result.getBoolean("active"));
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }      return election;
   }

}
