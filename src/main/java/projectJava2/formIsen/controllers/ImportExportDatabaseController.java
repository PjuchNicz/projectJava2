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
/**
 * La classe <b>ImportExportDatabaseController</b> permet de contrôler le FXML ImportExportDatabase, permettant 
 * d'importer un csv ou txt dans la database,
 * ou d'exporter la database en vcard, en csv ou txt
 * 
 */
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
	
	@FXML
	Text textExport;
	
	@FXML
	Text textImport;
	
	/**
	 * La methode <b>initialize</b> permet d'initialiser les RadioButton par groupe 
	 */
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
	
	/**
	 * La methode <b>handleExportButton</b> permet d'exporter à l'action du boutton <i>Export</i> dans la database dans un fichier dont le nom est inscrit dans le champs correspondant.
	 * On peut choisir de soit les exporter en csv ou txt
	 * Soit tout exporter en format vcf dans le dossier vcard
	 */
	@FXML
	public void handleExportButton() throws IOException {
		try {
			 if (group1.getSelectedToggle() != null && !getField1().equals("")){
	             RadioButton button = (RadioButton) group1.getSelectedToggle();
	             String field1_get = getField1();
	             Export e = new Export(field1_get+"."+button.getText(),";");
	             e.exportDataBase();
	             textExport.setText("Exported !");
			}
		} catch(Exception e){
			textExport.setText("The file name is incorrect");
		}
	}
	
	/**
	 * La methode <b>handleImportButton</b> permet d'importer à l'action du boutton <i>Import</i> un fichier txt ou csv avec le nom correspondant dans le champs associé
	 */
	@FXML
	public void handleImportButton() throws IOException {
		try {
			if (group2.getSelectedToggle() != null) {
	            RadioButton button = (RadioButton) group2.getSelectedToggle();
	            String field2_get = getField2();
	            Import i = new Import(field2_get+"."+button.getText(),";");
	            i.importDataBase();
	            textImport.setText("Imported !");
			}
		} catch(Exception e){
			textImport.setText("The file name is incorrect or doesn't exist");
		}
	}
}
