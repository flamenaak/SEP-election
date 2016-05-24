package model;

import java.util.ArrayList;

public class Position
{
   private String positionName;
   private ArrayList<Candidate> candidateList;
   
   public Position(String positionName)
   {
      this.positionName = positionName;
      candidateList = new ArrayList<Candidate>();
   }
   
   public String getPositionName()
   {
      return positionName;
   }
   
   public void addCandidate(Candidate candidate)
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
  
      for(int i = 0; i < candidateList.size(); i++)
      {
         candidateArray[i] = candidateList.get(i).getName();
      }
      
      return candidateArray;
   }
}
