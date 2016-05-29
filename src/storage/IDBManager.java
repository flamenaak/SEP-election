package storage;

import java.util.ArrayList;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

import model.*;

public interface IDBManager extends Remote{

	public User logIn(String username, String password) throws SQLException, RemoteException;
	public Election getElection() throws SQLException, RemoteException;
<<<<<<< HEAD
	public void startElection() throws SQLException;
	public void stopElection() throws SQLException;
	public void addPosition(Position position) throws SQLException;
	public void addCandidate(Position position, Candidate candidate) throws SQLException;
	public void changePassword(String username, String password) throws SQLException;
	public Candidate getCandidate(String name, Position position) throws SQLException;
	public void vote(Candidate candidate) throws SQLException;
	public ArrayList<Candidate> getCandidates(Position position) throws SQLException;
	public ArrayList<Position> getPositions() throws SQLException;
	public void reset() throws SQLException;
	public void deletePosition(String position) throws SQLException;
	public void deleteCandidate(String candidate, String position) throws SQLException;
	
=======
	public void startElection() throws SQLException, RemoteException;
	public void stopElection() throws SQLException, RemoteException;
	public void addPosition(Position position) throws SQLException, RemoteException;
	public void addCandidate(Position position, Candidate candidate) throws SQLException, RemoteException;
	public void changePassword(String username, String password) throws SQLException, RemoteException;
	public void getCandidate(String name, Position position) throws SQLException, RemoteException;
	public void vote(Candidate candidate) throws SQLException, RemoteException;
	public ArrayList<Candidate> getCandidates(Position position) throws SQLException, RemoteException;
	public ArrayList<Position> getPositions() throws SQLException, RemoteException;
	public void reset() throws SQLException, RemoteException;
	public void deletePosition(String position) throws SQLException, RemoteException;
	public void deleteCandidate(String candidate, String position) throws SQLException, RemoteException;
>>>>>>> refs/heads/develop
}
