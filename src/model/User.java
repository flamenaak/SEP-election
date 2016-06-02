package model;

import java.io.Serializable;

public class User implements Serializable
{
   private static final long serialVersionUID = 1L;
   
   private String name;
   protected String password;

   public User(String name, String password)
   {
      this.name = name;
      this.password = password;
   }

   public String getPassword()
   {
      return password;
   }

   public String getName()
   {
      return name;
   }

}
