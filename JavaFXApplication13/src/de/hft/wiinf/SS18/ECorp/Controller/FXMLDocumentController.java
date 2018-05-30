
package de.hft.wiinf.SS18.ECorp.Controller;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import de.hft.wiinf.SS18.ECorp.Consumer.InitSensor1;
import de.hft.wiinf.cebarround.CeBarRoundDataSensor;
import de.hft.wiinf.cebarround.SensorRegister;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
/**
 *
 * @author User
 */
public class FXMLDocumentController implements Initializable {

	// Adding the DATA of the API
	InitSensor1 sensor;

	SensorRegister app = new CeBarRoundDataSensor();

	private int accuracy2 = 1000;
	private boolean buttonstop = true;
	private boolean graphtemp = true;
	private boolean graphrev = true;
	private boolean graphpres = true;
	@FXML
	private ScrollPane scroll;
	private Thread accuracy = new Thread(new Accuracy());
	private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {

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

		accuracy.start();

	}

	public class Accuracy implements Runnable {

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public void run() {
			slider.valueProperty().addListener(new ChangeListener() {

				@Override
				public void changed(ObservableValue arg0, Object arg1, Object arg2) {
					Sensor1Temp.setPrefWidth(slider.getValue());
					Sensor1Pre.setPrefWidth(slider.getValue());
					Sensor1Re.setPrefWidth(slider.getValue());
				}
			});

		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void dataChanged() {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		String StringTime = sdf.format(time);

		ser.getData().add(new XYChart.Data(StringTime, sensor.temp));
		if (ser.getData().size() > 10) {
			ser.getData().remove(0);
		}

		ser2.getData().add(new XYChart.Data(StringTime, sensor.pressure));
		if (ser2.getData().size() > 10) {
			ser2.getData().remove(0);
		}

		ser3.getData().add(new XYChart.Data(StringTime, sensor.rev));
		if (ser3.getData().size() > 10) {
			ser3.getData().remove(0);
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

		if (buttonstop) {
			sensor1StartKnopf2.setText("Stop");
			buttonstop = false;
		} else if (!buttonstop) {
			sensor1StartKnopf2.setText("Start");
			buttonstop = true;
		}
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

}
