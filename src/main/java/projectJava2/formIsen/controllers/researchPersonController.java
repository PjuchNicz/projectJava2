/**
 * 
 */
package projectJava2.formIsen.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import projectJava2.formIsen.App;
import projectJava2.formIsen.person.Person;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import projectJava2.formIsen.daos.PersonDao;

public class researchPersonController {
	@FXML
	public TextField firstname;
	
	@FXML
	public TextField lastname;
	
	@FXML
	public Text displayResult;
	
	@FXML
	List<String> listOfPersonsString = new ArrayList<>();
	
	@FXML
	public void handleLaunchButton() throws IOException {
		// Here we make use of our new method allowing us to change views inside the main Parent		
		String firstname_send = getTextFirstname();
		String lastname_send = getTextLastname();

		PersonDao personDao = new PersonDao();
		List<Person> listOfPersons = new ArrayList<>();
		listOfPersons = personDao.listPersonsByLastnameAndFirstname(lastname_send,firstname_send);
		for (int i = 0; i < listOfPersons.size();i++) {
			listOfPersonsString.add(listOfPersons.get(i).toString());
		}
		System.out.print(listOfPersons);
		displayResult();  

	}
	
	public String getTextFirstname() {
		return firstname.getText().toString();
	}
	
	public String getTextLastname() {
		return lastname.getText().toString();
	}
	
	public void displayResult() {
		System.out.print(listOfPersonsString);
		displayResult.setText(listOfPersonsString.get(0));
	}
	
	
}
