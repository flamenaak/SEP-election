package storage;


import java.sql.SQLException;
import java.util.ArrayList;

import model.*;

public interface IDBManager {

	public User logIn(String username, String password) throws SQLException;
	public Election getElection() throws SQLException;
	public void startElection() throws SQLException;
	public void stopElection() throws SQLException;
	public void addPosition(Position position) throws SQLException;
	public void addCandidate(Position position, Candidate candidate) throws SQLException;
	public void changePassword(String username, String password) throws SQLException;
	public void getCandidate(String name, Position position) throws SQLException;
	public void vote(Candidate candidate) throws SQLException;
	public ArrayList<Candidate> getCandidates(Position position) throws SQLException;
	public ArrayList<Position> getPositions() throws SQLException;
	public void reset() throws SQLException;
	public void deletePosition(String position) throws SQLException;
	public void deleteCandidate(String candidate, String position) throws SQLException;
	
}
