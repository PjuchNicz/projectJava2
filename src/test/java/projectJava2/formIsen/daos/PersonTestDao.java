package projectJava2.formIsen.daos;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import org.junit.Before;
import org.junit.Test;
import projectJava2.formIsen.person.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
                + "VALUES (1, 'LastName', 'FirstName', 'NickName', '0600000000', '1 rue Rue', 'adress@gmail.com','2015-11-29 00:00:00.000')");
        stmt.close();
        connection.close();
    }

    @Test
    public void shouldListPersons() {
        // WHEN
        List<Person> persons = personDao.listPersons();
        // THEN
        assertThat(persons).hasSize(1);
        assertThat(persons).extracting("idperson", "lastname", "firstname", "nickname", "phone_number", "address", "email_address", "birth_date")
                .containsOnly(tuple(1, "LastName", "FirstName", "NickName", "0600000000", "1 rue Rue", "adress@gmail.com", LocalDateTime.parse("2015-11-29T00:00:00.000").toLocalDate()));
    }

    @Test
    public void shouldAddPerson() throws Exception {
        // WHEN
        Person person = new Person(null, "juch", "pierre", "pierro", "0611111111",
                "rue","juch.pierre@",LocalDateTime.parse("2015-11-29T00:00:00.000").toLocalDate());
        personDao.addPerson(person);
        // THEN
        Connection connection = DataSourceFactory.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM person WHERE lastname='juch'");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getInt("idperson")).isNotNull();
        assertThat(resultSet.getString("lastname")).isEqualTo("juch");
        assertThat(resultSet.next()).isFalse();
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void shouldListPersonsByFirstname() {
        // WHEN
        List<Person> persons = personDao.listPersonsByFirstname("FI%");
        // THEN

    }
}
