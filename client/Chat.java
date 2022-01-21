package client;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Chat extends Application implements Runnable{
	
	private Scene scene;
	
	  public static final String host = "10.0.3.36";
	    public static final int portNumber = 4711;


	 @Override
	    public void start(Stage stage) throws Exception {

//Doppelt
	      /*  FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("Chat.fxml"));
	        Parent root = loader.load(); */
		 
	      /*  con = fxmlLoader.<ChatController>.getController();
	        Listener listener = new Listener(name, con);
	        Thread x = new Thread(listener);
	        x.start(); */
	        
	     //   this.scene = new Scene(root);
	        
	        String username = AnmeldenController.getUsername();
	        
	        	 Client client = new Client(username, host, portNumber);
	        	 client.startClient();
	        	 
	        ChatController.nutzerAnzeigen();
	        
	        //stage.setScene(scene);
	        //stage.show();
	    }

	public static void main() {
		launch();
		
	}
	


	@Override
	public void run() {
		 try {
			 String username = AnmeldenController.getUsername();
		     System.out.println(username);
        	 Client client = new Client(username, host, portNumber);
        	 client.startClient();
        	 
       
			AnmeldenController.getInstance().showScene();
			ChatController.nutzerAnzeigen();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
