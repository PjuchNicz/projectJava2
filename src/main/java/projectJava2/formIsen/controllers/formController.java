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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import projectJava2.formIsen.daos.PersonDao;

public class formController {
	@FXML
	public TextField firstname;
	
	@FXML
	public TextField lastname;
	
	@FXML
	public TextField nickname;
	
	@FXML
	public TextField tel;
	
	@FXML
	public TextField email;
	
	@FXML
	public TextField address;
	
	@FXML
	public DatePicker birthdate;
	
	@FXML
	public Text displayFirstname;
	
	@FXML
	public Text displayLastname;
	
	@FXML
	public Text displayNickname;
	
	@FXML
	public Text displayTel;
	
	@FXML
	public Text displayEmail;
	
	@FXML
	public Text displayAddress;
	
	@FXML
	public Text displayBirthdate;
	
	@FXML
	public void handleLaunchButton() throws IOException {
		// Here we make use of our new method allowing us to change views inside the main Parent		
		Person person = new Person();
		person.setFirstname(getTextFirstname());
		person.setLastname(getTextLastname());
		person.setNickname(getTextNickname());
		person.setPhone_number(getTextTel());
		person.setEmail_address(getTextEmail());
		person.setAddress(getTextAdress());
		person.setBirth_date(getBirthdate());
		System.out.print("\n" + person.getFirstname());
		System.out.print("\n" + person.getLastname());
		System.out.print("\n" + person.getNickname());
		System.out.print("\n" + person.getPhone_number());
		System.out.print("\n" + person.getEmail_address());
		System.out.print("\n" + person.getAddress());
		System.out.print("\n" + person.getBirth_date());
		PersonDao personDao = new PersonDao();
		personDao.addPerson(person);
	}
	
	
	
	public String getTextFirstname() {
		return firstname.getText().toString();
	}
	
	public String getTextLastname() {
		return lastname.getText().toString();
	}
	
	public String getTextNickname() {
		return nickname.getText().toString();
	}
	
	public String getTextTel() {
		return tel.getText().toString();
	}
	
	public String getTextEmail() {
		return email.getText().toString();
	}
	
	public String getTextAdress() {
		return address.getText().toString();
	}
	
	public LocalDate getBirthdate() {
		return birthdate.getValue();
	}
	
	public void displayTextFirstname() {
		displayFirstname.setText(getTextFirstname());
	}
	
	public void displayTextLastname() {
		displayLastname.setText(getTextLastname());
	}
	
	public void displayTextNickname() {
		displayNickname.setText(getTextNickname());
	}
	
	public void displayTextTel() {
		displayTel.setText(getTextTel());
	}
	
	public void displayTextAddress() {
		displayAddress.setText(getTextAdress());
	}
	
	public void displayTextEmail() {
		displayEmail.setText(getTextEmail());
	}
	
	public void displayTextBirthdate() {
		displayBirthdate.setText(getBirthdate().toString());
	}
}
