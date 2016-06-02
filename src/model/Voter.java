package model;

import java.io.Serializable;

public class Voter extends User implements Serializable
{
   private static final long serialVersionUID = 1L;
   private boolean voted;

   public Voter(String name, String password, boolean voted)
   {
      super(name, password);
      this.voted = voted;
   }

   public void vote()
   {
      voted = true;
   }
   
   public boolean voted()
   {
      return voted;
   }
}
