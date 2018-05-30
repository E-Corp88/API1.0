/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hft.wiinf.SS18.ECorp.Run;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author User
 */
public class JavaFXApplication13 extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

		Scene scene = new Scene(root);
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				System.exit(0);
			}

		});
		stage.setScene(scene);
		stage.show();

	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		launch(args);

	}

}
