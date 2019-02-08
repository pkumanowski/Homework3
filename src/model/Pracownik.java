package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pracownik {

    StringProperty imie = new SimpleStringProperty();
    StringProperty nazwisko = new SimpleStringProperty();
    StringProperty pokoj = new SimpleStringProperty();
    IntegerProperty godzRozPracy = new SimpleIntegerProperty();
    IntegerProperty godzZakPracy = new SimpleIntegerProperty();
    StringProperty czasPracy = new SimpleStringProperty();

    public void setCzasPracy(StringProperty czasPracy) {
        this.czasPracy = czasPracy;
    }

    public int getCzasPracy() {
        return getGodzZakPracy() - getGodzRozPracy();
    }

    public int getGodzRozPracy() {
        return godzRozPracy.get();
    }

    public IntegerProperty godzRozPracyProperty() {
        return godzRozPracy;
    }

    public void setGodzRozPracy(int godzRozPracy) {
        this.godzRozPracy.set(godzRozPracy);
    }

    public int getGodzZakPracy() {
        return godzZakPracy.get();
    }

    public IntegerProperty godzZakPracyProperty() {
        return godzZakPracy;
    }

    public void setGodzZakPracy(int godzZakPracy) {
        this.godzZakPracy.set(godzZakPracy);
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

}