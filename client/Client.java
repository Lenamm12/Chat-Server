
package client;

import java.io.IOException;
import java.net.Socket;
import server.ServerThread;

public class Client {

	private Client client;
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
            Socket socket = new Socket(serverHost, serverPort);
            Thread.sleep(1000); 
            
            ServerThread serverThread = new ServerThread(socket, this);
            serverAccessThread = new Thread(serverThread);
            serverAccessThread.start();
            setServerThread(serverThread);
            Message.addNachricht(userName +" ist dem Chat beigetreten");

        }catch(IOException ex){
            System.err.println("Es kann keine Verbindung hergestellt werden.!");
            ex.printStackTrace();
        }catch(InterruptedException ex){
            System.out.println("Die Verbindung wurde unterbrochen");
        }
                   
    }
    
}
