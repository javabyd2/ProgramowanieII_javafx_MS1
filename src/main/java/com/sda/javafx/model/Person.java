package com.sda.javafx.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Random;

public class Person {
    private StringProperty firstname;
    private StringProperty lastname;
    private StringProperty street;
    private StringProperty city;
    private StringProperty postalcode;
    private StringProperty birthday;

    static String[] countriesArray = {"Polska", "Niemcy", "Czechy", "Słowacja", "Austria", "Szwajcaria", "Szwecja",
            "Norwegia", "Słowenia", "Chorwacja"};
    static String[] citiesArray = {"Bydgoszcz", "Kraków", "Gerusalem", "Roma", "Siena", "Manoppello", "Vilnius", "Praha",
            "Wien", "Genève"};
    static String[] streets = {"ul. 11 Dywizjonu Artylerii Konnej", "ul. 11 Listopada", "ul. 15 Dywizji Piechoty Wielkopolskiej",
            "ul. 16 Pułku Ułanów Wielkopolskich", "ul. 19 Marca 1981 Roku", "ul. 20 Stycznia 1920 Roku",
            "ul. 2 Października", "ul. 3 Maja", "ul. 62 Pułku Piechoty Wielkopolskiej", "ul. Adama Asnyka",
            "ul. Adama Czartoryskiego", "ul. Adama Grzymały Siedleckiego", "ul. Adama Naruszewicza", "ul. Agatowa",
            "ul. Agrestowa", "ul. Akacjowa", "ul. Akademicka", "ul. Albatrosowa"};
    static String[] mNamesArray = {"Jan", "Andrzej", "Piotr", "Krzysztof", "Stanisław", "Tomasz", "Paweł", "Józef",
            "Marcin", "Marek"};
    static String[] fNamesArray = {"Anna", "Maria", "Katarzyna", "Małgorzata", "Agnieszka", "Krystyna", "Barbara",
            "Ewa", "Elżbieta", "Zofia"};
    static String[] mLastNamesArray = {"Nowak", "Kowalski", "Wiśniewski", "Dąbrowski", "Lewandowski", "Wójcik", "Kamiński",
            "Kowalczyk", "Zieliński", "Szymański", "Woźniak", "Kozłowski", "Jankowski", "Wojciechowski",
            "Kwiatkowski", "Kaczmarek", "Mazur", "Krawczyk", "Piotrowski", "Grabowski"};
    static String[] fLastNamesArray = {"Nowakowska", "Pawłowska", "Michalska", "Nowicka", "Adamczyk", "Dudek", "Zając",
            "Wieczorek", "Jabłońska", "Król", "Majewska", "Olszewska", "Jaworska", "Wróbel", "Malinowska", "Pawlak",
            "Witkowska", "Walczak", "Stępień", "Górska"};

    public Person() {
    }

    public Person(String firstname, String lastname/*, String street, String city, String postalcode, String birthday*/) {
        Person person = generatePersonData();
        if (firstname.equals("")) {
            this.firstname = new SimpleStringProperty(person.getFirstname());
        } else {
            this.firstname = new SimpleStringProperty(firstname);
        }
        if (lastname.equals("")) {
            this.lastname = new SimpleStringProperty(person.getLastname());
        } else {
            this.lastname = new SimpleStringProperty(lastname);
        }
        this.street = new SimpleStringProperty(person.getStreet());
        this.city = new SimpleStringProperty(person.getCity());
        this.postalcode = new SimpleStringProperty(person.getPostalcode());
        this.birthday = new SimpleStringProperty(person.getBirthday());
    }

    public String getFirstname() {
        return firstname.get();
    }

    public StringProperty firstnameProperty() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = new SimpleStringProperty(firstname);
    }

    public String getLastname() {
        return lastname.get();
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = new SimpleStringProperty(lastname);
    }

    public String getStreet() {
        return street.get();
    }

    public StringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street = new SimpleStringProperty(street);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city = new SimpleStringProperty(city);
    }

    public String getPostalcode() {
        return postalcode.get();
    }

    public StringProperty postalcodeProperty() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = new SimpleStringProperty(postalcode);
    }

    public String getBirthday() {
        return birthday.get();
    }

    public StringProperty birthdayProperty() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = new SimpleStringProperty(birthday);
    }

    public static Person generatePersonData(){
        Person person = new Person();
        Random generator = new Random();
        if (generator.nextBoolean()) {
            person.firstname = new SimpleStringProperty(mNamesArray[generator.nextInt(mNamesArray.length)]);
            person.lastname = new SimpleStringProperty(mLastNamesArray[generator.nextInt(mLastNamesArray.length)]);
        } else {
            person.firstname = new SimpleStringProperty(fNamesArray[generator.nextInt(fNamesArray.length)]);
            person.lastname = new SimpleStringProperty(fLastNamesArray[generator.nextInt(fLastNamesArray.length)]);
        }
        person.street = new SimpleStringProperty(streets[generator.nextInt(streets.length)]+" "+generator.nextInt(100));
        person.city = new SimpleStringProperty(citiesArray[generator.nextInt(citiesArray.length)]);
        person.postalcode = new SimpleStringProperty(String.format("%02d-%03d", generator.nextInt(99)+1, generator.nextInt(999)+1));
        person.birthday = new SimpleStringProperty(String.format("%02d-%02d-%04d",
                generator.nextInt(28)+1,
                generator.nextInt(12)+1,
                generator.nextInt(50)+1950));
        return person;
    }

    @Override
    public String toString() {
        return firstname.getValue() +" "+ lastname.getValue();
    }
}
