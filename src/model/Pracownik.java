package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pracownik {

    StringProperty imie = new SimpleStringProperty();
    StringProperty nazwisko = new SimpleStringProperty();
    StringProperty pokoj = new SimpleStringProperty();
    StringProperty godzRozPracy = new SimpleStringProperty();
    StringProperty godzZakPracy = new SimpleStringProperty();
    private int czasPracy;

    public int getCzasPracy() {
        return czasPracy;
    }

    public void setCzasPracy(int czasPracy) {
        this.czasPracy = czasPracy;
    }

    public String getImie() {
        return imie.get();
    }

    public StringProperty imieProperty() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie.set(imie);
    }

    public String getNazwisko() {
        return nazwisko.get();
    }

    public StringProperty nazwiskoProperty() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko.set(nazwisko);
    }

    public String getPokoj() {
        return pokoj.get();
    }

    public StringProperty pokojProperty() {
        return pokoj;
    }

    public void setPokoj(String pokoj) {
        this.pokoj.set(pokoj);
    }

    public String getGodzRozPracy() {
        return godzRozPracy.get();
    }

    public StringProperty godzRozPracyProperty() {
        return godzRozPracy;
    }

    public void setGodzRozPracy(String godzRozPracy) {
        this.godzRozPracy.set(godzRozPracy);
    }

    public String getGodzZakPracy() {
        return godzZakPracy.get();
    }

    public StringProperty godzZakPracyProperty() {
        return godzZakPracy;
    }

    public void setGodzZakPracy(String godzZakPracy) {
        this.godzZakPracy.set(godzZakPracy);
    }

    public void obliczCzasPracy (){

    }
}