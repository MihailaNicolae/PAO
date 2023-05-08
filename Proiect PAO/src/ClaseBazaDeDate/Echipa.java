package ClaseBazaDeDate;

import ClaseBazaDeDate.Antrenor;
import ClaseBazaDeDate.Club;

public class Echipa {
    /*
    Datele membre
     */
    private String numeEchipa;

    private int nrMembri;

    private Club club;

    private Antrenor antrenor;
    /*
    Get-eri
     */
    public String getNumeEchipa(){
        return this.numeEchipa;
    }

    public int getNrMembri(){
        return this.nrMembri;
    }

    public Club getClub(){
        return this.club;
    }

    public Antrenor getAntrenor(){
        return this.antrenor;
    }
    /*
    Set-eri
     */
    public void setNumeEchipa(String numeEchipa){
        this.numeEchipa = numeEchipa;
    }

    public void setNrMembri(int nrMembri){
        this.nrMembri = nrMembri;
    }

    public void setClub(Club club){
        this.club = club;
    }

    public void setAntrenor(Antrenor antrenor){
        this.antrenor = antrenor;
    }
    /*
    Metode specifice clasei
     */
    public String toString(){
        return this.getNumeEchipa();
    }
    public Echipa(String numeEchipa, Club club, Antrenor antrenor){
        this.numeEchipa = numeEchipa;
        this.nrMembri = 0;
        this.club = club;
        this.antrenor = antrenor;
    }
}
