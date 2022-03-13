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
	public Text champs_vide;
	
	@FXML
	public void handleLaunchButton() throws IOException {
		// Here we make use of our new method allowing us to change views inside the main Parent		
		String firstname_send = getTextFirstname();
		String lastname_send = getTextLastname();
		String nickname_send = getTextNickname();
		String phone_number_send = getTextTel();
		String email_address_send = getTextEmail();
		String address_send = getTextAdress();
		LocalDate birth_date_send = getBirthdate();

		PersonDao personDao = new PersonDao();
		
		try {
			Person person = new Person();
			person = personDao.addPerson(lastname_send,firstname_send,nickname_send,phone_number_send,address_send,email_address_send,birth_date_send,new String[]{""});
			firstname.setText("");
			lastname.setText("");
			nickname.setText("");
			tel.setText("");
			email.setText("");
			address.setText("");
			birthdate.setValue(null);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Un ou plusieurs champs sont vides");
			champs_vide.setText("Un ou plusieurs champs sont vides");
		}
		

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
		//displayFirstname.setText(getTextField1());
	}
	
	public void displayTextLastname() {
		//displayLastname.setText(getTextField2());
	}
	
	public void displayTextNickname() {
		//displayNickname.setText(getTextNickname());
	}
	
	public void displayTextTel() {
		//displayTel.setText(getTextTel());
	}
	
	public void displayTextAddress() {
		//displayAddress.setText(getTextAdress());
	}
	
	public void displayTextEmail() {
		//displayEmail.setText(getTextEmail());
	}
	
	public void displayTextBirthdate() {
		//displayBirthdate.setText(getBirthdate().toString());
	}
}
