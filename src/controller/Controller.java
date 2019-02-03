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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
    PrintWriter out = null;

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

        zapiszBtn.setOnAction(event->{
            Writer writer=null;

            try{
                File file=new File("C:/javaFX/Homework3/src/input.txt");
                writer=new BufferedWriter(new FileWriter(file));
                for(Pracownik prac:listaPracownikow)
                {
                    String text=prac.getImie()+ " " + prac.getNazwisko()+" " + prac.getPokoj()+ " " + prac.getGodzRozPracy()+ " "+ prac.getGodzZakPracy() + "\n";

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
              Path initialFile = Paths.get("C:/javaFX/Homework3/src/input.txt");
              Path sortedFile = Paths.get("C:/javaFX/Homework3/src/raport.txt");

              try {
                  Stream<CharSequence> sortedLines = Files.lines(initialFile).sorted().map(Function.identity());
                  Files.write(sortedFile, sortedLines::iterator, StandardOpenOption.CREATE);
              } catch (IOException e) {
                  e.printStackTrace();
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



//List<List<String>>arrList=newArrayList<>();
//Pracownikprac=newPracownik();
//for(inti=0;i<tableView.getItems().size();i++){
//prac=tableView.getItems().get(i);
//arrList.add(newArrayList<>());
//arrList.get(i).add(prac.getImie());
//arrList.get(i).add(prac.getNazwisko());
//arrList.get(i).add(prac.getPokoj());
//arrList.get(i).add(prac.getGodzRozPracy());
//arrList.get(i).add(prac.getGodzZakPracy());
//}
//for(inti=0;i<arrList.size();i++){
//for(intj=0;j<arrList.get(i).size();j++){
////System.out.println(arrList.get(i).get(j));
//try{
//Stringtmp=arrList.toString();
//PrintWriterpw=newPrintWriter(newFileOutputStream("C:/javaFX/Homework3/src/input2.txt"));
//pw.write(tmp);
//pw.close();
//}catch(FileNotFoundExceptione){
//e.printStackTrace();
//}
//}
//}
