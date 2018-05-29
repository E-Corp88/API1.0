
package javafxapplication13;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import Consumer.InitSensor1;
import Consumer.TestRun;
import de.hft.wiinf.cebarround.CeBarRoundDataSensor;
import de.hft.wiinf.cebarround.SensorRegister;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
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

	private int counter = 0;
	private int counter2 = 0;
	private int counter3 = 0;
	private int counterSensor1StartKnopf = 0;
	private int counterSensor1StartKnopf2 = 0;
	private int accuracy2 = 1000;
	private boolean stopper = true;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		sensor = new InitSensor1(this);
		sensor.listen();
		
		Sensor1ID.setText("Sensor ID: ");
		Sensor1TYPNR.setText("Sensor TypNr: ");

		cbTemperatur.setSelected(true);
		cbDruck.setSelected(true);
		cbUmdrehung.setSelected(true);
		valueOfSlider.setText(String.valueOf(slider.getValue()));
		slider.valueProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue arg0, Object arg1, Object arg2) {
				valueOfSlider.textProperty().setValue(String.valueOf((int) slider.getValue()));

			}
		});
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
			}
		});

		accuracy.start();

	}

	class Accuracy implements Runnable {

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

		if (counterSensor1StartKnopf % 2 == 0) {
			stopper = true;
			sensor1StartKnopf.setText("Stop");

			// receiving data
			sensor.startMeasure();
			

		}
		if (counterSensor1StartKnopf % 2 != 0) {
			sensor1StartKnopf.setText("Start");
			// Not receiving data anymore
			sensor.stopMeasure();
			stopper = false;
		}
		counterSensor1StartKnopf++;
	}

	@FXML
	private void handleButtonAction2Sensor1(ActionEvent event) {

		if (counterSensor1StartKnopf2 % 2 == 0) {
			sensor1StartKnopf2.setText("Stop");
		}
		if (counterSensor1StartKnopf2 % 2 != 0) {
			sensor1StartKnopf2.setText("Start");
		}
		counterSensor1StartKnopf2++;
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
		log.info("graphics gedrückt");

	}

	@FXML
	private void tableListen(ActionEvent event) {
		log.info("table gedruückt");

	}

	@FXML
	private void dataArchivesListen(ActionEvent event) {
		log.info("dataArchive gedrückt");

	}

	@FXML
	private void cbTemperatur(ActionEvent event) {
		if (counter % 2 == 0) {
			Sensor1Temp.setVisible(false);
			Sensor1Pre.setPrefHeight(327);
			Sensor1Re.setPrefHeight(327);

		}
		if (counter % 2 != 0) {
			Sensor1Temp.setVisible(true);
			Sensor1Pre.setPrefHeight(218);
			Sensor1Re.setPrefHeight(218);
		}
		counter++;
	}

	@FXML
	private void cbDruck(ActionEvent event) {
		if (counter2 % 2 == 0) {
			Sensor1Pre.setVisible(false);
			Sensor1Temp.setPrefHeight(327);
			Sensor1Re.setPrefHeight(327);
		}
		if (counter2 % 2 != 0) {
			Sensor1Pre.setVisible(true);
			Sensor1Temp.setPrefHeight(218);
			Sensor1Re.setPrefHeight(218);
		}
		counter2++;
	}

	@FXML
	private void cbUmdrehung(ActionEvent event) {
		if (counter3 % 2 == 0) {
			Sensor1Re.setVisible(false);
			Sensor1Pre.setPrefHeight(327);
			Sensor1Temp.setPrefHeight(327);
		}
		if (counter3 % 2 != 0) {
			Sensor1Re.setVisible(true);
			Sensor1Temp.setPrefHeight(218);
			Sensor1Pre.setPrefHeight(218);
		}
		counter3++;
	}

}
