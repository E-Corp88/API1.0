package de.hft.wiinf.ss.ecorp.controller;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import de.hft.wiinf.cebarround.CeBarRoundDataSensor;
import de.hft.wiinf.cebarround.SensorEvent;
import de.hft.wiinf.cebarround.SensorRegister;
import de.hft.wiinf.ss.ecorp.consumer.InitSensor1;
import de.hft.wiinf.ss.ecorp.db.DB;
import de.hft.wiinf.ss.ecorp.event.EventDTO;
import de.hft.wiinf.ss.ecorp.table.FXMLTableController;

import java.io.IOException;
import java.util.logging.Level;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class FXMLDocumentController implements Initializable {

	// Adding the DATA of the API
	InitSensor1 sensor;
	DB db = new DB();

	@SuppressWarnings("unused")
	private int accuracy2 = 1000;

	Stage newWindow = new Stage();
	Stage newWindow2 = new Stage();

	private boolean buttonstop = true;
	private boolean buttonstop2 = true;
	private boolean graphtemp = true;
	private boolean graphrev = true;
	private boolean graphpres = true;
	private boolean aufnahme = false;

	

	private static final Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@FXML
	private Label Sensor1ID;
	@FXML
	private Label Sensor1TYPNR;
	@FXML
	private LineChart<String, Number> Sensor1Temp;
	@FXML
	private LineChart<String, Number> Sensor1Pre;
	@FXML
	private LineChart<String, Number> Sensor1Re;
	@FXML
	private CheckBox cbTemperatur;
	@FXML
	private CheckBox cbDruck;
	@FXML
	private CheckBox cbUmdrehung;
	@FXML
	private Slider slider;
	@FXML
	private Slider acSlider;
	@FXML
	private TextField valueOfSlider;
	@FXML
	private TextField valueOfSlider1;
	@FXML
	private Button sensor1StartKnopf;
	@FXML
	private Button sensor1StartKnopf2;
	private XYChart.Series<String, Number> ser, ser2, ser3;
	@FXML
	private Spinner<Integer> spinner;
	private int boader = 10;

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {

			try {
				sensor = new InitSensor1(this);
				sensor.listen();

				Sensor1ID.setText("Sensor ID: ");
				Sensor1TYPNR.setText("Sensor TypNr: ");

				cbTemperatur.setSelected(true);
				cbDruck.setSelected(true);
				cbUmdrehung.setSelected(true);

				ser = new XYChart.Series<>();
				ser.setName("Temperatur");
				ser2 = new XYChart.Series<>();
				ser2.setName("Druck");
				ser3 = new XYChart.Series<>();
				ser3.setName("Umdrehung");
				Sensor1Temp.getData().add(ser);
				Sensor1Temp.setAnimated(false);
				Sensor1Pre.getData().add(ser2);
				Sensor1Pre.setAnimated(false);
				Sensor1Re.getData().add(ser3);
				Sensor1Re.setAnimated(false);

				valueOfSlider.setText(String.valueOf(slider.getValue()));
				slider.valueProperty().addListener(new ChangeListener() {

					@Override
					public void changed(ObservableValue arg0, Object arg1, Object arg2) {
						valueOfSlider.textProperty().setValue(String.valueOf((int) slider.getValue()));

					}
				});

				acSlider.valueProperty().addListener(new ChangeListener() {

					@Override
					public void changed(ObservableValue arg0, Object arg1, Object arg2) {
						accuracy2 = (int) acSlider.getValue();
					}
				});
				valueOfSlider1.setText(String.valueOf(acSlider.getValue()));
				acSlider.valueProperty().addListener(new ChangeListener() {

					@Override
					public void changed(ObservableValue arg0, Object arg1, Object arg2) {
						valueOfSlider1.textProperty().setValue(String.valueOf((int) acSlider.getValue()));
						sensor.setVerbosity(acSlider.getValue());

					}
				});

				Service<String> service;
				service = new Service<String>() {
					@Override
					protected Task<String> createTask() {
						return new Task<String>() {
							@Override
							protected String call() throws Exception {
								slider.valueProperty().addListener(new ChangeListener() {

									@Override
									public void changed(ObservableValue arg0, Object arg1, Object arg2) {
										Sensor1Temp.setPrefWidth(slider.getValue());
										Sensor1Pre.setPrefWidth(slider.getValue());
										Sensor1Re.setPrefWidth(slider.getValue());
									}
								});
								return null;
							}
						};
					}
				};
				service.start();

				Parent root2 = FXMLLoader.load(getClass().getResource("FXMLTable.fxml"));
				StackPane secondaryLayout = new StackPane();
				Scene secondScene = new Scene(root2);
				newWindow.setScene(secondScene);
			} catch (IOException ex) {
				Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
			}

			Parent root3 = FXMLLoader.load(getClass().getResource("FXMLArchive.fxml"));
			StackPane secondaryLayout2 = new StackPane();
			Scene secondScene = new Scene(root3);
			newWindow2 = new Stage();
			newWindow2.setScene(secondScene);
		} catch (IOException ex) {
			Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
		}
		SpinnerValueFactory<Integer> valueFactory = //
				new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 100, 10);

		spinner.setValueFactory(valueFactory);
		spinner.setEditable(true);
		boader = spinner.getValue();

	}

	// boader erweiterbar allerdings nicht komprimierbar
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void dataChanged() {

		boader = spinner.getValue();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss.SSS");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		String StringTime = sdf.format(time);

		ser.getData().add(new XYChart.Data(StringTime, sensor.temp));
		if (ser.getData().size() > boader) {
			ser.getData().remove(0);
		}

		ser2.getData().add(new XYChart.Data(StringTime, sensor.pressure));
		if (ser2.getData().size() > boader) {
			ser2.getData().remove(0);
		}

		ser3.getData().add(new XYChart.Data(StringTime, sensor.rev));
		if (ser3.getData().size() > boader) {
			ser3.getData().remove(0);
		}

		if (aufnahme) {
			sensor.saveData(db.datalist, sensor.temp, sensor.pressure, sensor.rev, sensor.date, sensor.typecode,
					sensor.id);
			for (int x = 0; x < db.datalist.size(); x++) {
				System.out.println(db.datalist.get(x));
				
			}
		}

		Platform.runLater(() -> {
			Sensor1ID.setText("Sensor ID: " + sensor.id);
			Sensor1TYPNR.setText("Sensor TypNr: " + sensor.typecode);

		});

	}

	@FXML
	private void handleButtonActionSensor1(ActionEvent event) {

		// ((NumberAxis<String>)Sensor1Temp.getXAxis()).setAutoRanging(false);
		if (buttonstop) {
			buttonstop = false;
			sensor1StartKnopf.setText("Stop");

			// receiving data
			sensor.startMeasure();

		} else if (!buttonstop) {
			sensor1StartKnopf.setText("Start");
			// Not receiving data anymore
			sensor.stopMeasure();
			buttonstop = true;
		}
	}

	@FXML
	private void handleButtonAction2Sensor1(ActionEvent event) {

		if (buttonstop2) {
			sensor1StartKnopf2.setText("Stop");
			buttonstop2 = false;
			aufnahme = true;
		} else if (!buttonstop2) {
			sensor1StartKnopf2.setText("Start");
			buttonstop2 = true;
			aufnahme = false;
		}
	}

	@FXML
	private void textboxSkalierung(ActionEvent event) {
		slider.setValue(Double.parseDouble(valueOfSlider.getText()));
	}

	@FXML
	private void textboxIntervall(ActionEvent event) {
		acSlider.setValue(Double.parseDouble(valueOfSlider1.getText()));
	}

	@FXML
	private void handleButtonActionSensor2(ActionEvent event) {
		System.out.println("You clicked handleButtonActionSensor2");

	}

	@FXML
	private void handleButtonAction2Sensor2(ActionEvent event) {
		System.out.println("You clicked handleButtonAction2Sensor2!");

	}

	@FXML
	private void graphicListen(ActionEvent event) {
		log.info("graphics gedrueckt");

	}

	@FXML
	private void tableListen(ActionEvent event) {
		log.info("table gedrueckt");

	}

	@FXML
	private void dataArchivesListen(ActionEvent event) {
		log.info("dataArchive gedrueckt");

	}

	@FXML
	private void cbTemperatur(ActionEvent event) {
		if (graphtemp) {
			Sensor1Temp.setVisible(false);
			Sensor1Pre.setPrefHeight(327);
			Sensor1Re.setPrefHeight(327);
			graphtemp = false;

		} else if (!graphtemp) {
			Sensor1Temp.setVisible(true);
			Sensor1Pre.setPrefHeight(218);
			Sensor1Re.setPrefHeight(218);
			graphtemp = true;
		}
	}

	@FXML
	private void cbDruck(ActionEvent event) {
		if (graphpres) {
			Sensor1Pre.setVisible(false);
			Sensor1Temp.setPrefHeight(327);
			Sensor1Re.setPrefHeight(327);
			graphpres = false;
		} else if (!graphpres) {
			Sensor1Pre.setVisible(true);
			Sensor1Temp.setPrefHeight(218);
			Sensor1Re.setPrefHeight(218);
			graphpres = true;
		}
	}

	@FXML
	private void cbUmdrehung(ActionEvent event) {
		if (graphrev) {
			Sensor1Re.setVisible(false);
			Sensor1Pre.setPrefHeight(327);
			Sensor1Temp.setPrefHeight(327);
			graphrev = false;
		} else if (!graphrev) {
			Sensor1Re.setVisible(true);
			Sensor1Temp.setPrefHeight(218);
			Sensor1Pre.setPrefHeight(218);
			graphrev = true;
		}
	}

	@FXML
	private void openTableHandle(ActionEvent event) {

		newWindow.show();

	}

	@FXML
	private void openDataArchive(ActionEvent event) {

		newWindow2.show();

	}

}
