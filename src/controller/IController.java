package controller;

import model.Candidate;
import model.Election;
import model.Position;


public interface IController {
	
	public void logIn(String name, String password);
	public void logOut();
	public boolean checkPassword(String password);
	public boolean checkName(String name);
	
	public void changePassword(String password);

	public void createElection();
	public void startElection(Election election);
	public void endElection(Election election);
	public void addPosition(Election election, Position position);
	public void addCandidate(Election election, Position position, Candidate candidate);
	
	public void viewCandidatesAndPositions();
	public void viewResults(Election election, Position position);
	public void okPressed();
	
	public void vote(int candidateIndex);
}
