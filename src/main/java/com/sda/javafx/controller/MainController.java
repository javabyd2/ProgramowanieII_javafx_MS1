package com.sda.javafx.controller;

import com.sda.javafx.Main;
import com.sda.javafx.model.ActionState;
import com.sda.javafx.model.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MainController {

    @FXML
    private TableView<Person> personTableView;
    @FXML
    private TableColumn<Person, String> firstnameColumn;
    @FXML
    private TableColumn<Person, String> lastColumn;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastnameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label postalcodeLabel;
    @FXML
    private Label birthdayLabel;

    @FXML
    private Button addPersonButton;
    @FXML
    private Button modifyPersonButton;
    @FXML
    private Button deletePersonButton;

    public MainController() {
    }

    @FXML
    private void initialize() {
        firstnameColumn.setCellValueFactory(
            data -> data.getValue().firstnameProperty()
        );
        lastColumn.setCellValueFactory(
            data -> data.getValue().lastnameProperty()
        );

        personTableView.getSelectionModel()
            .selectedItemProperty()
            .addListener((observable, oldSelectedPerson, newSelectedPerson) -> {
                modifyPersonButton.setDisable(newSelectedPerson == null);
                deletePersonButton.setDisable(newSelectedPerson == null);
                if (newSelectedPerson != null)
                    showPerson(newSelectedPerson);
            });
// inna metoda:
//            .addListener((obs, oldSelection, newSelection) -> {
//                if (newSelection != null) {
//                    firstNameLabel.setText(newSelection.getFirstname());
//                    lastnameLabel.setText(newSelection.getLastname());
//                }
//            });
    }

    private void showPerson(Person person) {
        if (!person.equals(null)) {
            firstNameLabel.setText(person.getFirstname());
            lastnameLabel.setText(person.getLastname());
            cityLabel.setText(person.getCity());
            postalcodeLabel.setText(person.getPostalcode());
            streetLabel.setText(person.getStreet());
            birthdayLabel.setText(person.getBirthday());
        }
    }

    //referencja do klasy main
    private Main main;

    public void setMain(Main main){
        this.main = main;
        personTableView.setItems(main.getPerson());
    }

    @FXML
    private void addPerson() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("PersonData.fxml"));
        AnchorPane addPersonLayout = loader.load();

        PersonController personController = loader.getController();
        personController.setState(ActionState.ADD);
        personController.setMain(main);

        Stage stage = new Stage();
        Scene scene = new Scene(addPersonLayout);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void modifyPerson() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("PersonData.fxml"));
        AnchorPane addPersonLayout = loader.load();

        PersonController personController = loader.getController();
        personController.setState(ActionState.MODIFY);
        personController.setMain(main);
        personController.setPerson(personTableView.getSelectionModel().getSelectedItem());
        personController.setPersonId(personTableView.getSelectionModel().getFocusedIndex());
        personController.showPerson();

        Stage stage = new Stage();
        Scene scene = new Scene(addPersonLayout);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void deletePerson(){
        int index = personTableView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Usunięcie osoby z listy");
            alert.setHeaderText("Usuwanie osoby o indeksie: "+index);
            alert.setContentText("Czy na pewno chcesz usunąć osobę: "+main.getPerson().get(index).toString()+"?");
            Optional<ButtonType> buttons = alert.showAndWait();

            if (buttons.get() == ButtonType.OK) {
                personTableView.getItems().remove(index);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Usunięcie osoby z listy");
                alert.setHeaderText("Usuwanie osoby o indeksie: "+index);
                alert.setContentText("Usunąłeś osobę: "+main.getPerson().get(index).toString());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Błąd");
            alert.setHeaderText("To jest error");
            alert.setContentText("Nie można usunąć");
            alert.showAndWait();
        }
    }
}
