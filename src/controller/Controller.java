package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Pracownik;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class Controller implements Initializable {

    @FXML
    private TableView<Pracownik> tableView;
    @FXML
    private TableColumn<Pracownik, String> imieCol;
    @FXML
    private TableColumn<Pracownik, String> nazwiskoCol;
    @FXML
    private TableColumn<Pracownik, String> pokojCol;

    @FXML
    private TextField imieTField;
    @FXML
    private TextField nazwiskoTField;
    @FXML
    private TextField nrPokojuTField;
    @FXML
    private TextField rozpoczeciePracyTfield;
    @FXML
    private TextField zakonczeniePracyTField;

    @FXML
    private Button wczytajBtn;
    @FXML
    private Button zapiszBtn;
    @FXML
    private Button dodajBtn;
    @FXML
    private Button raportBtn;

    private Main main;
    private Stage primaryStage;

    private  Collection<Pracownik> list = Files.readAllLines(new File("C:/javaFX/Homework3/src/input.txt").toPath())
            .stream()
            .map(line -> {
                String[] details = line.split(",");
                Pracownik prac = new Pracownik();
                prac.setImie(details[0]);
                prac.setNazwisko(details[1]);
                prac.setPokoj(details[2]);
                prac.setGodzZakPracy(details[3]);
                prac.setGodzRozPracy(details[4]);
                return prac;
            })
            .collect(Collectors.toList());

    private ObservableList<Pracownik> listaPracownikow = FXCollections.observableArrayList(list);

    public Controller() throws IOException {
    }

    public void setMain(Main main, Stage primaryStage) {
        this.main = main;
        this.primaryStage = primaryStage;
        setTable();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        wczytajBtn.setOnAction(event -> {
            imieCol.setCellValueFactory(data -> data.getValue().imieProperty());
            nazwiskoCol.setCellValueFactory(data -> data.getValue().nazwiskoProperty());
            pokojCol.setCellValueFactory(data -> data.getValue().pokojProperty());
            tableView.setItems(listaPracownikow);
        });

        dodajBtn.setOnAction(event -> {
            Pracownik alan = new Pracownik();
            alan.setImie(imieTField.getText());
            alan.setNazwisko(nazwiskoTField.getText());
            alan.setPokoj(nrPokojuTField.getText());
            imieCol.setCellValueFactory(new PropertyValueFactory<>("imie"));
            nazwiskoCol.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            pokojCol.setCellValueFactory(new PropertyValueFactory<>("pokoj"));
            tableView.getItems().add(alan);
        });
//
//        dodajBtn.setOnAction(event -> );
//
//        raportBtn.setOnAction(event -> );

    }

    private void setTable() {
   }

    @FXML
    public void closeStage() {
        primaryStage.close();
    }

}
