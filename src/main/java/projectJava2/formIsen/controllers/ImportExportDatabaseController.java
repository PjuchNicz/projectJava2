/**
 * 
 */
package projectJava2.formIsen.controllers;

import java.io.IOException;
import java.time.LocalDate;

import projectJava2.formIsen.App;
import projectJava2.formIsen.person.Person;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import projectJava2.formIsen.daos.PersonDao;

public class ImportExportDatabaseController {
	// Group
	ToggleGroup group = new ToggleGroup();

	// Radio 1: txt
	@FXML
	RadioButton button1;


	// Radio 2: csv.
	@FXML
	RadioButton button2;
	
	// Radio 3: vcard.
	@FXML
	RadioButton button3;
	
	@FXML
	TextField field1;
	
	public void initialize() {
		button1.setToggleGroup(group);
		button2.setToggleGroup(group);
		button3.setToggleGroup(group);
		button1.setSelected(true);
	}
	
	public String getField1() {
		return field1.getText().toString();
	}
	
	@FXML
	public void handleLaunchButton() throws IOException {
		 if (group.getSelectedToggle() != null) {
             RadioButton button = (RadioButton) group.getSelectedToggle();
             String field1_get = getField1();
             System.out.println(field1_get + button.getText());
		}
	}
}
