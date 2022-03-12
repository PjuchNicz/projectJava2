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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import projectJava2.formIsen.daos.PersonDao;

public class researchPersonController {
	@FXML
	public TextField firstname;

	@FXML
	public TextField lastname;

	@FXML
	public Text displayResultFirstname;

	@FXML
	public Text displayResultLastname;

	@FXML
	public Text displayResultNickname;

	@FXML
	public Text displayResultPhoneNumber;

	@FXML
	public Text displayResultAddress;

	@FXML
	public Text displayResultEmail;

	@FXML
	public Text displayResultBirthdate;

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
		String firstname_send = getTextFirstname();
		String lastname_send = getTextLastname();

		PersonDao personDao = new PersonDao();

		listOfPersons = personDao.listPersonsByLastnameAndFirstname(lastname_send,firstname_send);
		if (listOfPersons.size() == 0) {
			listOfPersons = personDao.listPersonsByLastnameOrFirstname(lastname_send, firstname_send);
		}

		ObservableList<Person> list = FXCollections.observableArrayList(listOfPersons);
		table.setItems(list);


	}

	public String getTextFirstname() {
		return firstname.getText().toString();
	}

	public String getTextLastname() {
		return lastname.getText().toString();
	}

	public void displayResultFirstname() {
		displayResultFirstname.setText(listOfPersons.get(0).getFirstname());
	}

	public void displayResultNickname() {
		displayResultNickname.setText(listOfPersons.get(0).getNickname());
	}

	public void displayResultLasttname() {
		displayResultLastname.setText(listOfPersons.get(0).getLastname());
	}

	public void displayResultPhoneNumber() {
		displayResultPhoneNumber.setText(listOfPersons.get(0).getPhone_number());
	}

	public void displayResultAddress() {
		displayResultAddress.setText(listOfPersons.get(0).getAddress());
	}

	public void displayResultEmail() {
		displayResultEmail.setText(listOfPersons.get(0).getEmail_address());
	}

	public void displayResultBirthdate() {
		displayResultBirthdate.setText(listOfPersons.get(0).getBirth_date().toString());
	}

	public void selectPerson() {
		Person selectedPerson = table.getSelectionModel().getSelectedItem();
		System.out.println(selectedPerson);
	}
}