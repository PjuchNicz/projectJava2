package projectJava2.formIsen.transport;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;

import projectJava2.formIsen.person.Person;

public class VcardFactory {

	public VcardFactory() {
		
	
	
	}
	public String personToVCard(Person person) {
		String text = "BEGIN:VCARD\r\nVERSION:2.1\r\n";
		text += "FN:"+person.getFirstname()+" "+person.getLastname()+"\r\n";
		text += "N:"+person.getFirstname()+";"+person.getLastname();
		if(person.getNickname().equals("")) {
			text+=";"+person.getNickname();
		}
		text += "\r\n";
		text += "TEL;CELL:"+person.getPhone_number()+"\r\n";
		text +=  "ADR;TYPE=home:;;Rue;"+person.getAddress()+";Pays;CodePostal\r\n";
		text += "EMAIL;INTERNET:"+person.getEmail_address()+"\r\n";
		DateTimeFormatter fmt1 = DateTimeFormatter.BASIC_ISO_DATE;
		text += "BDAY:"+person.getBirth_date().format(fmt1)+"\r\n";
		text += "UID:\r\nEND:VCARD";
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
}
