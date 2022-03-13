/**
 *
 */
package projectJava2.formIsen.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import projectJava2.formIsen.person.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import projectJava2.formIsen.daos.PersonDao;

public class researchPersonController {
	@FXML
	public TextField field1;

	@FXML
	public TextField field2;
	
	@FXML
	public TextField displayResultID;
	
	@FXML
	public TextField displayResultFirstname;

	@FXML
	public TextField displayResultLastname;

	@FXML
	public TextField displayResultNickname;

	@FXML
	public TextField displayResultPhoneNumber;

	@FXML
	public TextField displayResultAddress;

	@FXML
	public TextField displayResultEmail;

	@FXML
	public DatePicker displayResultBirthdate;
	public Text text1;
	public Text text2;

	public Integer getTextdisplayResultInt() {
		return Integer.parseInt(displayResultID.getText().toString());
	}
	
	public String getTextdisplayResultFirstname() {
		return displayResultFirstname.getText().toString();
	}
	
	public String getTextdisplayResultLastname() {
		return displayResultLastname.getText().toString();
	}
	
	public String getTextdisplayResultNickname() {
		return displayResultNickname.getText().toString();
	}
	
	public String getTextdisplayResultPhoneNumber() {
		return displayResultPhoneNumber.getText().toString();
	}
	
	public String getTextdisplayResultEmail() {
		return displayResultEmail.getText().toString();
	}
	
	public String getTextdisplayResultAddress() {
		return displayResultAddress.getText().toString();
	}
	
	public LocalDate getdisplayResultBirthdate() {
		return displayResultBirthdate.getValue();
	}
	
	List<Person> listOfPersons = new ArrayList<>();

	@FXML
	TableView<Person> table = new TableView<Person>();
	@FXML private TableColumn<Person, Integer> userId;
	@FXML private TableColumn<Person, String> firstNameCol;
	@FXML private TableColumn<Person, String> lastNameCol;
	@FXML private TableColumn<Person, String> emailCol;


	@FXML
	public void initialize() {
		userId.setCellValueFactory(new PropertyValueFactory<>("idperson"));
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email_address"));


	}

	@FXML
	public void handleLaunchButton() throws IOException {
		// Here we make use of our new method allowing us to change views inside the main Parent		
		String field1Send = getTextField1();
		String field2Send = getTextField2();

		PersonDao personDao = new PersonDao();

		listOfPersons = personDao.listPersonsByLastnameAndFirstname(field2Send,field1Send);
		if (listOfPersons.size() == 0) {
			listOfPersons = personDao.listPersonsByLastnameOrFirstname(field2Send, field1Send);
		}

		ObservableList<Person> list = FXCollections.observableArrayList(listOfPersons);
		table.setItems(list);
	}

	@FXML
	public void handleModifyButton() throws IOException {
		PersonDao personDao = new PersonDao();
		try {
			Person person = new Person(getTextdisplayResultInt(),getTextdisplayResultLastname(),getTextdisplayResultFirstname(),getTextdisplayResultNickname(),getTextdisplayResultPhoneNumber(),getTextdisplayResultAddress(),getTextdisplayResultEmail(),getdisplayResultBirthdate(),new String[]{""});
			personDao.modifyPerson(person);
		}
		catch(Exception e) {
			System.out.println("Vous n'avez sélectionné personne");
		}
	}
	
	@FXML
	public void handleDeleteButton() throws IOException {
		
	}

	public String getTextField1() {
		return field1.getText().toString();
	}

	public String getTextField2() {
		return field2.getText().toString();
	}
	
	public void displayResultID(Person selectedPerson) {
		displayResultID.setText(selectedPerson.getIdperson().toString());
	}
	
	public void displayResultFirstname(Person selectedPerson) {
		displayResultFirstname.setText(selectedPerson.getFirstname());
	}

	public void displayResultNickname(Person selectedPerson) {
		displayResultNickname.setText(selectedPerson.getNickname());
	}

	public void displayResultLastname(Person selectedPerson) {
		displayResultLastname.setText(selectedPerson.getLastname());
	}

	public void displayResultPhoneNumber(Person selectedPerson) {
		displayResultPhoneNumber.setText(selectedPerson.getPhone_number());
	}

	public void displayResultAddress(Person selectedPerson) {
		displayResultAddress.setText(selectedPerson.getAddress());
	}

	public void displayResultEmail(Person selectedPerson) {
		displayResultEmail.setText(selectedPerson.getEmail_address());
	}

	public void displayResultBirthdate(Person selectedPerson) {
		displayResultBirthdate.setValue(selectedPerson.getBirth_date());
	}

	public void selectPerson() {
		Person selectedPerson = table.getSelectionModel().getSelectedItem();
		displayResultID(selectedPerson);
		displayResultFirstname(selectedPerson);
		displayResultLastname(selectedPerson);
		displayResultNickname(selectedPerson);
		displayResultPhoneNumber(selectedPerson);
		displayResultAddress(selectedPerson);
		displayResultEmail(selectedPerson);
		displayResultBirthdate(selectedPerson);
	}
}