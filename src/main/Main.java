package main;
import controller.Controller;
import view.AdminWindow;

public class Main
{
   public static void main(String[] args)
   {
      Controller con = new Controller();
	   AdminWindow win = new AdminWindow(con);
   }
}
