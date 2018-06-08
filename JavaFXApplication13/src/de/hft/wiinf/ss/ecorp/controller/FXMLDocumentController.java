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
	private boolean aufnahmeS1 = true;
	private boolean aufnahmeS2 = true;
	private static final Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	@FXML
	private VBox vbox2;
	@FXML
	private Label sensor1ID;
	@FXML
	private Label sensor1TYPNR;
	@FXML
	private Label sensor2ID;
	@FXML
	private Label sensor2TYPNR;
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
	private CheckBox cbTemperaturSensor2;
	@FXML
	private CheckBox cbDruckSensor2;
	@FXML
	private CheckBox cbUmdrehungSensor2;
	@FXML
	private Slider zoomSliderSens1;
	@FXML
	private Slider acSliderSens1;
	@FXML
	private Slider zoomSliderSens2;
	@FXML
	private Slider acSliderSens2;
	@FXML
	private TextField valueOfzoomSliderSens1;
	@FXML
	private TextField valueOfacSliderSens1;
	@FXML
	private TextField valueOfzoomSliderSens2;
	@FXML
	private TextField valueOfacSliderSens2;
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
	private TableView tableS1;
	@FXML
	private TableView tableS2;

	private final ObservableList<Value> dataS1 = FXCollections.observableArrayList();
	private final ObservableList<Value> dataS2 = FXCollections.observableArrayList();

	private XYChart.Series<String, Number> ser, ser2, ser3;
	private XYChart.Series<String, Number> ser4, ser5, ser6;
	@FXML
	private Spinner<Integer> spinner;
	private int boader = 10;
	@FXML
	private Spinner<Integer> spinner2;
	private int boader2 = 10;
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

			sensor1ID.setText("Sensor ID: ");
			sensor1TYPNR.setText("Sensor TypNr: ");

			sensor2ID.setText("Sensor ID: ");
			sensor2TYPNR.setText("Sensor TypNr: ");

			cbTemperatur.setSelected(true);
			cbDruck.setSelected(true);
			cbUmdrehung.setSelected(true);

			cbTemperaturSensor2.setSelected(true);
			cbDruckSensor2.setSelected(true);
			cbUmdrehungSensor2.setSelected(true);

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

			sliderMethode(valueOfzoomSliderSens1, zoomSliderSens1, valueOfacSliderSens1, acSliderSens1, Sensor1Temp,
					Sensor1Pre, Sensor1Re);
			sliderMethode2(valueOfzoomSliderSens2, zoomSliderSens2, valueOfacSliderSens2, acSliderSens2, Sensor2Temp,
					Sensor2Pre, Sensor2Re);

			Parent root3 = FXMLLoader.load(getClass().getResource("FXMLArchive.fxml"));
			StackPane secondaryLayout2 = new StackPane();
			Scene secondScene = new Scene(root3);
			newWindow2 = new Stage();
			newWindow2.setScene(secondScene);
		} catch (IOException ex) {
			Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
		}
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 100, 10);

		spinner.setValueFactory(valueFactory);
		spinner.setEditable(true);

		SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 100, 10);

		spinner2.setValueFactory(valueFactory2);
		spinner2.setEditable(true);

		boader = spinner.getValue();
		boader2 = spinner2.getValue();

		createTableS1();
		createTableS2();

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
			sensor1ID.setText("Sensor ID: " + sensor.id);
			sensor1TYPNR.setText("Sensor TypNr: " + sensor.typecode);
		});

		dataS1.add(new Value(StringTime, String.valueOf(sensor.temp), String.valueOf(sensor.pressure),
				String.valueOf(sensor.rev)));

		if (aufnahmeS1) {
			sensor.saveData(db.datalistsensor1, sensor.temp, sensor.pressure, sensor.rev, sensor.date, sensor.typecode,
					sensor.id);
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void dataChangedS2() {

		boader2 = spinner2.getValue();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss.SSS");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		String StringTime = sdf.format(time);

		ser4.getData().add(new XYChart.Data(StringTime, sensor2.temp));
		if (ser4.getData().size() > boader2) {
			ser4.getData().remove(0);
			for (int i = 0; ser4.getData().size() > boader2; i++) {
				ser4.getData().remove(i);
			}
			for (int i = 0; ser5.getData().size() > boader2; i++) {
				ser5.getData().remove(i);
			}
			for (int i = 0; ser6.getData().size() > boader2; i++) {
				ser6.getData().remove(i);
			}
		}
		ser5.getData().add(new XYChart.Data(StringTime, sensor2.pressure));
		if (ser5.getData().size() > boader2) {
			ser5.getData().remove(0);

		}
		ser6.getData().add(new XYChart.Data(StringTime, sensor2.rev));
		if (ser6.getData().size() > boader2) {
			ser6.getData().remove(0);
		}

		Platform.runLater(() -> {
			sensor2ID.setText("Sensor ID: " + sensor2.id);
			sensor2TYPNR.setText("Sensor TypNr: " + sensor2.typecode);
		});

		dataS2.add(new Value(StringTime, String.valueOf(sensor2.temp), String.valueOf(sensor2.pressure),
				String.valueOf(sensor2.rev)));

		if (aufnahmeS2) {
			sensor2.saveData(db.datalistsensor2, sensor2.temp, sensor2.pressure, sensor2.rev, sensor2.date,
					sensor2.typecode, sensor2.id);
		}

	}

	@FXML
	private void handleButtonActionSensor1ShowData(ActionEvent event) {

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
	private void handleButtonActionSensor1SaveData(ActionEvent event) {

		if (buttonstop2) {
			sensor1AufnahmeKnopf.setText("Stop");
			sensor1AufnahmeKnopftbl.setText("Stop");
			buttonstop2 = false;
			aufnahmeS1 = true;

		} else if (!buttonstop2) {
			sensor1AufnahmeKnopf.setText("Start");
			sensor1AufnahmeKnopftbl.setText("Start");
			buttonstop2 = true;
			aufnahmeS1 = false;
			db.saveDBSensor1();
			db.closeConnection();

		}
	}

	@FXML
	private void handleButtonActionSensor2ShowData(ActionEvent event) {
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
	private void handleButtonActionSensor2SaveData(ActionEvent event) {
		if (buttonstop4) {
			sensor2AufnahmeKnopf.setText("Stop");
			sensor2AufnahmeKnopftbl.setText("Stop");
			buttonstop4 = false;
			aufnahmeS2 = true;

		} else if (!buttonstop4) {
			sensor2AufnahmeKnopf.setText("Start");
			sensor2AufnahmeKnopftbl.setText("Start");
			buttonstop4 = true;
			aufnahmeS2 = false;
			db.saveDBSensor2();
			db.closeConnection();
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
	private void deleteTable1() {
		dataS1.clear();
	}

	@FXML
	private void deleteTable2() {
		dataS2.clear();
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
		checkBoxSelection(this.vbox2, cbTemperaturSensor2.isSelected(), cbDruckSensor2.isSelected(),
				cbUmdrehungSensor2.isSelected(), Sensor2Temp, Sensor2Pre, Sensor2Re);
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
	private void valueOfzoomSliderAction(ActionEvent event) {
		try {
			valueOfzoomSliderSens1.setText(valueOfzoomSliderSens1.getText());
			zoomSliderSens1.setValue(Double.parseDouble(valueOfzoomSliderSens1.getText()));
		} catch (Exception e) {
			valueOfzoomSliderSens1.setText("1378");
		}
	}

	@FXML
	private void valueOfacSliderAction(ActionEvent event) {
		try {
			valueOfacSliderSens1.setText(valueOfacSliderSens1.getText());
			acSliderSens1.setValue(Double.parseDouble(valueOfacSliderSens1.getText()));
		} catch (Exception e) {
			valueOfacSliderSens1.setText("1000.0");
		}
	}

	@FXML
	private void valueOfzoomSliderAction2(ActionEvent event) {
		try {
			valueOfzoomSliderSens2.setText(valueOfzoomSliderSens2.getText());
			zoomSliderSens2.setValue(Double.parseDouble(valueOfzoomSliderSens2.getText()));
		} catch (Exception e) {
			valueOfzoomSliderSens2.setText("1378");
		}
	}

	@FXML
	private void valueOfacSliderAction2(ActionEvent event) {
		try {
			valueOfacSliderSens2.setText(valueOfacSliderSens2.getText());
			acSliderSens2.setValue(Double.parseDouble(valueOfacSliderSens2.getText()));
		} catch (Exception e) {
			valueOfacSliderSens2.setText("1000.0");
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

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	private void sliderMethode2(TextField vos, Slider s, TextField vos1, Slider acs,
			LineChart<String, Number> Sensor2Temp, LineChart<String, Number> Sensor2Pre,
			LineChart<String, Number> Sensor2Re) {
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
				sensor2.setVerbosity(acs.getValue());

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
								Sensor2Temp.setPrefWidth(s.getValue());
								Sensor2Pre.setPrefWidth(s.getValue());
								Sensor2Re.setPrefWidth(s.getValue());
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
	public void createTableS1() {
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

		tableS1.setItems(dataS1);
		tableS1.getColumns().addAll(time, temp, press, rev);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createTableS2() {
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

		tableS2.setItems(dataS2);
		tableS2.getColumns().addAll(time, temp, press, rev);

	}

}
