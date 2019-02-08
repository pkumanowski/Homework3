package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Pracownik;
import model.PracownikComparator;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;
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
    private TableColumn<Pracownik, Integer> rozPracyCol;
    @FXML
    private TableColumn<Pracownik, Integer> zakPracyCol;

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
                prac.setGodzRozPracy(Integer.parseInt(details[3]));
                prac.setGodzZakPracy(Integer.parseInt(details[4]));
                prac.getCzasPracy();
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
            rozpoczeciePracyTfield.setText(String.valueOf(wybranyTextField.getGodzRozPracy()));
            zakonczeniePracyTField.setText(String.valueOf(wybranyTextField.getGodzZakPracy()));
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
            alan.setGodzRozPracy(Integer.parseInt(rozpoczeciePracyTfield.getText()));
            alan.setGodzZakPracy(Integer.parseInt(zakonczeniePracyTField.getText()));
            imieCol.setCellValueFactory(new PropertyValueFactory<>("imie"));
            nazwiskoCol.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            pokojCol.setCellValueFactory(new PropertyValueFactory<>("pokoj"));
            rozPracyCol.setCellValueFactory(new PropertyValueFactory<>("godzRozPracy"));
            zakPracyCol.setCellValueFactory(new PropertyValueFactory<>("godzZakPracy"));
            tableView.getItems().add(alan);
        });

        zapiszBtn.setOnAction(event->{
            Writer writer=null;

            try{
                File file=new File("C:/javaFX/Homework3/src/input.txt");
                writer=new BufferedWriter(new FileWriter(file));
                for(Pracownik prac:listaPracownikow)
                {
                    String text = prac.getImie()+ " " + prac.getNazwisko()+" " + prac.getPokoj()+ " " + prac.getGodzRozPracy()+ " " + prac.getGodzZakPracy() + "\n";

                    writer.write(text);
                }
            }catch(IOException e){
                e.printStackTrace();
            }
            finally{
                {
                    try{
                        writer.flush();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    try{
                        writer.close();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }
        });
      raportBtn.setOnAction(event -> {
          Writer writer=null;
          List<Pracownik> temp = listaPracownikow;
          try {
              File file = new File("C:/javaFX/Homework3/src/raport.txt");
              writer = new BufferedWriter(new FileWriter(file));
              for(Pracownik prac:temp){
                  String text = prac.getImie() + " " + prac.getNazwisko()+ " " + prac.getPokoj() + " " + prac.getGodzRozPracy() + " " + prac.getGodzZakPracy() + " " + prac.getCzasPracy() + "\n";
                  Collections.sort(temp, new PracownikComparator());
                  writer.write(text);
                  
              }
          } catch (IOException e) {
              e.printStackTrace();
          }            finally{
              {
                  try{
                      writer.flush();
                  }catch(IOException e){
                      e.printStackTrace();
                  }
                  try{
                      writer.close();
                  }catch(IOException e){
                      e.printStackTrace();
                  }
              }
          }
          });
    }
    private void setTable() {
    }

    @FXML
    public void closeStage() {
        primaryStage.close();
    }
}

