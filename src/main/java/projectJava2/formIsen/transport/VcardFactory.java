package projectJava2.formIsen.transport;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import projectJava2.formIsen.daos.PersonDao;
import projectJava2.formIsen.person.Person;

public class VcardFactory {

	public VcardFactory() {
	}

	public String personToVCard(Person person) {
		String text = "BEGIN:VCARD\r\nVERSION:4.0\r\n";
		text += "UID:"+person.getId()+"\r\n";
		if(person.getNickname().equals("")) {
			text+="FN:"+person.getNickname()+"\r\n";
		}
		else {
			text += "FN:"+person.getLastname()+" "+person.getFirstname()+"\r\n";
		}
		text += "N:"+person.getLastname()+";"+person.getFirstname()+"\r\n";
		
		
		text += "TEL;CELL:"+person.getPhone_number()+"\r\n";
		text +=  "ADR;TYPE=home:;;Rue;"+person.getAddress()+";Pays;CodePostal\r\n";
		text += "EMAIL;INTERNET:"+person.getEmail_address()+"\r\n";
		DateTimeFormatter fmt1 = DateTimeFormatter.BASIC_ISO_DATE;
		text += "BDAY:"+person.getBirth_date().format(fmt1)+"\r\n";
		for(String p : person.getFriend_list()) {
			text += "RELATED;TYPE=contact:mailto:"+p;
		}
		
		text += "END:VCARD";
		return text;
	}
	public void vcardCreator(Person person) throws IOException {
		String projectDirectory = System.getProperty("user.dir");
		Path root = Paths.get(projectDirectory+"/vcard");
		BufferedWriter bufferedWriter = Files.newBufferedWriter(root.resolve(person.getFirstname()+person.getLastname()+".vcf"), StandardCharsets.UTF_8);
		try {
			bufferedWriter.write(personToVCard(person));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bufferedWriter.flush();
	}

	public Person cardToPerson(String FirstName,String LastName) throws IOException {
		String projectDirectory = System.getProperty("user.dir");
		Path root = Paths.get(projectDirectory+"/vcard");
		Path path = root.resolve(FirstName+LastName+".vcf");
		if(Files.exists(path)) {
			BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
			String line, lastname = null, firstname = null, nickname = null, phone_number = null, address = null, email_address = null;
			List<String> friend_list = new ArrayList<String>();
			int uid;
			LocalDate birth_date = null;
			while ((line = bufferedReader.readLine()) != null) {
			   String[] splitLineList = line.split(":");
			   String type = splitLineList[0].split(";")[0];
			   String value = splitLineList[splitLineList.length - 1];
			   switch(type) {
				   case "UID" -> uid = Integer.parseInt(value);
				   case "N" -> {
					   String[] lastNameFirstName = value.split(";");
					   lastname = lastNameFirstName[0];
					   firstname = lastNameFirstName[1];
				   }
				   case "FN" -> nickname = value;
				   case "TEL" -> phone_number = value;
				   case "ADR" -> address = value.replace(";"," ");
				   case "EMAIL" -> email_address = value;
				   case "BDAY" -> birth_date = LocalDate.parse(value);
				   case "RELATED" -> friend_list.add(value);
			   }
			}
			PersonDao personDao = new PersonDao();
			return personDao.addPerson(lastname, firstname, nickname, phone_number, address, email_address, birth_date,friend_list.toArray(new String[0]));
		}
		else {
			System.out.println("Vcard : No such Vcard in vcard directory");
			return null;
		}
	}
}
