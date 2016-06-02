package model;

import java.io.Serializable;

public class Election implements Serializable
{
   private static final long serialVersionUID = 1L;
   private boolean active;

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
