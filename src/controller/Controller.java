package controller;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Admin;
import model.Candidate;
import model.Election;
import model.Position;
import model.User;
import model.Voter;
import storage.DBManager;
import storage.IDBManager;
import view.AdminWindow;
import view.LogInWindow;
import view.VoterWindow;
import controller.IController;

public class Controller implements IController, Serializable
{
   private static final long serialVersionUID = 1L;

   private User user;
   private Voter voter;
   private Admin admin;
   private Election election;
   private IDBManager dbm;
   private LogInWindow logIn;
   private AdminWindow adminW;
   private VoterWindow voterW;
   private ArrayList<Candidate> voteList;

   public Controller(IDBManager dbm)
   {
      try
      {
         this.dbm = dbm;
         election = dbm.getElection();
         voteList = new ArrayList<>();
      }
      catch (Exception e)
      {
      }
   }

   @Override
   public void adminLogOut()
   {
      try
      {
         adminW.close();
         logIn = new LogInWindow(this);
      }
      catch (Exception e)
      {
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
               JOptionPane.showMessageDialog(new JFrame(), "Unable to log in. Check your username and password. You can only vote once.");
            }
         }
         catch (Exception e)
         {
         }
      }
   }

   @Override
   public void voterLogOut()
   {
      try
      {
         voterW.setVisible(false);
         logIn = new LogInWindow(this);
      }
      catch (Exception e)
      {
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
      }
   }

   @Override
   public void changePassword(String password)
   {
      try
      {
         dbm.changePassword(user.getName(), password);
      }
      catch (Exception e)
      {
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
      }

      return array;
   }

   public String[] getPositionsToCombo()
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
      }

      return array;
   }

   @Override
   public void okPressed()
   {
      try
      {
         vote();
      }
      catch (Exception e)
      {
      }
      finally
      {
         voterLogOut();
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
         for (int i = 0; i < list.size(); i++)
         {
            if (positionName.equals(list.get(i).getPositionName()))
            {
               pos = list.get(i);
            }
         }
      }
      catch (Exception e)
      {
      }

      return pos;
   }

   public Object[] getResultsToCombo(Position position)
   {
      ArrayList<Candidate> list;
      String[] array = null;
      try
      {
         list = dbm.getCandidates(position);
         array = new String[list.size()];
         for (int i = 0; i < list.size(); i++)
         {
            array[i] = list.get(i).getName() + " " + list.get(i).getVotes();
         }
      }
      catch (Exception e)
      {
      }

      return array;
   }

   public boolean getElection()
   {
      boolean election = false;
      try
      {
         election = dbm.getElection().getState();
      }
      catch (RemoteException e)
      {
      }
      return election;

   }

   public void disableTabs(String string)
   {
      adminW.disableTabs(string);
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
      }
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
      }
   }
   
   @Override
   public Candidate getCandidate(String name, String position)
   {
      Candidate candidate = null;
      try
      {
         candidate = dbm.getCandidate(name, position);
      }
      catch (RemoteException e)
      {
      }
      return candidate;
   }

   public void refreshAdmin()
   {
      adminW.refreshPanel();
   }
   
   @Override
   public void reset(){
      try
      {
         dbm.reset();
      }
      catch (Exception e)
      {
      }
   }
}
