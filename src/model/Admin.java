package model;

import java.io.Serializable;

public class Admin extends User implements Serializable
{
   private static final long serialVersionUID = 1L;

   public Admin(String name, String password)
   {
      super(name, password);
   }

   public void changePassword(String password)
   {
      this.password = password;
   }

   public void setPassword(String pass)
   {
      password = pass;
   }
}
