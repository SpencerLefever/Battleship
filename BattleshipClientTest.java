/**
 * Spencer Lefever
 * COSC330 Battleship
 * 
 * BattleshipClientTest class
 */

public class BattleshipClientTest 
{
   public static void main( String args[] )
   {
      Client application; // declare client application

      // if no command line args
      if ( args.length == 0 )
         application = new Client( "127.0.1.1" ); // connect to localhost
      else
         application = new Client( args[ 0 ] ); // use args to connect

      application.runClient(); // run client application
   } // end main
} // end class ClientTest