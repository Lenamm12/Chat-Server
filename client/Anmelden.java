package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Anmelden extends Application {
	
	//static Stage stage2;
	
	public static void main(String[] args) {
		launch(args);
	}

	 @Override
	    public void start(Stage stage) throws Exception {

	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("Anmelden.fxml"));
	        Parent root = loader.load();
	        
	        Scene scene = new Scene(root);
	        //stage2 = stage;
	        stage.setScene(scene);
	        stage.show();
	    }
	 
	 /*public static void beenden() throws Exception{
		 stage2.close();
	 }
*/
}
