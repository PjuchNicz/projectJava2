package projectJava2.formIsen.controllers;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import projectJava2.formIsen.App;
/**
 * La classe <b>MainLayoutController</b> est un borderPan qui sera le cadre principal de notre application, qui sera géré par des onglets
 */
public class MainLayoutController {
	
	@FXML
	public TabPane mainTab;
	
	public void closeApplication() {
		Platform.exit();
	}

	public void gotoHome() throws IOException {
		try {
		App.showView("HomeScreen");
		}
		catch(Exception e){
			System.out.println("Loaded");
		}
	}
	
	public void gotoFormAdd() {
		App.showView("Form");
	}
	
	public void gotoResearchPerson() {
		App.showView("ResearchPerson");
	}
	
	public void gotoImportExportDatabase() {
		App.showView("ImportExportDatabase");
	}
	
}
