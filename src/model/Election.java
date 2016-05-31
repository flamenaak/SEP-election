package model;

import java.io.Serializable;
import java.util.ArrayList;
import controller.*;

public class Election implements Serializable
{
   private static final long serialVersionUID = 1L;
   private boolean active;
   private ArrayList<Position> positions;

   public Election(boolean actiove)
   {
      this.active = active;
      positions = new ArrayList<>();
   }

   public void addPosition(Position pos)
   {
      positions.add(pos);
   }

   public void empty()
   {
      positions = null;
   }

   public Position getPosition(String name)
   {
      for (int i = 0; i < positions.size(); i++)
      {
         if (positions.get(i).getPositionName().equals(name))
         {
            return positions.get(i);
         }
      }
      return null;
   }

   public Position getPosition(int index)
   {
      return positions.get(index);
   }

   public void startElection()
   {
      active = true;
   }

   public void closeEection()
   {
      active = false;
   }

   public String[] positionsToString()
   {
      String[] positionArray = new String[positions.size()];

      for (int i = 0; i < positions.size(); i++)
      {
         positionArray[i] = positions.get(i).getPositionName();
      }

      return positionArray;
   }

   public ArrayList<Position> getPositions()
   {
      return positions;
   }

}
