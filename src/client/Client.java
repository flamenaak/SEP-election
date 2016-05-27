package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import controller.Controller;
import storage.IDBManager;

public class Client
{
   private static Controller controller;

   public static void main(String[] args)
   {

      controller = new Controller();
      String host = "10.52.230.56";
      try
      {
         Registry registry = LocateRegistry.getRegistry(host);
         IDBManager stub = (IDBManager) registry.lookup("Hello");

         controller.run();
         System.out.println("client");

      }
      catch (Exception e)
      {
         System.out.println("Client exception: " + e.toString());
         e.printStackTrace();
      }
   }
}
