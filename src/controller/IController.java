package controller;

import java.sql.SQLException;

import model.Candidate;
import model.Election;
import model.Position;


public interface IController {
   
   public void logIn(String name, char[] password);
   public void voterLogOut();
   public void adminLogOut();
   //public boolean checkPassword(String password);
   //public boolean checkName(String name);
   
   public void changePassword(String password);

   public void startElection();
   public void endElection();
   public void addPosition(Position position);
   public void addCandidate(String position, Candidate candidate);
   
   //public void viewCandidatesAndPositions();
   //public void viewResults(Position position);
   public void okPressed();
   
   public void addVote(String name, String pos);
   public void reset() throws SQLException;
   
}
