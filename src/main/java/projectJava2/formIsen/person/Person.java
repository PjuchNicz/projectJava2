package projectJava2.formIsen.person;

import java.time.LocalDate;
import java.util.*;


public class Person {
    private Integer idperson;
    private String lastname;
    private String firstname;
    private String nickname;
    private String phone_number;
    private String address;
    private String email_address;
    private LocalDate birth_date;
    private List<String> friend_list;

    public Person() {
    }

    /**
     * Constructeur de personne
     * @param idperson : id d'une personne UNIQUE dans la BDD
     * @param lastname : nom d'une personne
     * @param firstname : prenom d'une personne
     * @param nickname : surnom d'une personne
     * @param phone_number : numero de telephone d'une personne UNIQUE dans la BDD
     * @param address : address d'une personne
     * @param email_address : email d'une personne UNIQUE dans la BDD
     * @param birth_date : Date de naissance d'une personne
     * @param friend_list : Liste d'amis d'une personne
     */
    public Person(Integer idperson, String lastname, String firstname, String nickname, String phone_number, String address,
                  String email_address, LocalDate birth_date, String[] friend_list) {
        this.idperson = idperson;
        this.lastname = lastname;
        this.firstname = firstname;
        this.nickname = nickname;
        this.phone_number = phone_number;
        this.address = address;
        this.email_address = email_address;
        this.birth_date = birth_date;
        this.friend_list = new ArrayList<>(Arrays.asList(friend_list));
    }

    /**
     * getter
     * @return : idperson
     */
    public Integer getIdperson() {
        return idperson;
    }

    /**
     * setter
     * @param id : idperson
     */
    public void setId(Integer id) {
        this.idperson = id;
    }

    /**
     * getter
     * @return : lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * setter
     * @param lastname : lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * getter
     * @return firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * setter
     * @param firstname : firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * getter
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * setter
     * @param nickname : nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * getter
     * @return phone_number
     */
    public String getPhone_number() {
        return phone_number;
    }

    /**
     * setter
     * @param phone_number : phone_number
     */
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    /**
     * getter
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * setter
     * @param address : address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * getter
     * @return email_address
     */
    public String getEmail_address() {
        return email_address;
    }

    /**
     * setter
     * @param email_address : email_address
     */
    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    /**
     * getter
     * @return birth_date
     */
    public LocalDate getBirth_date() {
        return birth_date;
    }

    /**
     * setter
     * @param birth_date : birth_date
     */
    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    /**
     * setter
     * @param listString : listString
     */
    public void setFriend_list(String[] listString) {
        this.friend_list = new ArrayList<>(Arrays.asList(listString));
    }

    /**
     * adder 
     * @param friend : person
     */
    public void addFriend(Person friend) {
        friend_list.add(friend.getEmail_address());
    }

    /**
     * getter
     * @return friend_list to array
     */
    public String[] getFriend_list(){
        return friend_list.toArray(new String[0]);
    }
    
    @Override
    public String toString() {
        String string = super.toString();
        string += " [id : " + idperson;
        string += "| lastname : " + lastname;
        string += "| firstname : " + firstname;
        string += "| nickname : " + nickname;
        string += "\nphone_number : " + phone_number;
        string += "| address : " + address;
        string += "| email_address : " + email_address;
        string += "| birth_date : " + birth_date;
        string += "\nfriend_list : " + Arrays.toString(getFriend_list())  + "]";
        return string;
    }

    public String toString(String separator) {
        String formatedString = String.join(separator,
                idperson.toString(),
                lastname,
                firstname,
                nickname,
                phone_number,
                address,
                email_address,
                birth_date.toString(),
                Arrays.toString(getFriend_list()));
        return formatedString;
    }
} 