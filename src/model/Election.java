package model;

import java.io.Serializable;

public class Election implements Serializable
{
   private static final long serialVersionUID = 1L;
   private boolean active;
   private static Election instance = new Election();
   
   private Election(){};
   
   public static Election getInstance() {
      return instance;
   }

   public Election(boolean b)
   {
      active = b;
   }

   public void startElection()
   {
      active = true;
   }

   public void closeEection()
   {
      active = false;
   }

   public boolean getState()
   {
      return active;
   }

}