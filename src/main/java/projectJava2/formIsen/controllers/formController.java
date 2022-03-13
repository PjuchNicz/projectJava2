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

/**
 * La classe <b>formController</b> permet de contrôler le FXML Form, permettant d'ajouter une nouvelle personne dans la DataBase
 */
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
	public Text champs_vide;

	private final PersonDao personDao = new PersonDao();

	/**
	 * La méthode <b>handleLaunchButton</b> est active quand on actionne le bouton <i>send</i> afin d'essayer d'ajouter la personne si les champs sont donc tous remplis
	 * en respectant la bonne syntax
	 */
	@FXML
	public void handleLaunchButton() throws IOException {
		String firstname_send = getTextFirstname();
		String lastname_send = getTextLastname();
		String nickname_send = getTextNickname();
		String phone_number_send = getTextTel();
		String email_address_send = getTextEmail();
		String address_send = getTextAdress();
		LocalDate birth_date_send = getBirthdate();

		try {
			personDao.addPerson(lastname_send,firstname_send,nickname_send,phone_number_send,address_send,email_address_send,birth_date_send,new String[]{""});
			firstname.setText("");
			lastname.setText("");
			nickname.setText("");
			tel.setText("");
			email.setText("");
			address.setText("");
			birthdate.setValue(null);
			champs_vide.setText("Person added !");
		} catch(NullPointerException e){
			champs_vide.setText("One or multiple fields are empty or wrong");
		}
	}

	public String getTextFirstname() {
		return firstname.getText();
	}

	public String getTextLastname() {
		return lastname.getText();
	}

	public String getTextNickname() {
		return nickname.getText();
	}

	public String getTextTel() {
		return tel.getText();
	}

	public String getTextEmail() {
		return email.getText();
	}

	public String getTextAdress() {
		return address.getText();
	}

	public LocalDate getBirthdate() {
		return birthdate.getValue();
	}

}