package storage;

import java.util.ArrayList;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

import model.*;

public interface IDBManager extends Remote{

	public User logIn(String username, String password) throws SQLException, RemoteException;
	public Election getElection() throws SQLException, RemoteException;

	public void startElection() throws SQLException;
	public void stopElection() throws SQLException;
	public void addPosition(Position position) throws SQLException;
	public void addCandidate(String position, Candidate candidate) throws SQLException;
	public void changePassword(String username, String password) throws SQLException;

	public Candidate getCandidate(String name, String positionName) throws SQLException;

	public void vote(Candidate candidate) throws SQLException;
	public ArrayList<Candidate> getCandidates(Position position) throws SQLException;
	public ArrayList<Position> getPositions() throws SQLException;
	public void reset() throws SQLException;
	public void deletePosition(String position) throws SQLException;
	public void deleteCandidate(String candidate, String position) throws SQLException;
	
}
