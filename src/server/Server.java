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

// rmiregistry -J-Djava.rmi.server.codebase=file:"D:\ICTengineering\EclipseProjects\SEP-election\bin\\"
//java -classpath "D:\ICTengineering\EclipseProjects\SEP-election\bin;D:\ICT engineering\PostgresSQL\postgresql-9.4.1208.jre6.jar" -Djava.rmi.server.codebase=file:"D:\ICTengineering\EclipseProjects\SEP-election\bin" server.Server
public class Server implements IDBManager
{
   private static String IP = "10.52.225.141";
   private static DBManager dbm;

   public static void main(String[] args)
   {
      try
      {
         dbm = new DBManager();
         System.setProperty("java.rmi.server.hostname", IP);
         Server obj = new Server();
         IDBManager stub = (IDBManager) UnicastRemoteObject.exportObject((Remote) obj, 0);

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
   public User logIn(String username, String password)
   {
      return dbm.logIn(username, password);
   }

   @Override
   public Election getElection() throws RemoteException
   {
      return dbm.getElection();
   }

   @Override
   public void startElection()
   {
      dbm.startElection();
   }

   @Override
   public void stopElection() throws RemoteException
   {
      dbm.stopElection();
   }

   @Override
   public void addPosition(Position position) throws RemoteException
   {
      dbm.addPosition(position);
   }

   @Override
   public void addCandidate(String position, Candidate candidate) throws RemoteException
   {
      dbm.addCandidate(position, candidate);
   }

   @Override
   public void changePassword(String username, String password) throws RemoteException
   {
      dbm.changePassword(username, password);
   }

   @Override
   public Candidate getCandidate(String name, String position) throws RemoteException
   {
      return dbm.getCandidate(name, position);

   }

   @Override
   public void vote(Candidate candidate) throws RemoteException
   {
      dbm.vote(candidate);
   }

   @Override
   public ArrayList<Candidate> getCandidates(Position position) throws RemoteException
   {
      return dbm.getCandidates(position);
   }

   @Override
   public ArrayList<Position> getPositions() throws RemoteException
   {
      return dbm.getPositions();
   }

   @Override
   public void reset() throws RemoteException
   {
      dbm.reset();
   }

   @Override
   public void deletePosition(String position) throws RemoteException
   {
      dbm.deletePosition(position);
   }

   @Override
   public void deleteCandidate(String candidate, String position) throws RemoteException
   {
      dbm.deleteCandidate(candidate, position);

   }

}
