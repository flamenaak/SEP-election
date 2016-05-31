package main;
import java.rmi.RemoteException;

import controller.Controller;
import storage.DBManager;

public class Main
{
   public static void main(String[] args) throws RemoteException
   {
      DBManager dbm = new DBManager();
      Controller con = new Controller(dbm);
      con.run();
   }
}