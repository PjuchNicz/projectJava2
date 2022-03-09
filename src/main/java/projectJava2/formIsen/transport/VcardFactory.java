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
			
			List<String> list=new ArrayList<String>(Arrays.asList(null,null,null,null,null,null,null,null));
			String line;
			   while ((line = bufferedReader.readLine()) != null) {
				   String[] splitLineList = line.split(":");
				   String type = splitLineList[0].split(";")[0];
				   String value = splitLineList[splitLineList.length - 1];
				   switch(type) {
				   case "UID":
				     list.set(0,value);
				     break;
				   case "N":
					 String[] lastNameFirstName = value.split(";");
					 list.set(1,lastNameFirstName[0]);
					 list.set(2,lastNameFirstName[1]);
				     break;
				   case "FN":
					     list.set(3,value);
					     break;
				   case "TEL":
					     list.set(4,value);
					     break;
				   case "ADR":
					     list.set(5,value.replace(";"," "));
					     break;
				   case "EMAIL":
					     list.set(6,value);
					     break;
				   case "BDAY":
					     list.set(7,value);
					     break;
				   default:
					  break;
				 }
			   }  
			System.out.println(list.toString());
			Person personne = new Person(
					Integer.parseInt(list.get(0)),
					list.get(1),
					list.get(2),
					list.get(3),
					list.get(4),
					list.get(5),
					list.get(6),
					LocalDate.parse(list.get(7)));
			return personne;
		}
		else {
			System.out.println("Vcard : No such Vcard in vcard directory");
			return null;
		}
		
		
	}
}
