package controller;


import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Pracownik;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
    private TableColumn<Pracownik, String> rozPracyCol;
    @FXML
    private TableColumn<Pracownik, String> zakPracyCol;

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

    private Collection<Pracownik> list = Files.readAllLines(new File("C:/javaFX/Homework3/src/input.txt").toPath())
            .stream()
            .map(line -> {
                String[] details = line.split(" ");
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
        tableView.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                onEdit();
            }
        });
    }

    public void onEdit() {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            Pracownik wybranyTextField = tableView.getSelectionModel().getSelectedItem();
            imieTField.setText(wybranyTextField.getImie());
            nazwiskoTField.setText(wybranyTextField.getNazwisko());
            nrPokojuTField.setText(wybranyTextField.getPokoj());
            rozpoczeciePracyTfield.setText(wybranyTextField.getGodzRozPracy());
            zakonczeniePracyTField.setText(wybranyTextField.getGodzZakPracy());
        }
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
            alan.setGodzRozPracy(rozpoczeciePracyTfield.getText());
            alan.setGodzZakPracy(zakonczeniePracyTField.getText());
            imieCol.setCellValueFactory(new PropertyValueFactory<>("imie"));
            nazwiskoCol.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            pokojCol.setCellValueFactory(new PropertyValueFactory<>("pokoj"));
            rozPracyCol.setCellValueFactory(new PropertyValueFactory<>("godzRozPracy"));
            zakPracyCol.setCellValueFactory(new PropertyValueFactory<>("godzZakPracy"));
            tableView.getItems().add(alan);
        });

        zapiszBtn.setOnAction(event -> {
            List <List<String>> arrList = new ArrayList<>();
            Pracownik prac = new Pracownik();
            for (int i = 0; i < tableView.getItems().size(); i++){
                prac = tableView.getItems().get(i);
                arrList.add(new ArrayList<>());
                arrList.get(i).add(prac.getImie());
                arrList.get(i).add(prac.getNazwisko());
                arrList.get(i).add(prac.getPokoj());
                arrList.get(i).add(prac.getGodzRozPracy());
                arrList.get(i).add(prac.getGodzZakPracy());
            }
            for (int i = 0; i < arrList.size(); i++){
                for (int j = 0; j < arrList.get(i).size(); j++){
                    //System.out.println(arrList.get(i).get(j));
                    try {
                        String tmp = arrList.toString();
                        PrintWriter pw = new PrintWriter(new FileOutputStream("C:/javaFX/Homework3/src/input2.txt"));
                        pw.write(tmp);
                        pw.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
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
