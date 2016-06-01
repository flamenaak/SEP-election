package model;

import java.io.Serializable;

public class Candidate implements Serializable
{
   private static final long serialVersionUID = 1L;
   private String name;
   private int voteCounter = 0;
   private Position position;
   //private int ID;
   //private String description;

   public Candidate(String name, Position position, int ID, String description)
   {
      this.name = name;
      this.position = position;
      //this.ID = ID;
      //this.description = description;
   }
   
   public Candidate(String name, Position position)
   {
      this.name = name;
      this.position = position;      
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public int getVotes()
   {
      return voteCounter;
   }

   public void setVotes(int voteCounter)
   {
      this.voteCounter = voteCounter;
   }

   public void giveVote()
   {
      this.voteCounter++;
   }

   public Position getPosition()
   {
      return position;
   }

   public void setPosition(Position position)
   {
      this.position = position;
   }

   /*public int getID()
   {
      return ID;
   }

   public void setID(int iD)
   {
      ID = iD;
   }*/

  /* public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }
*/
}
