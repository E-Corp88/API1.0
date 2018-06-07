package de.hft.wiinf.ss.ecorp.controller;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import de.hft.wiinf.cebarround.CeBarRoundDataSensor;
import de.hft.wiinf.cebarround.SensorRegister;
import de.hft.wiinf.ss.ecorp.consumer.InitSensor1;
import de.hft.wiinf.ss.ecorp.consumer.InitSensor2;
import de.hft.wiinf.ss.ecorp.db.DB;
import de.hft.wiinf.ss.ecorp.table.Value;

import java.io.IOException;
import java.util.logging.Level;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class FXMLDocumentController implements Initializable {

	// Adding sensor
	InitSensor1 sensor;
	InitSensor2 sensor2;

	DB db = new DB();
	SensorRegister app = new CeBarRoundDataSensor();

	private int accuracy2 = 1000;
	Stage newWindow = new Stage();
	Stage newWindow2 = new Stage();
	private boolean buttonstop = true;
	private boolean buttonstop2 = true;
	private boolean buttonstop3 = true;
	private boolean buttonstop4 = true;
	private boolean aufnahme = true;
	private static final Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	@FXML
	private VBox vbox2;
	@FXML
	private Label Sensor1ID;
	@FXML
	private Label Sensor1TYPNR;
	@FXML
	private Label Sensor2ID;
	@FXML
	private Label Sensor2TYPNR;
	@FXML
	private LineChart<String, Number> Sensor1Temp;
	@FXML
	private LineChart<String, Number> Sensor1Pre;
	@FXML
	private LineChart<String, Number> Sensor1Re;
	@FXML
	private LineChart<String, Number> Sensor2Temp;
	@FXML
	private LineChart<String, Number> Sensor2Pre;
	@FXML
	private LineChart<String, Number> Sensor2Re;
	@FXML
	private CheckBox cbTemperatur;
	@FXML
	private CheckBox cbDruck;
	@FXML
	private CheckBox cbUmdrehung;
	@FXML
	private CheckBox Sensor2cbTemperatur;
	@FXML
	private CheckBox Sensor2cbDruck;
	@FXML
	private CheckBox Sensor2cbUmdrehung;
	@FXML
	private Slider slider;
	@FXML
	private Slider acSlider;
	@FXML
	private Slider Sensor2Slider1;
	@FXML
	private Slider Sensor2acSlider2;
	@FXML
	private TextField valueOfSlider;
	@FXML
	private TextField valueOfSlider1;
	@FXML
	private TextField Sensor2valueOfSlider1;
	@FXML
	private TextField Sensor2valueofSlider2;
	@FXML
	private Button sensor1StartKnopf;
	@FXML
	private Button sensor1AufnahmeKnopf;
	@FXML
	private Button sensor1StartKnopftbl;
	@FXML
	private Button sensor1AufnahmeKnopftbl;
	@FXML
	private Button sensor2StartKnopf;
	@FXML
	private Button sensor2AufnahmeKnopf;
	@FXML
	private Button sensor2StartKnopftbl;
	@FXML
	private Button sensor2AufnahmeKnopftbl;
	@FXML
	private TableView table;
	private final ObservableList<Value> data = FXCollections.observableArrayList();

	private XYChart.Series<String, Number> ser, ser2, ser3;
	private XYChart.Series<String, Number> ser4, ser5, ser6;
	@FXML
	private Spinner<Integer> spinner;
	private int boader = 10;
	@FXML
	private VBox vbox;
	@FXML
	private MenuItem delete;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {

			sensor = new InitSensor1(this);
			sensor.listen();
			
			sensor2 = new InitSensor2(this);
			sensor2.listen();

			Sensor1ID.setText("Sensor ID: ");
			Sensor1TYPNR.setText("Sensor TypNr: ");

			Sensor2ID.setText("Sensor ID: ");
			Sensor2TYPNR.setText("Sensor TypNr: ");

			cbTemperatur.setSelected(true);
			cbDruck.setSelected(true);
			cbUmdrehung.setSelected(true);

			Sensor2cbTemperatur.setSelected(true);
			Sensor2cbDruck.setSelected(true);
			Sensor2cbUmdrehung.setSelected(true);

			ser = new XYChart.Series<>();
			ser2 = new XYChart.Series<>();
			ser3 = new XYChart.Series<>();
			ser4 = new XYChart.Series<>();
			ser5 = new XYChart.Series<>();
			ser6 = new XYChart.Series<>();

			Sensor1Temp.getData().add(ser);
			Sensor1Pre.getData().add(ser2);
			Sensor1Re.getData().add(ser3);
			Sensor2Temp.getData().add(ser4);
			Sensor2Pre.getData().add(ser5);
			Sensor2Re.getData().add(ser6);

			sliderMethode(valueOfSlider, slider, valueOfSlider1, acSlider, Sensor1Temp, Sensor1Pre, Sensor1Re);
			sliderMethode(Sensor2valueOfSlider1, Sensor2Slider1, Sensor2valueofSlider2, Sensor2acSlider2, Sensor2Temp,
					Sensor2Pre, Sensor2Re);

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
		createTable();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void dataChangedS1() {

		boader = spinner.getValue();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss.SSS");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		String StringTime = sdf.format(time);

		ser.getData().add(new XYChart.Data(StringTime, sensor.temp));
		if (ser.getData().size() > boader) {
			ser.getData().remove(0);
			for (int i = 0; ser.getData().size() > boader; i++) {
				ser.getData().remove(i);
			}
			for (int i = 0; ser2.getData().size() > boader; i++) {
				ser2.getData().remove(i);
			}
			for (int i = 0; ser3.getData().size() > boader; i++) {
				ser3.getData().remove(i);
			}
		}

		ser2.getData().add(new XYChart.Data(StringTime, sensor.pressure));
		if (ser2.getData().size() > boader) {
			ser2.getData().remove(0);

		}

		ser3.getData().add(new XYChart.Data(StringTime, sensor.rev));
		if (ser3.getData().size() > boader) {
			ser3.getData().remove(0);

		}

		Platform.runLater(() -> {
			Sensor1ID.setText("Sensor ID: " + sensor.id);
			Sensor1TYPNR.setText("Sensor TypNr: " + sensor.typecode);
		});

		data.add(new Value(StringTime, String.valueOf(sensor.temp), String.valueOf(sensor.pressure),
				String.valueOf(sensor.rev)));

		if (aufnahme) {
			sensor.saveData(db.datalist, sensor.temp, sensor.pressure, sensor.rev, sensor.date, sensor.typecode,
					sensor.id);
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void dataChangedS2() {

		boader = spinner.getValue();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss.SSS");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		String StringTime = sdf.format(time);

		ser4.getData().add(new XYChart.Data(StringTime, sensor2.temp));
		if (ser4.getData().size() > boader) {
			ser4.getData().remove(0);
			for (int i = 0; ser4.getData().size() > boader; i++) {
				ser4.getData().remove(i);
			}
			for (int i = 0; ser5.getData().size() > boader; i++) {
				ser5.getData().remove(i);
			}
			for (int i = 0; ser6.getData().size() > boader; i++) {
				ser6.getData().remove(i);
			}
		}
		ser5.getData().add(new XYChart.Data(StringTime, sensor2.pressure));
		if (ser5.getData().size() > boader) {
			ser5.getData().remove(0);

		}
		ser6.getData().add(new XYChart.Data(StringTime, sensor2.rev));
		if (ser6.getData().size() > boader) {
			ser6.getData().remove(0);
		}

		Platform.runLater(() -> {
			Sensor2ID.setText("Sensor ID: " + sensor2.id);
			Sensor2TYPNR.setText("Sensor TypNr: " + sensor2.typecode);
		});
	}

	@FXML
	private void handleButtonActionSensor1(ActionEvent event) {

		// ((NumberAxis<String>)Sensor1Temp.getXAxis()).setAutoRanging(false);
		if (buttonstop) {
			buttonstop = false;
			sensor1StartKnopf.setText("Stop");
			sensor1StartKnopftbl.setText("Stop");

			// receiving data
			sensor.startMeasure();

		} else if (!buttonstop) {
			sensor1StartKnopf.setText("Start");
			sensor1StartKnopftbl.setText("Start");
			// Not receiving data anymore
			sensor.stopMeasure();
			buttonstop = true;
		}
	}

	@FXML
	private void handleButtonAction2Sensor1(ActionEvent event) {

		if (buttonstop2) {
			sensor1AufnahmeKnopf.setText("Stop");
			sensor1AufnahmeKnopftbl.setText("Stop");
			buttonstop2 = false;
			aufnahme = true;

		} else if (!buttonstop2) {
			sensor1AufnahmeKnopf.setText("Start");
			sensor1AufnahmeKnopftbl.setText("Start");
			buttonstop2 = true;
			aufnahme = false;
			db.saveDB();

		}
	}

	@FXML
	private void handleButtonActionSensor2(ActionEvent event) {
		if (buttonstop3) {
			buttonstop3 = false;
			sensor2StartKnopf.setText("Stop");
			sensor2StartKnopftbl.setText("Stop");

			// receiving data
			sensor2.startMeasure();

		} else if (!buttonstop3) {
			sensor2StartKnopf.setText("Start");
			sensor2StartKnopftbl.setText("Start");
			// Not receiving data anymore
			sensor2.stopMeasure();
			buttonstop3 = true;
		}
	}

	@FXML
	private void handleButtonAction2Sensor2(ActionEvent event) {
		if (buttonstop4) {
			sensor2AufnahmeKnopf.setText("Stop");
			sensor2AufnahmeKnopftbl.setText("Stop");
			buttonstop4 = false;
		} else if (!buttonstop4) {
			sensor2AufnahmeKnopf.setText("Start");
			sensor2AufnahmeKnopftbl.setText("Start");
			buttonstop4 = true;

		}
	}

	@FXML
	private void deleteListen() {

		ser.getData().clear();
		ser2.getData().clear();
		ser3.getData().clear();
	}

	@FXML
	private void deleteListen2() {

		ser4.getData().clear();
		ser5.getData().clear();
		ser6.getData().clear();
	}

	@FXML
	private void deleteTabel1() {
		data.clear();
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
		checkBoxSelection(this.vbox, cbTemperatur.isSelected(), cbDruck.isSelected(), cbUmdrehung.isSelected(),
				Sensor1Temp, Sensor1Pre, Sensor1Re);
		checkBoxSelection(this.vbox2, Sensor2cbTemperatur.isSelected(), Sensor2cbDruck.isSelected(),
				Sensor2cbUmdrehung.isSelected(), Sensor2Temp, Sensor2Pre, Sensor2Re);
	}

	// not removeable
	@FXML
	private void cbDruck(ActionEvent event) {

	}
	// not removeable

	@FXML
	private void cbUmdrehung(ActionEvent event) {

	}

	@FXML
	private void openTableHandle(ActionEvent event) {

		newWindow.show();
	}

	@FXML
	private void openDataArchive(ActionEvent event) {

		newWindow2.show();

	}

	@FXML
	private void valueOfSliderListen(ActionEvent event) {
		try {
			valueOfSlider.setText(valueOfSlider.getText());
			slider.setValue(Double.parseDouble(valueOfSlider.getText()));
		} catch (Exception e) {
			valueOfSlider.setText("1400");
		}
	}

	@FXML
	private void valueOfSlider1Listen(ActionEvent event) {
		try {
			valueOfSlider1.setText(valueOfSlider1.getText());
			acSlider.setValue(Double.parseDouble(valueOfSlider1.getText()));
		} catch (Exception e) {
			valueOfSlider1.setText("1000.0");
		}
	}

	private void checkBoxSelection(VBox vbox, Boolean temp, Boolean pre, Boolean rev, LineChart<String, Number> Temp,
			LineChart<String, Number> Pre, LineChart<String, Number> Re) {
		if (temp == true && pre == true && rev == true) {
			vbox.getChildren().removeAll(Temp, Pre, Re);
			vbox.getChildren().addAll(Temp, Pre, Re);
			Temp.setPrefHeight(218);
			Pre.setPrefHeight(218);
			Re.setPrefHeight(218);
		}
		if (temp == true && pre == true && rev == false) {
			vbox.getChildren().removeAll(Temp, Pre, Re);
			vbox.getChildren().addAll(Temp, Pre);
			Temp.setPrefHeight(327);
			Pre.setPrefHeight(327);
		}
		if (temp == true && pre == false && rev == false) {
			vbox.getChildren().removeAll(Temp, Pre, Re);
			vbox.getChildren().addAll(Temp);
			Temp.setPrefHeight(654);
		}
		if (temp == false && pre == true && rev == false) {
			vbox.getChildren().removeAll(Temp, Pre, Re);
			vbox.getChildren().addAll(Pre);
			Pre.setPrefHeight(654);
		}
		if (temp == false && pre == true && rev == true) {
			vbox.getChildren().removeAll(Temp, Pre, Re);
			vbox.getChildren().addAll(Pre, Re);
			Re.setPrefHeight(327);
			Pre.setPrefHeight(327);

		}
		if (temp == true && pre == false && rev == true) {
			vbox.getChildren().removeAll(Temp, Pre, Re);
			vbox.getChildren().addAll(Temp, Re);
			Temp.setPrefHeight(327);
			Re.setPrefHeight(327);
		}
		if (temp == false && pre == false && rev == false) {
			vbox.getChildren().removeAll(Temp, Pre, Re);
			Temp.setPrefHeight(218);
			Pre.setPrefHeight(218);
			Re.setPrefHeight(218);

		}
		if (temp == false && pre == false && rev == true) {
			vbox.getChildren().removeAll(Sensor1Temp, Sensor1Pre, Sensor1Re);
			vbox.getChildren().addAll(Sensor1Re);
			Sensor1Re.setPrefHeight(654);
		}

	}

	private void sliderMethode(TextField vos, Slider s, TextField vos1, Slider acs,
			LineChart<String, Number> Sensor1Temp, LineChart<String, Number> Sensor1Pre,
			LineChart<String, Number> Sensor1Re) {
		vos.setText(String.valueOf(s.getValue()));
		s.valueProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue arg0, Object arg1, Object arg2) {
				vos.textProperty().setValue(String.valueOf((int) s.getValue()));

			}
		});

		acs.valueProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue arg0, Object arg1, Object arg2) {
				accuracy2 = (int) acs.getValue();
			}
		});
		vos1.setText(String.valueOf(acs.getValue()));
		acs.valueProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue arg0, Object arg1, Object arg2) {
				vos1.textProperty().setValue(String.valueOf((int) acs.getValue()));
				sensor.setVerbosity(acs.getValue());

			}
		});

		Service<String> service;
		service = new Service<String>() {
			@Override
			protected Task<String> createTask() {
				return new Task<String>() {
					@SuppressWarnings("unchecked")
					@Override
					protected String call() throws Exception {
						s.valueProperty().addListener(new ChangeListener() {

							@Override
							public void changed(ObservableValue arg0, Object arg1, Object arg2) {
								Sensor1Temp.setPrefWidth(s.getValue());
								Sensor1Pre.setPrefWidth(s.getValue());
								Sensor1Re.setPrefWidth(s.getValue());
							}
						});
						return null;
					}
				};
			}
		};
		service.start();

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createTable() {
		TableColumn time = new TableColumn("Zeit");
		time.setMinWidth(100);
		time.setMaxWidth(100);
		time.setCellValueFactory(new PropertyValueFactory<Value, String>("time"));

		TableColumn temp = new TableColumn("Temperatur (°C)");
		temp.setMinWidth(150);
		temp.setMaxWidth(150);
		temp.setCellValueFactory(new PropertyValueFactory<Value, String>("value1"));

		TableColumn press = new TableColumn("Druck (bar)");
		press.setMinWidth(150);
		press.setMaxWidth(150);
		press.setCellValueFactory(new PropertyValueFactory<Value, String>("value2"));

		TableColumn rev = new TableColumn("Umdrehung (U/min)");
		rev.setMinWidth(150);
		rev.setMaxWidth(150);
		rev.setCellValueFactory(new PropertyValueFactory<Value, String>("value3"));
		table.setItems(data);
		table.getColumns().addAll(time, temp, press, rev);

	}
}
