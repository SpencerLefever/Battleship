/**
 * Spencer Lefever
 * COSC330 Battleship
 * 
 * BattleshipServer Test class
 */

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class BattleshipServerTest
{
   public static void main( String args[] )
   {

      InetAddress ip;
      try {

         ip = InetAddress.getLocalHost();
         System.out.println(ip);
      } catch (UnknownHostException e) {
         e.printStackTrace();
      }
      Server application = new Server(); // create server
      application.runServer(); // run server application

   } // end main
} // end class ServerTest