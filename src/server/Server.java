package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Candidate;
import model.Election;
import model.Position;
import model.User;
import storage.DBManager;
import storage.IDBManager;

public class Server implements IDBManager
{
   private static String IP = "192.168.1.1";
   private static DBManager dbm;

   public static void main(String[] args)
   {

      try
      {
         dbm = new DBManager();
         System.setProperty("java.rmi.server.hostname", IP);
         Server obj = new Server();
         IDBManager stub = (IDBManager) UnicastRemoteObject
               .exportObject((Remote) obj, 0);

         // Bind the remote object's stub in the registry
         Registry registry = LocateRegistry.getRegistry();
         registry.bind("Hello", (Remote) stub);

         System.out.println("Server ready");
      }
      catch (Exception e)
      {
         System.out.println("Server exception: " + e.toString());
         e.printStackTrace();
      }
   }

   @Override
   public User logIn(String username, String password) throws SQLException
   {
      return dbm.logIn(username, password);
   }

   @Override
   public Election getElection() throws SQLException
   {
      return dbm.getElection();
   }

   @Override
   public void startElection() throws SQLException
   {
      dbm.startElection();
   }

   @Override
   public void stopElection() throws SQLException
   {
      dbm.stopElection();
   }

   @Override
   public void addPosition(Position position) throws SQLException
   {
      dbm.addPosition(position);
   }

   @Override
   public void addCandidate(Position position, Candidate candidate)
         throws SQLException
   {
      dbm.addCandidate(position, candidate);
   }

   @Override
   public void changePassword(String username, String password)
         throws SQLException
   {
      dbm.changePassword(username, password);
   }

   @Override
   public void getCandidate(String name, Position position)
         throws SQLException, RemoteException
   {
      dbm.getCandidate(name, position);
   }

   @Override
   public void vote(Candidate candidate) throws SQLException, RemoteException
   {
      dbm.vote(candidate);
   }

   @Override
   public ArrayList<Candidate> getCandidates(Position position)
         throws SQLException, RemoteException
   {
      return dbm.getCandidates(position);
   }

   @Override
   public ArrayList<Position> getPositions()
         throws SQLException, RemoteException
   {
      return dbm.getPositions();
   }

   @Override
   public void reset() throws SQLException
   {
      dbm.reset();
   }

   @Override
   public void deletePosition(String position)
         throws SQLException
   {
      dbm.deletePosition(position);
   }

   @Override
   public void deleteCandidate(String candidate, String position)
         throws SQLException
   {
      dbm.deleteCandidate(candidate, position);
   }

}
