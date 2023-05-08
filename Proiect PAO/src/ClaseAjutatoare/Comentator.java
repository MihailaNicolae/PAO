package ClaseAjutatoare;

import ClaseBazaDeDate.Competitie;
import ClaseBazaDeDate.Echipa;
import ClaseBazaDeDate.Jucator;

public interface Comentator {
    public void inceputMeci(Competitie c, Echipa e1, Echipa e2, String nume, String prenume);
    public void sfarsitMeci(Competitie competitie, Echipa echipa1, Echipa echipa2, String nume, String prenume);
    public void reactieFault(Jucator jucator1, Jucator jucator2);
    public void reactieGol(Jucator jucator);
    public void interactiuneColeg(String nume, String prenume);
    public void comentariuJucator(Jucator jucator);
}
