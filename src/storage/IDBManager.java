package storage;

import java.util.ArrayList;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.*;

public interface IDBManager extends Remote{

	public User logIn(String username, String password) throws RemoteException;
	public Election getElection() throws RemoteException;

	public void startElection() throws RemoteException;
	public void stopElection() throws RemoteException;
	public void addPosition(Position position) throws RemoteException;
	public void addCandidate(String position, Candidate candidate) throws RemoteException;
	public void changePassword(String username, String password) throws RemoteException;

	public Candidate getCandidate(String name, String positionName) throws RemoteException;

	public void vote(Candidate candidate) throws RemoteException;
	public ArrayList<Candidate> getCandidates(Position position) throws RemoteException;
	public ArrayList<Position> getPositions() throws RemoteException;
	public void reset() throws RemoteException;
	public void deletePosition(String position) throws RemoteException;
	public void deleteCandidate(String candidate, String position) throws RemoteException;
	}
