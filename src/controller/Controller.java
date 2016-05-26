package controller;
import java.util.ArrayList;

import model.Admin;
import model.Candidate;
import model.Election;
import model.Position;
import model.User;
import model.Voter;
import view.AdminWindow;
import view.LogInWindow;
import view.VoterWindow;

public class Controller implements IController{

   private User user;
   private Voter voter;
   private Admin admin;
   private Election election;
   private DBManager dbm;
   private LogInWindow logIn;
   private AdminWindow adminW;
   private VoterWindow voterW;
   
   public Controller() {
      election = dbm.getElection();
   }
   
   
   public void logIn(String name, char[] passwordIn) {
      if (!name.equals(""))
      {
         String password = new String(passwordIn);
         user = new User(name, password);
         //System.out.println(password);
         if (dbm.logIn(name, password)instanceof Admin)
         {
            logIn.close();
            adminW = new AdminWindow(this);  
            logIn.clearFields();
         }
         else if(dbm.logIn(name, password)instanceof Voter)
         {
            logIn.close();
            voterW = new VoterWindow(this);
            logIn.clearFields();
         }
         else
         {
            logIn.clearFields();
         }
      }
   }

   @Override
   public void logOut() {
      
   }

   @Override
   public void changePassword(String password) {
      dbm.changePassword(password, user.getName());
   }

   @Override
   public void startElection(Election election) {
      dbm.startElection();
   }
   
   @Override
   public void endElection(Election election) {
      dbm.stopElection();
   }

   @Override
   public void addPosition(Position position) {
      dbm.addPosition(position);
   }

   @Override
   public void addCandidate(Position position, Candidate candidate) {
      dbm.addCandidate(position, candidate);
   }

  /* @Override
   public ArrayList<Candidate> viewCandidatesAndPositions() {

      return election.getAllCandidates();;
   }*/

   /*@Override
   public ArrayList<Candidate> viewResults(Election election, Position position) {
      ArrayList<Candidate> winning = new ArrayList<>();

      for (int i = 0; i < election.getPositions().size(); i++) {
         winning.add(election.getPosition(i).getWinningCandidate());
      }
      
      return winning;
   }*/


   @Override
   public void okPressed()
   {
      // TODO Auto-generated method stub
      
   }

   public String[] getCandidates()
   {
      return position.toStringArray();
   }

   public String[] getPositions()
   {
      return election.positionsToString();
   }



   @Override
   public void vote(int candidateIndex)
   {
      // TODO Auto-generated method stub      
   }
   
   public void run()
   {
      logIn = new LogInWindow(this);
   }


	

}
