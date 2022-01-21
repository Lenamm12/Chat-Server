
package client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

//import javafx.application.Application;
//import server.FileManager;
import server.ServerThread;

public class Client {

    public static final String host = "10.0.3.36";
    public static final int portNumber = 4711;

    private static String userName;
    private static String serverHost;
    private static int serverPort;
    
    public static ServerThread serverThread;


    public static void main(String[] args) throws IOException{
    	Anmelden.main(args); //Fenster öffnen
    }
    
    /*static public void initClient() {
    	   Scanner scan = new Scanner(System.in);
    	   
        String username = AnmeldenController.getUsername();
        String passwort = AnmeldenController.getPasswort();
        
        	 Client client = new Client(username, host, portNumber);
        	 client.startClient(scan);
              
     
    } */

    Client(String userName, String host, int portNumber){
        this.userName = userName;
        this.serverHost = host;
        this.serverPort = portNumber;
    }

    public static void setServerThread(ServerThread thr) {
    	 Client.serverThread = thr;
    }
    
    public void startClient(){
    	
    	   Scanner scan = new Scanner(System.in);
    	   
        //   userName = AnmeldenController.getUsername();
       	// Client client = new Client(userName, host, portNumber);
           
        try{
        	System.out.println("socket mach was");
            Socket socket = new Socket(serverHost, serverPort);
            Thread.sleep(1000); 

            ServerThread serverThread = new ServerThread(socket, userName);
            Thread serverAccessThread = new Thread(serverThread);
            serverAccessThread.start();
            setServerThread(serverThread);
                       //Bräuchte exxtra Thread
          /*while(serverAccessThread.isAlive()){
            	  System.out.println("Test");
                if(scan.hasNextLine()){
                    serverThread.addNextMessage(scan.nextLine());
                  
                }

            } */
        }catch(IOException ex){
            System.err.println("Verbindungsfehler!");
            ex.printStackTrace();
        }catch(InterruptedException ex){
            System.out.println("Unterbrochen");
        }
    }
}
