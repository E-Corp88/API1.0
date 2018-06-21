package de.hft.wiinf.ss.ecorp.controller;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import de.hft.wiinf.cebarround.CeBarRoundDataSensor;
import de.hft.wiinf.cebarround.SensorRegister;
import de.hft.wiinf.ss.ecorp.consumer.InitSensor1;
import de.hft.wiinf.ss.ecorp.consumer.InitSensor2;
import de.hft.wiinf.ss.ecorp.db.DB;
import de.hft.wiinf.ss.ecorp.logger.LogBook;
import de.hft.wiinf.ss.ecorp.run.Run;
import de.hft.wiinf.ss.ecorp.table.Value;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 *
 * @author User
 */
public class FXMLDocumentController implements Initializable {

	// Adding sensor
	InitSensor1 sensor;
	InitSensor2 sensor2;

	ArrayList<String> messreiheS1 = new ArrayList<>();
	ArrayList<String> messreiheS2 = new ArrayList<>();

	ArrayList<String> timeS1 = new ArrayList<>();
	ArrayList<String> timeS2 = new ArrayList<>();

	String timeSavedS1;
	String timeSavedS2;

	DB db = new DB();
	SensorRegister app = new CeBarRoundDataSensor();
	ObservableList<String> languageList = FXCollections.observableArrayList("de", "en");
	private int accuracy2 = 1000;
	Stage newWindow = new Stage();
	Stage newWindow2 = new Stage();
	private boolean buttonstop = true;
	private boolean buttonstop2 = true;
	private boolean buttonstop3 = true;
	private boolean buttonstop4 = true;
	private boolean aufnahmeS1 = true;
	private boolean aufnahmeS2 = true;
	private static final Logger log = Logger.getLogger("");
	private LogBook logBook = new LogBook(log);
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
	private Button loadArchiveS1;
	@FXML
	private Button loadArchiveS2;
	@FXML
	private TableView tableS1;
	@FXML
	private TableView tableS2;
	@FXML
	private TableColumn time1;
	@FXML
	private TableColumn temp1;
	@FXML
	private TableColumn press1;
	@FXML
	private TableColumn rev1;
	@FXML
	private TableColumn time2;
	@FXML
	private TableColumn temp2;
	@FXML
	private TableColumn press2;
	@FXML
	private TableColumn rev2;
	private final ObservableList<Value> tbldataS1 = FXCollections.observableArrayList();
	private final ObservableList<Value> tbldataS2 = FXCollections.observableArrayList();

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
	@FXML
	private ListView list1;
	ObservableList<String> list1_Items = FXCollections.observableArrayList();
	@FXML
	private ListView list2;
	ObservableList<String> list2_Items = FXCollections.observableArrayList();
	@FXML
	public ChoiceBox languageChoice;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (Run.currentLocale == "de") {
			languageChoice.setValue("de");
		} else if (Run.currentLocale == "en") {
			languageChoice.setValue("en");
		}
		languageChoice.setItems(languageList);
		languageChanged();

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

		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 100, 10);

		spinner.setValueFactory(valueFactory);
		spinner.setEditable(false);

		SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 100, 10);

		spinner2.setValueFactory(valueFactory2);
		spinner2.setEditable(false);

		boader = spinner.getValue();
		boader2 = spinner2.getValue();

		createTableS1();
		createTableS2();

		createList(list1, list1_Items);
		createList(list2, list2_Items);

		// in thread packen!!!
		db.getArchiveS1(messreiheS1, timeS1);
		db.getArchiveS2(messreiheS2, timeS2);

		try {
			addItemsToListView(list1, messreiheS1.get(0) + ". Messreihe @" + timeS1.get(0));
			db.messIDS1 = 2;

			for (int x = 0; x < messreiheS1.size(); x++) {
				if (!(messreiheS1.get(x).equals(messreiheS1.get(x + 1)))) {
					addItemsToListView(list1, messreiheS1.get(x + 1) + ". Messreihe @" + timeS1.get(x + 1));
					db.messIDS1 = Integer.parseInt(messreiheS1.get(x + 1)) + 1;
				}
			}

		} catch (IndexOutOfBoundsException e) {
		}

		try {
			addItemsToListView(list2, messreiheS2.get(0) + ". Messreihe @" + timeS2.get(0));
			db.messIDS2 = 2;

			for (int x = 0; x < messreiheS2.size(); x++) {
				if (!(messreiheS2.get(x).equals(messreiheS2.get(x + 1)))) {
					addItemsToListView(list2, messreiheS2.get(x + 1) + ". Messreihe @" + timeS2.get(x + 1));
					db.messIDS2 = Integer.parseInt(messreiheS2.get(x + 1)) + 1;
				}
			}
		} catch (IndexOutOfBoundsException e) {
		}
	}

	@SuppressWarnings("unchecked")
	public void languageChanged() {
		languageChoice.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
			Stage stage = (Stage) languageChoice.getScene().getWindow();
			if (!buttonstop) {
				sensor.stopMeasure();
			}
			if (!buttonstop2) {
				aufnahmeS1 = false;
			}
			if (!buttonstop3) {
				sensor.stopMeasure();
			}
			if (!buttonstop4) {
				aufnahmeS2 = false;
			}
			stage.close();

			Platform.runLater(() -> {
				if (newValue.toString() == "en") {
					try {
						new Run().startEN(new Stage());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (newValue.toString() == "de") {
					try {
						new Run().start(new Stage());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void dataChangedS1() {
		try {
			boader = spinner.getValue();
			SimpleDateFormat sdf = new SimpleDateFormat("MMM d. HH:mm:ss.SS");
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

			tbldataS1.add(new Value(StringTime, String.valueOf(sensor.temp), String.valueOf(sensor.pressure),
					String.valueOf(sensor.rev)));

			if (aufnahmeS1) {

				timeSavedS1 = sdf.format(sensor.date);

				sensor.saveData(db.datalistsensor1, sensor.temp, sensor.pressure, sensor.rev, timeSavedS1,
						sensor.typecode, sensor.id);
			}

		} catch (Exception e) {
			log.info("Obacht");
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void dataChangedS2() {

		boader2 = spinner2.getValue();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM d. HH:mm:ss.SSS");
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

		tbldataS2.add(new Value(StringTime, String.valueOf(sensor2.temp), String.valueOf(sensor2.pressure),
				String.valueOf(sensor2.rev)));

		if (aufnahmeS2) {

			timeSavedS2 = sdf.format(sensor2.date);

			sensor2.saveData(db.datalistsensor2, sensor2.temp, sensor2.pressure, sensor2.rev, timeSavedS2,
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

			languageChoice.setDisable(true);
			loadArchiveS1.setDisable(true);

			// receiving data
			sensor.startMeasure();

		} else if (!buttonstop) {
			sensor1StartKnopf.setText("Start");
			sensor1StartKnopftbl.setText("Start");

			languageChoice.setDisable(false);
			loadArchiveS1.setDisable(false);

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
			loadArchiveS1.setDisable(true);

		} else if (!buttonstop2) {
			sensor1AufnahmeKnopf.setText("Start");
			sensor1AufnahmeKnopftbl.setText("Start");
			buttonstop2 = true;
			aufnahmeS1 = false;
			loadArchiveS1.setDisable(false);
			db.saveDBSensor1();

			if (!db.datalistsensor1.isEmpty()) {
				addItemsToListView(list1, db.messIDS1.toString() + ". Messreihe @" + timeSavedS1);
				db.messIDS1++;
			}
			db.datalistsensor1.clear();
		}
	}

	@FXML
	private void handleButtonActionSensor2ShowData(ActionEvent event) {
		if (buttonstop3) {
			buttonstop3 = false;
			sensor2StartKnopf.setText("Stop");
			sensor2StartKnopftbl.setText("Stop");

			languageChoice.setDisable(true);
			loadArchiveS2.setDisable(true);

			// receiving data
			sensor2.startMeasure();

		} else if (!buttonstop3) {
			sensor2StartKnopf.setText("Start");
			sensor2StartKnopftbl.setText("Start");

			languageChoice.setDisable(false);
			loadArchiveS2.setDisable(false);

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
			loadArchiveS2.setDisable(true);

		} else if (!buttonstop4) {
			sensor2AufnahmeKnopf.setText("Start");
			sensor2AufnahmeKnopftbl.setText("Start");
			buttonstop4 = true;
			aufnahmeS2 = false;
			loadArchiveS2.setDisable(false);
			db.saveDBSensor2();

			if (!db.datalistsensor2.isEmpty()) {
				addItemsToListView(list2, db.messIDS2.toString() + ". Messreihe @" + timeSavedS2);
				db.messIDS2++;
			}
			db.datalistsensor2.clear();
		}
	}

	@FXML
	private void deleteListen() {

		ser.getData().clear();
		ser2.getData().clear();
		ser3.getData().clear();
		db.tempValuesS1.clear();
		db.revValuesS1.clear();
		db.presValuesS1.clear();
		db.timeValuesS1.clear();
	}

	@FXML
	private void deleteListen2() {

		ser4.getData().clear();
		ser5.getData().clear();
		ser6.getData().clear();
		db.tempValuesS2.clear();
		db.revValuesS2.clear();
		db.presValuesS2.clear();
		db.timeValuesS2.clear();
	}

	@FXML
	private void deleteTable1() {
		tbldataS1.clear();
		db.tempValuesS1.clear();
		db.revValuesS1.clear();
		db.presValuesS1.clear();
		db.timeValuesS1.clear();
	}

	@FXML
	private void deleteTable2() {
		tbldataS2.clear();
		db.tempValuesS2.clear();
		db.revValuesS2.clear();
		db.presValuesS2.clear();
		db.timeValuesS2.clear();
	}

	@FXML
	private void loadBtnListen(ActionEvent event) {
		try {
			deleteListen();
			deleteTable1();
			getSelectedItemS1(list1);
			visualiseDataS1();
			db.tempValuesS1.clear();
			db.revValuesS1.clear();
			db.presValuesS1.clear();
			db.timeValuesS1.clear();
		} catch (NullPointerException e) {
		}
	}

	@FXML
	private void loadBtnListen2(ActionEvent event) {
		try {
			deleteListen2();
			deleteTable2();
			getSelectedItemS2(list2);
			visualiseDataS2();
			db.tempValuesS2.clear();
			db.revValuesS2.clear();
			db.presValuesS2.clear();
			db.timeValuesS2.clear();
		} catch (NullPointerException e) {
		}
	}

	@FXML
	private void deleteBtnListen(ActionEvent event) {

		try {
			deleteSelectedItemS1(list1);
		} catch (NullPointerException e) {
		}

	}

	@FXML
	private void deleteBtnListen2(ActionEvent event) {
		try {
			deleteSelectedItemS2(list2);
		} catch (NullPointerException e) {
		}
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
			vbox.getChildren().removeAll(Temp, Pre, Re);
			vbox.getChildren().addAll(Re);
			Re.setPrefHeight(654);
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

		time1.setResizable(false);
		time1.setCellValueFactory(new PropertyValueFactory<Value, String>("time"));

		temp1.setResizable(false);
		temp1.setCellValueFactory(new PropertyValueFactory<Value, String>("value1"));

		press1.setResizable(false);
		press1.setCellValueFactory(new PropertyValueFactory<Value, String>("value2"));

		rev1.setResizable(false);
		rev1.setCellValueFactory(new PropertyValueFactory<Value, String>("value3"));

		tableS1.setItems(tbldataS1);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createTableS2() {

		time2.setResizable(false);
		time2.setCellValueFactory(new PropertyValueFactory<Value, String>("time"));

		temp2.setResizable(false);
		temp2.setCellValueFactory(new PropertyValueFactory<Value, String>("value1"));

		press2.setResizable(false);
		press2.setCellValueFactory(new PropertyValueFactory<Value, String>("value2"));

		rev2.setResizable(false);
		rev2.setCellValueFactory(new PropertyValueFactory<Value, String>("value3"));

		tableS2.setItems(tbldataS2);

	}

	public void createList(ListView list, ObservableList<String> items) {
		list.setItems(items);
		list.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {

			@Override
			public ListCell<String> call(ListView<String> param) {
				return new TextFieldListCell<>(new StringConverter<String>() {

					@Override
					public String toString(String object) {
						return object;
					}

					@Override
					public String fromString(String string) {
						return string;
					}
				});
			}
		});

	}

	public void addItemsToListView(ListView list, String itemName) {
		list.getItems().add(list.getItems().size(), itemName);
		list.scrollTo(itemName);
		list.edit(list.getItems().size() - 1);
	}

	public void deleteItemsToListView(ListView list) throws NullPointerException {
		list.getItems().remove(list.getSelectionModel().getSelectedIndex());
		list.edit(list.getItems().size() - 1);
	}

	public void getSelectedItemS1(ListView list) throws NullPointerException {

		String s = list.getSelectionModel().getSelectedItem().toString();

		String[] splitted = s.split("\\.");

		db.loadDBS1(splitted[0]);

	}

	public void deleteSelectedItemS1(ListView list) throws NullPointerException {

		String s = list.getSelectionModel().getSelectedItem().toString();

		String[] splitted = s.split("\\.");

		db.deleteDBS1(splitted[0]);

		deleteItemsToListView(list1);

		if (list1.getSelectionModel().isEmpty()) {
			db.messIDS1 = 1;
		}

	}

	public void getSelectedItemS2(ListView list) throws NullPointerException {

		String s = list.getSelectionModel().getSelectedItem().toString();

		String[] splitted = s.split("\\.");

		db.loadDBS2(splitted[0]);

	}

	public void deleteSelectedItemS2(ListView list) throws NullPointerException {

		String s = list.getSelectionModel().getSelectedItem().toString();

		String[] splitted = s.split("\\.");

		db.deleteDBS2(splitted[0]);

		deleteItemsToListView(list2);

		if (list2.getSelectionModel().isEmpty()) {
			db.messIDS2 = 1;
		}

	}

	public void createPopUp() {

		HBox hbox = new HBox();
		VBox pane = new VBox();

		Label label = new Label("Wollen Sie die aufgenommenen Daten Speichern?");
		Label space = new Label();
		Label space2 = new Label("          ");
		Button yes = new Button("JA");
		yes.setPrefWidth(100);
		Button no = new Button("NEIN");
		no.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				newWindow.close();
			}
		});
		no.setPrefWidth(100);

		pane.getChildren().add(label);
		pane.getChildren().add(space);
		pane.getChildren().add(hbox);
		hbox.getChildren().add(yes);
		hbox.getChildren().add(space2);
		hbox.getChildren().add(no);

		Scene secondScene = new Scene(pane, 230, 80);

		// New window (Stage)
		Stage newWindow = new Stage();
		newWindow.setTitle("Speichern");
		newWindow.setResizable(false);
		newWindow.setScene(secondScene);

		newWindow.show();

	}

	public void visualiseDataS1() {

		for (int x = 0; x < db.tempValuesS1.size(); x++) {

			ser.getData().add(new XYChart.Data(db.timeValuesS1.get(x), db.tempValuesS1.get(x)));
			ser2.getData().add(new XYChart.Data(db.timeValuesS1.get(x), db.presValuesS1.get(x)));
			ser3.getData().add(new XYChart.Data(db.timeValuesS1.get(x), db.revValuesS1.get(x)));

			tbldataS1.add(new Value(db.timeValuesS1.get(x), String.valueOf(db.tempValuesS1.get(x)),
					String.valueOf(db.presValuesS1.get(x)), String.valueOf(db.revValuesS1.get(x))));

		}

	}

	public void visualiseDataS2() {

		for (int x = 0; x < db.tempValuesS2.size(); x++) {

			ser4.getData().add(new XYChart.Data(db.timeValuesS2.get(x), db.tempValuesS2.get(x)));
			ser5.getData().add(new XYChart.Data(db.timeValuesS2.get(x), db.presValuesS2.get(x)));
			ser6.getData().add(new XYChart.Data(db.timeValuesS2.get(x), db.revValuesS2.get(x)));

			tbldataS2.add(new Value(db.timeValuesS2.get(x), String.valueOf(db.tempValuesS2.get(x)),
					String.valueOf(db.presValuesS2.get(x)), String.valueOf(db.revValuesS2.get(x))));

		}

	}
}
