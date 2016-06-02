package controller;

import java.rmi.RemoteException;

import model.Candidate;
import model.Position;


public interface IController {
   
   public void logIn(String name, char[] password);
   public void voterLogOut();
   public void adminLogOut();
   public void changePassword(String password);
   public void startElection();
   public void endElection();
   public void addPosition(Position position);
   public void addCandidate(String position, Candidate candidate);
   public void okPressed();
   public void addVote(String name, String pos);
   public void vote();
   public void reset();
   void run();
   String[] getCandidatesToCombo(Position position);
   String[] getPositionsToCombo();
   void deletePosition(String positionName);
   void deleteCandidate(String candidateName, String positionName);
   Position getPosition(String positionName);
   Candidate getCandidate(String name, String position) throws RemoteException;
   }