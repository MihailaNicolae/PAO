package ClaseBazaDeDate;

import ClaseBazaDeDate.Angajat;

import java.time.LocalDate;

public class Jucator extends Angajat {
    /*
    Datele membre
     */
    private String rol;
    private Echipa echipa;


    /*
    Get-eri
     */
    public String getRol(){
        return this.rol;
    }

    public Echipa getEchipa(){
        return this.echipa;
    }
    /*
    Set-eri
     */
    public void setRol(String rol){
        this.rol = rol;
    }

    public void setEchipa(Echipa echipa){
        this.echipa = echipa;
    }

    /*
    Metode specifice clasei
     */
    public String toString(){
        return this.getNume() + " " + this.getPrenume();
    }

    public Jucator(String nume, String prenume, double salariu, LocalDate dataNasterii, LocalDate dataAngajarii, String rol, Echipa echipa){
        super(nume,prenume,salariu,dataNasterii,dataAngajarii);
        this.rol = rol;
        this.echipa = echipa;
        this.echipa.setNrMembri(this.echipa.getNrMembri()+1);
    }
}
