package client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import controller.Controller;
import storage.IDBManager;

public class Client
{
   private static Controller controller;

   public static void main(String[] args) throws RemoteException
   {
      
      String host = "192.168.1.95";
      try
      {
         Registry registry = LocateRegistry.getRegistry(host);
         IDBManager stub = (IDBManager) registry.lookup("Hello");
         controller = new Controller(stub);

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
