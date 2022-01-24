package client;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.FileManager;

public class AnmeldenController {

	@FXML
	private TextField name;
	@FXML
	private TextField passwort;

	static String username;
	static String userpasswort;
	static Stage stage;

	public static ChatController con;
	private static AnmeldenController instance;

	public AnmeldenController() {
		instance = this;
	}

	public static AnmeldenController getInstance() {
		return instance;
	}

	// Methode des Login-Buttons
	@FXML
	public void login() throws Exception {
		// Auslesen der Nutzerdaten
		username = name.getText();
		userpasswort = passwort.getText();

		// Überprüfung des Nutzers
		if (FileManager.loginCheck(username, userpasswort) == true) {
			User.setName(username);

			// Weiterleitung zum Chat-Fenster
			stage = (Stage) name.getScene().getWindow();
			new Chat().start(stage);
		}
	}

	public void registrieren() throws Exception {
		// Auslesen der Nutzerdaten
		username = name.getText();
		userpasswort = passwort.getText();

		// Überprüfen des neuen Nutzers
		FileManager.registrierenCheck(username, userpasswort);
		User.setName(username);

		// Weiterleitung zum Chat-Fenster
		stage = (Stage) name.getScene().getWindow();
		new Chat().start(stage);

	}

	public static String getUsername() {
		return username;
	}

	public static String getPasswort() {
		return userpasswort;
	}

}
