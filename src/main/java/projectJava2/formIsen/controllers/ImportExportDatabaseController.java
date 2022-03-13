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
import projectJava2.formIsen.transport.Export;
import projectJava2.formIsen.transport.Import;

public class ImportExportDatabaseController {
	// Group
	ToggleGroup group1 = new ToggleGroup();
	ToggleGroup group2 = new ToggleGroup();
	// Radio 1: txt
	@FXML
	RadioButton button1;

	// Radio 2: csv.
	@FXML
	RadioButton button2;
	
	// Radio 3: vcard.
	@FXML
	RadioButton button3;
	
	// Radio 4: txt
	@FXML
	RadioButton button4;

	// Radio 5: csv.
	@FXML
	RadioButton button5;
	
	// Radio 6: vcard.
	//@FXML
	//RadioButton button6;
	
	
	@FXML
	TextField field1;
	
	@FXML
	TextField field2;
	
	public void initialize() {
		button1.setToggleGroup(group1);
		button2.setToggleGroup(group1);
		button3.setToggleGroup(group1);
		button1.setSelected(true);
		button4.setToggleGroup(group2);
		button5.setToggleGroup(group2);
		//button6.setToggleGroup(group2);
		button4.setSelected(true);
	}
	
	public String getField1() {
		return field1.getText().toString();
	}
	
	public String getField2() {
		return field2.getText().toString();
	}
	
	@FXML
	public void handleExportButton() throws IOException {
		 if (group1.getSelectedToggle() != null) {
             RadioButton button = (RadioButton) group1.getSelectedToggle();
             String field1_get = getField1();
             System.out.println(field1_get + button.getText());
             Export e = new Export(field1_get+"."+button.getText(),";");
             e.exportDataBase();
		}
	}
	
	@FXML
	public void handleImportButton() throws IOException {
		if (group2.getSelectedToggle() != null) {
            RadioButton button = (RadioButton) group2.getSelectedToggle();
            String field2_get = getField2();
            System.out.println(field2_get+"."+button.getText());
            Import i = new Import(field2_get+"."+button.getText(),";");
            i.importDataBase();
		}
	}
}
