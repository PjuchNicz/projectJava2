package projectJava2.formIsen.daos;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static projectJava2.formIsen.daos.DataSourceFactory.initDb;

import org.junit.Before;
import org.junit.Test;
import projectJava2.formIsen.person.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class PersonTestDao {

    private PersonDao personDao = new PersonDao();

    @Before
    public void initDbTest() throws Exception {
        initDb();
        Connection connection = DataSourceFactory.getDataSource().getConnection();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM person");
        stmt.executeUpdate("INSERT INTO person(idperson, lastname, firstname, nickname, phone_number, address, email_address, birth_date) "
                + "VALUES (1, 'LastName1', 'FirstName1', 'NickName1', '0100000000', '1 rue Rue', 'address1@gmail.com','2015-01-28 00:00:00.000')");
        stmt.executeUpdate("INSERT INTO person(idperson, lastname, firstname, nickname, phone_number, address, email_address, birth_date) "
                + "VALUES (2, 'LastName2', 'FirstName2', 'NickName2', '0200000000', '2 rue Rue', 'address2@gmail.com','2015-02-28 00:00:00.000')");
        stmt.executeUpdate("INSERT INTO person(idperson, lastname, firstname, nickname, phone_number, address, email_address, birth_date) "
                + "VALUES (3, 'LastName3', 'FirstName2', 'NickName3', '0300000000', '3 rue Rue', 'address3@gmail.com','2015-03-28 00:00:00.000')");
        stmt.close();
        connection.close();
    }

    @Test
    public void shouldListPersons() {
        // WHEN
        List<Person> persons = personDao.listPersons();
        // THEN
        assertThat(persons).hasSize(3);
        assertThat(persons).extracting("idperson", "lastname", "firstname", "nickname", "phone_number", "address", "email_address", "birth_date")
                .containsOnly(tuple(1, "LastName1", "FirstName1", "NickName1", "0100000000", "1 rue Rue", "address1@gmail.com", LocalDateTime.parse("2015-01-28T00:00:00.000").toLocalDate()),
                        tuple(2, "LastName2", "FirstName2", "NickName2", "0200000000", "2 rue Rue", "address2@gmail.com", LocalDateTime.parse("2015-02-28T00:00:00.000").toLocalDate()),
                        tuple(3, "LastName3", "FirstName2", "NickName3", "0300000000", "3 rue Rue", "address3@gmail.com", LocalDateTime.parse("2015-03-28T00:00:00.000").toLocalDate()));
    }

    @Test
    public void shouldListPersonsByFirstname() {
        // WHEN
        List<Person> persons = personDao.listPersonsByFirstname("FirstName2");
        // THEN
        assertThat(persons).hasSize(2);
    }

    @Test
    public void shouldPersonByEmailAddress() {
        // WHEN
        Person person = personDao.personByEmailAddress("address1@gmail.com");
        // THEN
        assertThat(Objects.equals(person.getAddress(), "address1@gmail.com"));
    }

    @Test
    public void shouldListPersonsByLastnameAndFirstname() {
        // WHEN
        List<Person> personsListTrue = personDao.listPersonsByLastnameAndFirstname("LastName2", "FirstName2");
        List<Person> personsListFalse = personDao.listPersonsByLastnameAndFirstname("LastName2", "");
        // THEN
        assertThat(personsListTrue).hasSize(1);
        assertThat(personsListFalse).hasSize(0);
    }

    @Test
    public void shouldListPersonsByLastnameOrFirstname() {
        // WHEN
        List<Person> personsList1 = personDao.listPersonsByLastnameOrFirstname("LastName2", "FirstName2");
        List<Person> personsList2 = personDao.listPersonsByLastnameOrFirstname("LastName2", null);
        List<Person> personsList3 = personDao.listPersonsByLastnameOrFirstname(null, null);
        // THEN
        assertThat(personsList1).hasSize(2);
        assertThat(personsList2).hasSize(1);
        assertThat(personsList3).hasSize(0);
    }

    @Test
    public void shouldAddPerson() throws Exception {
        // WHEN
        personDao.addPerson("LastName4", "FirstName4", "NickName4",
                "0400000000", "4 rue Rue", "address4@gmail.com", LocalDateTime.parse("2015-04-28T00:00:00.000").toLocalDate());
        // THEN
        Connection connection = DataSourceFactory.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM person WHERE lastname='LastName4'");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getInt("idperson")).isNotNull();
        assertThat(resultSet.getString("firstname")).isEqualTo("FirstName4");
        assertThat(resultSet.next()).isFalse();
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void shouldModifyPerson() throws Exception {
        // WHEN
        Person person = personDao.personByEmailAddress("address3@gmail.com");
        person.setFirstname("FirstName3");
        personDao.modifyPerson(person);
        // THEN
        Connection connection = DataSourceFactory.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM person WHERE email_address='address3@gmail.com'");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getInt("idperson")).isNotNull();
        assertThat(resultSet.getString("firstname")).isEqualTo("FirstName3");
        assertThat(resultSet.next()).isFalse();
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void shouldDeletePerson() throws Exception {
        // WHEN
        Person person = personDao.personByEmailAddress("address3@gmail.com");
        personDao.deletePerson(person);
        // THEN
        Connection connection = DataSourceFactory.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM person WHERE email_address='address3@gmail.com'");
        assertThat(resultSet.next()).isFalse();
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void shouldNotAddPerson() throws SQLException {
        // WHEN
        Connection connection = DataSourceFactory.getDataSource().getConnection();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM person");
        Person newPerson1 = personDao.addPerson("juch", "pierre", "pierro", "0611111111",
                "rue", "juch.pierre@", LocalDateTime.parse("2015-11-29T00:00:00.000").toLocalDate());
        Person newPerson2 = personDao.addPerson("juch", "pierre", "pierro", "0611111111",
                "rue", "juch.pierre@", LocalDateTime.parse("2015-11-29T00:00:00.000").toLocalDate());
        List<Person> persons = personDao.listPersons();
        stmt.close();
        connection.close();
        // THEN
        assertThat(persons).hasSize(1);
    }
}
