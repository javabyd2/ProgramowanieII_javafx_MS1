package com.sda.javafx.controller;

import com.sda.javafx.Main;
import com.sda.javafx.model.ActionState;
import com.sda.javafx.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonController {

    //referencja do klasy main
    private Main main;
    private Person person = new Person();
    private ActionState state = ActionState.NONE;
    private Integer personId = -1;

    public void setMain(Main main){
        this.main = main;
    }

    public void setState(ActionState state) {
        this.state = state;
        if (state.equals(ActionState.ADD)) {
            titleLabel.setText("DODAWANIE NOWEJ OSOBY");
        }
        if (state.equals(ActionState.MODIFY)) {
            titleLabel.setText("MODYFIKOWANIE DANYCH OSOBY");
        }
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    @FXML
    private Label titleLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField streetTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField postalTextField;
    @FXML
    private TextField birthdayTextField;
    @FXML
    private Button applyButton;
    @FXML
    private Button cancelButton;

    @FXML
    private void initialize() {
    }

    @FXML
    public void generateData(){
        Person person = new Person("", "");
        nameTextField.setText(person.getFirstname());
        lastnameTextField.setText(person.getLastname());
        streetTextField.setText(person.getStreet());
        cityTextField.setText(person.getCity());
        postalTextField.setText(person.getPostalcode());
        birthdayTextField.setText(person.getBirthday());
    }

    @FXML
    public void applyButtonAction(){
        Person person = new Person();
        person.setFirstname(nameTextField.getText());
        person.setLastname(lastnameTextField.getText());
        person.setStreet(streetTextField.getText());
        person.setCity(cityTextField.getText());
        person.setPostalcode(postalTextField.getText());
        person.setBirthday(birthdayTextField.getText());
        switch (state) {
            case ADD:
                main.addPersonToList(person);
                break;
            case MODIFY:
                main.modifyPersonToList(person, personId);
                break;
        }
        Stage stage = (Stage) applyButton.getScene().getWindow();
        stage.close();
    }

    public void showPerson() {
        nameTextField.setText(person.getFirstname());
        lastnameTextField.setText(person.getLastname());
        cityTextField.setText(person.getCity());
        postalTextField.setText(person.getPostalcode());
        streetTextField.setText(person.getStreet());
        birthdayTextField.setText(person.getBirthday());
    }

    @FXML
    public void cancelButtonAction(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
