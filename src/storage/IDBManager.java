package storage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

import model.*;

public interface IDBManager extends Remote {

   public User logIn(String username, String password) throws SQLException, RemoteException;
   public Election getElection() throws SQLException, RemoteException;
   public void startElection() throws SQLException, RemoteException;
   public void stopElection() throws SQLException, RemoteException;
   public void addPosition(Position position) throws SQLException, RemoteException;
   public void addCandidate(Position position, Candidate candidate) throws SQLException, RemoteException;
   public void changePassword(String username, String password) throws SQLException, RemoteException;
   
}
