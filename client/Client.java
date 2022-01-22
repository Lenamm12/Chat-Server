
package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import server.ServerThread;

public class Client {

    public static final String host = "10.0.3.36";
    public static final int portNumber = 4711;

    private static String userName;
    private static String serverHost;
    private static int serverPort;
    public ChatController controller;
    private Socket socket;
    public static ServerThread serverThread;
    
    private static ObjectOutputStream oos;
    private InputStream is;
    private ObjectInputStream input;
    private OutputStream outputStream;


    public static void main(String[] args) throws IOException{
    	Anmelden.main(args); //Fenster öffnen
    }

    Client(String userName, String host, int portNumber, ChatController controller){
        this.userName = userName;
        this.serverHost = host;
        this.serverPort = portNumber;
        this.controller = controller;
    }

    public static void setServerThread(ServerThread thr) {
    	 Client.serverThread = thr;
    }
    
    public void startClient(){
    	 Thread serverAccessThread;
    	
           
        try{
        	System.out.println("socket mach was");
            Socket socket = new Socket(serverHost, serverPort);
            Thread.sleep(1000); 

            ServerThread serverThread = new ServerThread(socket, userName);
            serverAccessThread = new Thread(serverThread);
            serverAccessThread.start();
            setServerThread(serverThread);
            try {
            connect();
          while(serverAccessThread.isAlive()){
        	   Message message = null;
               message = (Message) input.readObject();

               if (message != null) {
                   controller.addToChat(message);
                   
               }
        	  
          }
          }
          catch (IOException | ClassNotFoundException e) {
              e.printStackTrace();
              controller.logoutScene();
          }

        }catch(IOException ex){
            System.err.println("Verbindungsfehler!");
            ex.printStackTrace();
        }catch(InterruptedException ex){
            System.out.println("Unterbrochen");
        }
            
         
            
    }
    
    public static void connect() throws IOException {
        Message createMessage = new Message();
        createMessage.setName(userName);
        createMessage.setNachricht("verbunden");
        oos.writeObject(createMessage);
    }
    

	public static void send(String msg) throws IOException {
        Message createMessage = new Message();
        createMessage.setName(userName);
        createMessage.setNachricht(msg);
        oos.writeObject(createMessage);
        oos.flush();
    }
}
