package client;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import server.FileManager;

public class ChatController {
	
	@FXML
	private AnchorPane chatBox;
	@FXML
	private TextField neue_Nachricht;
	
	@FXML
	static ListView kontakteBox;
	
	protected static ListProperty<String> lp = new SimpleListProperty<>();
	
	@FXML
	private void nachricht_senden() throws Exception {
		
		//Nachricht im Fenster anzeigen
		
		//Nachricht über Server an anderen Nutzer senden
		
	}
	
	public static void nutzerAnzeigen() {
		System.out.println("Nutzer anzeigen funkt");
		
		ArrayList<String> list =  FileManager.getAktiveNutzer();
		lp.set(FXCollections.observableArrayList(list));
		kontakteBox.itemsProperty().bind(lp);
		
	}

}
