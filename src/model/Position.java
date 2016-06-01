package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Position implements Serializable
{
   private static final long serialVersionUID = 1L;
   private String positionName;
   
   public Position(String positionName)
   {
      this.positionName = positionName;
   }

   public String getPositionName()
   {
      return positionName;
   }

   /*public void addCandidate(Candidate candidate)
   {
      candidateList.add(candidate);
   }

   public ArrayList<Candidate> getCandidates()
   {
      return candidateList;
   }

   public String[] toStringArray()
   {
      String[] candidateArray = new String[candidateList.size()];

      for (int i = 0; i < candidateList.size(); i++)
      {
         candidateArray[i] = candidateList.get(i).getName();
      }

      return candidateArray;
   }*/
}
