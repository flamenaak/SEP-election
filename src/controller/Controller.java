package controller;


import java.util.ArrayList;

import model.Admin;
import model.Candidate;
import model.Election;
import model.Position;
import model.User;
import model.Voter;

public class Controller implements IController{

   private User user;
   private Voter voter;
   private Admin admin;
   private Election election;
   private DBManager dbm;
   
   public Controller() {
      user = new User();
      voter = new Voter();
      admin = new Admin();
      election = new Election();
   }
   
   
   @Override
   public void logIn(String name, String password) {
      
   }

   @Override
   public void logOut() {
      
   }
   
   public boolean checkName(String name) {
      return name.equals(user.getName());
   }

   @Override
   public boolean checkPassword(String password) {
      return password.equals(user.getPassword());
   }

   @Override
   public void changePassword(String password) {
      admin.setPassword(password);
   }

   @Override
   public void createElection() {
      
   }

   @Override
   public void startElection(Election election) {
      election.start();
   }
   
   @Override
   public void endElection(Election election) {
      election.end();
   }

   @Override
   public void addPosition(Election election, Position position) {
      election.getPosition(position);
   }

   @Override
   public void addCandidate(Election election, Position position, Candidate candidate) {
      election.getPosition(position).addCandidate(candidate);
   }

   @Override
   public ArrayList<Candidate> viewCandidatesAndPositions() {

      return election.getAllCandidates();;
   }

   @Override
   public ArrayList<Candidate> viewResults(Election election, Position position) {
      ArrayList<Candidate> winning = new ArrayList<>();

      for (int i = 0; i < election.getPositionSize(); i++) {
         winning.add(election.getPosition(i).getWinningCandidate());
      }
      
      return winning;
   }


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


	

}
