package projectJava2.formIsen.daos;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import org.junit.Before;
import org.junit.Test;
import projectJava2.formIsen.person.Person;

import java.sql.Connection;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class PersonTestDao {

    private PersonDao personDao = new PersonDao();

    @Before
    public void initDb() throws Exception {
        Connection connection = DataSourceFactory.getDataSource().getConnection();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS person (\r\n"
                        + "  idperson INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\r\n"
                        + "  lastname VARCHAR(45) NOT NULL,\r\n"
                        + "  firstname VARCHAR(45) NOT NULL,\r\n"
                        + "  nickname VARCHAR(45) NOT NULL,\r\n"
                        + "  phone_number VARCHAR(15) NULL,\r\n"
                        + "  address VARCHAR(200) NULL,\r\n"
                        + "  email_address VARCHAR(150) NULL,\r\n"
                        + "  birth_date DATE NULL);");
        stmt.executeUpdate("DELETE FROM person");
        stmt.executeUpdate("INSERT INTO person(idperson, lastname, firstname, nickname, phone_number, address, email_address, birth_date) "
                + "VALUES (1, 'LastName', 'FirstName', 'NickName', '0600000000', '1 rue Rue', 'adress@gmail.com','2015-12-12 12:00:00.000')");
        stmt.close();
        connection.close();
    }

    @Test
    public void shouldListPersons() throws ParseException {
        // WHEN
        List<Person> persons = personDao.listPersons();
        // THEN
        String str = "2015-12-12 12:00:00.000";
        Date date1=new SimpleDateFormat("yyyy-dd-MM").parse(str);

        assertThat(persons).hasSize(1);
        assertThat(persons).extracting("idperson", "lastname", "firstname", "nickname", "phone_number", "address", "email_address", "birth_date")
                .containsOnly(tuple(1, "LastName", "FirstName", "NickName", "0600000000", "1 rue Rue", "adress@gmail.com", date1));
    }

}
