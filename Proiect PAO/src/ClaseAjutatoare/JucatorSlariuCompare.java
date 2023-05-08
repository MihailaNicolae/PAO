package ClaseAjutatoare;

import ClaseBazaDeDate.Jucator;

import java.util.Comparator;

public class JucatorSlariuCompare implements Comparator<Jucator> {
    public int compare(Jucator j1, Jucator j2){
        if(j1.getSalariu() > j2.getSalariu())
            return 1;
        else if(j1.getSalariu() < j2.getSalariu())
            return -1;
        else
            return 0;
    }
}
