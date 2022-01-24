package client;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;		
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ChatController implements Initializable{
	
	
	@FXML
	private TextField neue_Nachricht;
	private Client client;
	
	@FXML		
	private ListView<String> kontakteBox;
	@FXML
	private ListView<String> chatBox;
	
	protected static ListProperty<String> lpUser = new SimpleListProperty<>();
	protected static ListProperty<String> lpNachrichten = new SimpleListProperty<>();
	
	//Methode die vom Button Senden benutzt wird
	@FXML
	private void nachricht_senden() throws Exception {
		
		//Nachricht über Server an andere Nutzer senden
		
		String nachricht = neue_Nachricht.getText();
		if (!neue_Nachricht.getText().isEmpty()) {
			 Client.serverThread.addNextMessage(nachricht);
			 neue_Nachricht.clear();
	        }
	}
	
	
	public void setClient(Client client){
		this.client = client;
		
	}
	
	
	public void nutzerAnzeigen() {
		ArrayList<String> users =  Message.getAktiveNutzer();
		lpUser.set(FXCollections.observableArrayList(users));
		kontakteBox.itemsProperty().bind(lpUser);
	}

	public void nachrichtenAnzeigen() {
		ArrayList<String> nachrichten =  Message.getNachrichten();
		lpNachrichten.set(FXCollections.observableArrayList(nachrichten));
		chatBox.itemsProperty().bind(lpNachrichten);
		
	} 

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nutzerAnzeigen();
		nachrichtenAnzeigen();
		
	}
	
      


}
