package de.hft.wiinf.ss.ecorp.table;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import de.hft.wiinf.ss.ecorp.consumer.InitSensor1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FXMLTableController implements Initializable {

	InitSensor1 sensor;
	@FXML
	private TableColumn time;
	@FXML
	private TableColumn temp;
	@FXML
	private TableColumn press;
	@FXML
	private TableColumn rev;
	@FXML
	Label sensorId;
	@FXML
	Label sensorTypNr;
	@FXML
	private TableView table;

	private final ObservableList<Value> data = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	public void dataChangedTable() {

		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss.SSS");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		String StringTime = sdf.format(time);
		data.add(new Value(StringTime, String.valueOf(sensor.temp), String.valueOf(sensor.pressure),
				String.valueOf(sensor.rev)));

	}

}
