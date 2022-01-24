package client;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Chat extends Application {
	
	
	  public static final String host = "10.0.3.36";
	    public static final int portNumber = 4711;
	  

	    //Öffnen des Chat-Fensters
	 public void start(Stage stage) throws Exception {

		 try {
			 String username = AnmeldenController.getUsername();
		     
		     FXMLLoader loader = new FXMLLoader();
		     loader.setLocation(getClass().getResource("Chat.fxml"));
		     
		     Parent root = loader.load();
			 ChatController con =  loader.<ChatController>getController();
			    
		     Client client = new Client(username, host, portNumber, con);
        	 
		     stage.setOnCloseRequest(e -> {Platform.exit(); System.exit(0);});

        	 Scene scene = new Scene(root);
		     stage.setScene(scene);
		     stage.show();
		     
        	 
		     con.setClient(client);
		     client.startClient();
       
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	    }
	
}
