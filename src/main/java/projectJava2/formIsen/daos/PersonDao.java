package projectJava2.formIsen.daos;

import projectJava2.formIsen.person.Person;

import javax.management.RuntimeErrorException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static projectJava2.formIsen.daos.DataSourceFactory.getDataSource;

public class PersonDao {

    /**
     * SELECT * FROM person
     * @return List<Person> : liste avec toutes les {@link Person}
     */
    public List<Person> listPersons() {
        List<Person> listOfPersons = new ArrayList<>();
        try (Connection connection = getDataSource().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet results = statement.executeQuery("SELECT * FROM person")) {
                    while (results.next()) {
                        Person person = new Person(results.getInt("idperson"),
                                results.getString("lastname"),
                                results.getString("firstname"),
                                results.getString("nickname"),
                                results.getString("phone_number"),
                                results.getString("address"),
                                results.getString("email_address"),
                                results.getDate("birth_date").toLocalDate());
                        listOfPersons.add(person);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfPersons;
    }

    /**
     * SELECT * FROM person WHERE firstname=?
     * @param firstname : firstname de la personne recherchée
     * @return List<Person> : liste avec les {@link Person} trouvées
     */
    public List<Person> listPersonsByFirstname(String firstname) {
        List<Person> listOfPersons = new ArrayList<>();
        try (Connection connection = getDataSource().getConnection()) {
            String sqlQuery = "SELECT * FROM person WHERE firstname=?";
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, firstname);
                try (ResultSet results = statement.executeQuery()) {
                    while (results.next()) {
                        Person person = new Person(results.getInt("idperson"),
                                results.getString("lastname"),
                                results.getString("firstname"),
                                results.getString("nickname"),
                                results.getString("phone_number"),
                                results.getString("address"),
                                results.getString("email_address"),
                                results.getDate("birth_date").toLocalDate());
                        listOfPersons.add(person);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfPersons;
    }

    /**
     * SELECT * FROM person WHERE email_address=?
     * @param email_address : email_address de la personne recherchée
     * @return Person : {@link Person} UNIQUE trouvée
     */
    public Person personByEmailAddress(String email_address) {
        Person person = new Person();
        try (Connection connection = getDataSource().getConnection()) {
            String sqlQuery = "SELECT * FROM person WHERE email_address=?";
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, email_address);
                try (ResultSet results = statement.executeQuery()) {
                    person.setId(results.getInt("idperson"));
                    person.setLastname(results.getString("lastname"));
                    person.setFirstname(results.getString("firstname"));
                    person.setNickname(results.getString("nickname"));
                    person.setPhone_number(results.getString("phone_number"));
                    person.setAddress(results.getString("address"));
                    person.setEmail_address(results.getString("email_address"));
                    person.setBirth_date(results.getDate("birth_date").toLocalDate());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    /**
     * SELECT * FROM person WHERE lastname=? AND firstname=?
     * Si l'une des caractéristiques n'est pas connue : Utiliser {@link #listPersonsByLastnameOrFirstname(String, String)}
     * @param lastname : lastname de la personne recherchée
     * @param firstname : firstname de la personne recherchée
     * @return List<Person> : liste avec les {@link Person} trouvées
     */
    public List<Person> listPersonsByLastnameAndFirstname(String lastname, String firstname) {
        List<Person> listOfPersons = new ArrayList<>();
        try (Connection connection = getDataSource().getConnection()) {
            String sqlQuery = "SELECT * FROM person WHERE lastname=? AND firstname=?";
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, lastname);
                statement.setString(2, firstname);
                try (ResultSet results = statement.executeQuery()) {
                    while (results.next()) {
                        Person person = new Person(results.getInt("idperson"),
                                results.getString("lastname"),
                                results.getString("firstname"),
                                results.getString("nickname"),
                                results.getString("phone_number"),
                                results.getString("address"),
                                results.getString("email_address"),
                                results.getDate("birth_date").toLocalDate());
                        listOfPersons.add(person);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfPersons;
    }

    /**
     * Trouve une personne avec son firstname ou son lastname.
     * Si l'une des caractéristiques n'est pas connue : Mettre null
     * @param lastname : lastname de la personne recherchée
     * @param firstname : firstname de la personne recherchée
     * @return List<Person> : liste avec les {@link Person} trouvées
     */
    public List<Person> listPersonsByLastnameOrFirstname(String lastname, String firstname) {
        List<Person> listOfPersons = new ArrayList<>();
        try (Connection connection = getDataSource().getConnection()) {
            String sqlQuery = "SELECT * FROM person WHERE lastname=? OR firstname=?";
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, lastname);
                statement.setString(2, firstname);
                try (ResultSet results = statement.executeQuery()) {
                    while (results.next()) {
                        Person person = new Person(results.getInt("idperson"),
                                results.getString("lastname"),
                                results.getString("firstname"),
                                results.getString("nickname"),
                                results.getString("phone_number"),
                                results.getString("address"),
                                results.getString("email_address"),
                                results.getDate("birth_date").toLocalDate());
                        listOfPersons.add(person);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfPersons;
    }

    /**
     * Ajoute dans la bdd une nouvelle personne uniquement si celle si n'est pas déjà présente dans la BDD
     * Verification sur email_address + phone_number étant unique
     * INSERT OR IGNORE INTO person(lastname,firstname,nickname,phone_number,address,email_address,birth_date)
     * @param lastname : lastname de la personne recherchée
     * @param firstname : firstname de la personne recherchée
     * @param nickname : nickname de la personne recherchée
     * @param phone_number : phone_number de la personne recherchée
     * @param address : address de la personne recherchée
     * @param email_address : email_address de la personne recherchée
     * @param birth_date : birth_date de la personne recherchée
     * @return Person : {@link Person} ajoutée
     */
    public Person addPerson(String lastname, String firstname, String nickname, String phone_number, String address, String email_address, LocalDate birth_date) {
        try (Connection connection = getDataSource().getConnection()) {
            String sqlQuery = "INSERT OR IGNORE INTO person(lastname,firstname,nickname,phone_number,address,email_address,birth_date)" + "VALUES(?,?,?,?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(
                    sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, lastname);
                statement.setString(2, firstname);
                statement.setString(3, nickname);
                statement.setString(4, phone_number);
                statement.setString(5, address);
                statement.setString(6, email_address);
                statement.setDate(7, Date.valueOf(birth_date));

                statement.executeUpdate();
                ResultSet ids = statement.getGeneratedKeys();
                if (ids.getInt(1) == 0) {
                    //TODO Trouver un moyen d'avoir un répertoire des conflits
                    System.out.println("La personne existe déjà dans la bdd");
                    return null;
                }
                if (ids.next()) {
                    return new Person(ids.getInt(1),lastname, firstname, nickname, phone_number, address, email_address, birth_date);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Modifie les attributs d'une personne existante dans la BDD
     * La recherche se fait avec idperson
     * La personne à modifier doit avoir un idperson valide et existant dans la BDD
     * @param person : {@link Person} à modifier
     */
    public void modifyPerson(Person person) {
        try (Connection connection = getDataSource().getConnection()) {
            String sqlQuery = "UPDATE person set lastname=?, firstname=?, nickname=?, phone_number=?, address=?," +
                    "email_address=?, birth_date=? WHERE idperson=?";
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, person.getLastname());
                statement.setString(2, person.getFirstname());
                statement.setString(3, person.getNickname());
                statement.setString(4, person.getPhone_number());
                statement.setString(5, person.getAddress());
                statement.setString(6, person.getEmail_address());
                statement.setDate(7, Date.valueOf(person.getBirth_date()));
                statement.setInt(8, person.getId());
                int nbRows = statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprimer une personne dans la BDD
     * La recherche se fait avec idperson
     * La personne à supprimer doit avoir un idperson valide et existant dans la BDD
     * @param person : {@link Person} à supprimer
     */
    public void deletePerson(Person person) {
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement statement =
                         connection.prepareStatement("DELETE FROM PERSON WHERE idperson=?")) {
                statement.setInt(1, person.getId());
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
    }
}