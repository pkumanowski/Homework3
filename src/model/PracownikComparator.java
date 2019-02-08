package model;

import java.util.Comparator;

public class PracownikComparator implements Comparator<Pracownik> {

    @Override
    public int compare(Pracownik o1, Pracownik o2) {
        return o1.getCzasPracy() - o2.getCzasPracy();
    }
}
