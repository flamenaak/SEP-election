package controller;

import java.io.Serializable;
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

public class Controller implements IController, Serializable
{
   private static final long serialVersionUID = 1L;

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
      try
      {
         election = dbm.getElection();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
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
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
   }
   
   @Override
   public void adminLogOut()
   {
      try
      {
         //do some stuff
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   @Override
   public void voterLogOut()
   {
      try
      {
         vote();
         voterW.setVisible(false);
         logIn = new LogInWindow(this);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
     
   }

   @Override
   public void changePassword(String password)
   {
      try
      {
         dbm.changePassword(password, user.getName());
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   @Override
   public void startElection()
   {
      try
      {
         dbm.startElection();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   @Override
   public void endElection()
   {
      try
      {
         dbm.stopElection();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   @Override
   public void addPosition(Position position)
   {
      try
      {
         dbm.addPosition(position);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   @Override
   public void addCandidate(String position, Candidate candidate)
   {
      try
      {
         dbm.addCandidate(position, candidate);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
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
      try
      {
         vote();
         voterW.setVisible(false);
         logIn = new LogInWindow(this);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      voterW.setVisible(false);
      logIn = new LogInWindow(this);
   }

   public void addVote(String name, String pos)
   {
      for (int i = 0; i < voteList.size(); i++)
      {
         if (voteList.get(i).getPosition().equals(pos))
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
  @Override
  public void vote()
   {
      for (int i = 0; i < voteList.size(); i++)
      {
         try
         {
            dbm.vote(voteList.get(i));
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      voteList = new ArrayList<Candidate>();
   }
  
  @Override
   public void run()
   {
      logIn = new LogInWindow(this);
   }
  
  @Override
   public String[] getCandidatesToCombo(Position position)
   {
      ArrayList<Candidate> list;
      String[] array = null;
      try
      {
         list = dbm.getCandidates(position);
         array = new String[list.size()];
         for (int i = 0; i < list.size(); i++)
         {
            array[i] = list.get(i).getName();
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      
      return array;
   }

   public String[] getPositionsToCombo() throws SQLException
   {
      ArrayList<Position> list;
      String[] array = null;
      try
      {
         list = dbm.getPositions();
         array = new String[list.size()];
         for (int i = 0; i < list.size(); i++)
         {
            array[i] = list.get(i).getPositionName();
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      
      return array;
   }

  @Override
   public void deletePosition(String positionName) 
   {
      try
      {
         dbm.deletePosition(positionName);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

  @Override
   public void deleteCandidate(String candidateName, String positionName)
   {
      try
      {
         dbm.deleteCandidate(candidateName, positionName);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

  @Override
   public Position getPosition(String positionName)
   {
      Position pos = null;
      ArrayList<Position> list;
      try
      {
         list = dbm.getPositions();
         for(int i = 0; i < list.size(); i++)
         {
            if (positionName == list.get(i).getPositionName())
            {
               pos = list.get(i);
            }
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }        
      
      return pos;
   }
  
  @Override
   public void reset(){
      try
      {
         dbm.reset();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   @Override
   public Candidate getCandidate(String name, String position)
   {
      // TODO Auto-generated method stub
      return null;
   }
}
