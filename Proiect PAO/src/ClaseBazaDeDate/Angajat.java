package ClaseBazaDeDate;

import java.time.LocalDate;
import java.util.Date;

public abstract class Angajat {
    /*
    Datele membre
     */
    private String nume;
    private String prenume;
    private double salariu;
    private LocalDate dataNasterii;
    private LocalDate dataAngajarii;

    /*
    Get-eri
     */
    public String getNume(){
        return this.nume;
    }

    public String getPrenume(){
        return this.prenume;
    }

    public double getSalariu(){
        return this.salariu;
    }

    public LocalDate getDataNasterii(){
        return this.dataNasterii;
    }

    public LocalDate getDataAngajarii(){
        return this.dataAngajarii;
    }
    /*
    Set-eri
     */
    public void setNume(String nume){
        this.nume = nume;
    }

    public void setPrenume(String prenume){
        this.prenume = prenume;
    }

    public void setSalariu(double salariu){
        this.salariu = salariu;
    }

    public void setDataNasterii(LocalDate dataNasterii){
        this.dataNasterii = dataNasterii;
    }

    public void setDataAngajarii(LocalDate dataAngajarii){
        this.dataAngajarii = dataAngajarii;
    }
    /*
    Metode specifice clasei
     */
    public Angajat(String nume, String prenume, double salariu, LocalDate dataNasterii, LocalDate dataAngajarii){
        this.nume = nume;
        this.prenume = prenume;
        this.salariu = salariu;
        this.dataNasterii = dataNasterii;
        this.dataAngajarii = dataAngajarii;
    }
}
