
package client;

import java.io.IOException;
import java.net.Socket;
import server.ServerThread;

public class Client {


    private static String userName;
    private static String serverHost;
    private static int serverPort;
    public ChatController controller;
    
    private Socket socket;
    public static ServerThread serverThread;
    
    public static void main(String[] args) throws IOException{
    	Anmelden.main(args); //Anmelden-Fenster öffnen
    	
    }
    
    //Client-Konstruktor
    Client(String userName, String host, int portNumber, ChatController controller){
        this.userName = userName;
        this.serverHost = host;
        this.serverPort = portNumber;
        this.controller = controller;
    }

    public static void setServerThread(ServerThread thr) {
    	 Client.serverThread = thr;
    }
    
    //Starten des Sockets und ServerThreads 
    public void startClient(){
    	 Thread serverAccessThread;
    	
           
        try{
        	System.out.println("Methode startClient()");
            Socket socket = new Socket(serverHost, serverPort);
            Thread.sleep(1000); 
            
            ServerThread serverThread = new ServerThread(socket, userName);
            serverAccessThread = new Thread(serverThread);
            serverAccessThread.start();
            setServerThread(serverThread);

        }catch(IOException ex){
            System.err.println("Verbindungsfehler!");
            ex.printStackTrace();
        }catch(InterruptedException ex){
            System.out.println("Unterbrochen");
        }
                   
    }
    
}
