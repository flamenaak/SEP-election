package model;

import java.io.Serializable;

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
}
