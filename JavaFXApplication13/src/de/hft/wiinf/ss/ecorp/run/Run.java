/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hft.wiinf.ss.ecorp.run;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

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
public class Run extends Application {

	public static String currentLocale;

	@Override
	public void start(Stage stage) throws Exception {
		Locale locale = new Locale("de");
		currentLocale = "de";
		ResourceBundle resources = new ResourceBundleWrapper(ResourceBundle.getBundle("de.hft.wiinf.ss.ecorp.run/message", locale));
		System.out.println(this.getClass().getClassLoader());
		System.out.println(resources.getClass().getClassLoader());

		Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"), resources);

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
	
	public void startEN(Stage stage) throws Exception {
		Locale locale = new Locale("en");
		currentLocale = "en";
		ResourceBundle resources = new ResourceBundleWrapper(ResourceBundle.getBundle("de.hft.wiinf.ss.ecorp.run/message", locale));
		System.out.println(this.getClass().getClassLoader());
		System.out.println(resources.getClass().getClassLoader());

		Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"), resources);

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
	
	private static class ResourceBundleWrapper extends ResourceBundle{
		private final ResourceBundle bundle;
		
		public ResourceBundleWrapper(ResourceBundle bundle) {
			this.bundle = bundle;
		}
		
		@Override
		protected Object handleGetObject(String key) {
			// TODO Auto-generated method stub
			return bundle.getObject(key);
		}
		

		@Override
		public Enumeration<String> getKeys() {
			// TODO Auto-generated method stub
			return bundle.getKeys();
		}

		
		
		@Override
		public boolean containsKey(String key) {
			return bundle.containsKey(key);
		}
		
		@Override
		public Locale getLocale() {
			return bundle.getLocale();
			
		}
		
		@Override
		public Set<String> keySet(){
			return bundle.keySet();
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		launch(args);

	}

}
