
package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Admin;
import model.Candidate;
import model.Election;
import model.Position;
import model.User;
import model.Voter;
import storage.DBManager;
import view.AdminWindow;
import view.LogInWindow;
import view.VoterWindow;

public class Controller implements IController
{

   private User user;
   private Voter voter;
   private Admin admin;
   private Election election;
   private DBManager dbm;
   private LogInWindow logIn;
   private AdminWindow adminW;
   private VoterWindow voterW;
   private ArrayList<Candidate> voteList;

   public Controller()
   {
      election = dbm.getElection();
   }

   public void logIn(String name, char[] passwordIn)
   {
      if (!name.equals(""))
      {
         String password = new String(passwordIn);
         user = new User(name, password);
         // System.out.println(password);
         try
         {
            if (dbm.logIn(name, password) instanceof Admin)
            {
               logIn.close();
               adminW = new AdminWindow(this);
               logIn.clearFields();
            }
            else if (dbm.logIn(name, password) instanceof Voter)
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
         catch (SQLException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }

   @Override
   public void logOut()
   {

   }

   @Override
   public void changePassword(String password)
   {
      dbm.changePassword(password, user.getName());
   }

   @Override
   public void startElection()
   {
      dbm.startElection();
   }

   @Override
   public void endElection()
   {
      dbm.stopElection();
   }

   @Override
   public void addPosition(Position position)
   {
      dbm.addPosition(position);
   }

   @Override
   public void addCandidate(Position position, Candidate candidate)
   {
      dbm.addCandidate(position, candidate);
   }

   /*
    * @Override public ArrayList<Candidate> viewCandidatesAndPositions() {
    * return election.getAllCandidates();; }
    */

   /*
    * @Override public ArrayList<Candidate> viewResults(Election election,
    * Position position) { ArrayList<Candidate> winning = new ArrayList<>(); for
    * (int i = 0; i < election.getPositions().size(); i++) {
    * winning.add(election.getPosition(i).getWinningCandidate()); } return
    * winning; }
    */

   @Override
   public void okPressed()
   {
      // TODO Auto-generated method stub

   }

   public void addVote(String name, String pos)
   {
      for(int i = 0; i < voteList.size(); i++)
      {
         if(voteList.get(i).getPosition().equals(pos))
            voteList.remove(i);
      }
      try
      {
         voteList.add(dbm.getCandidate(name, pos));
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
   
   public void vote()
   {
      for(int i = 0; i < voteList.size(); i++)
      {
         dbm.vote(voteList.get(i));        
      }
   }

   public void run()
   {
      logIn = new LogInWindow(this);
   }

   public String[] getCandidatesToCombo(Position position)
   {
      ArrayList<Candidate> list = dbm.getCandidates(position);
      String[] array = new String[list.size()];
      for (int i = 0; i < list.size(); i++)
      {
         array[i] = list.get(i).getName();
      }
      return array;
   }

   public String[] getPositionsToCombo()
   {
      ArrayList<Position> list = dbm.getPositions();
      String[] array = new String[list.size()];
      for (int i = 0; i < list.size(); i++)
      {
         array[i] = list.get(i).getPositionName();
      }
      return array;
   }
}
