package ClaseBazaDeDate;

import java.time.LocalDate;
import java.util.Date;

public class Competitie {
    /*
    Datele membre
     */
    private String numeCompetitie;
    private String locatie;
    private LocalDate dataInceput;
    private LocalDate dataFinal;

    /*
    Get-eri
     */
    public String getNumeCompetitie(){
        return this.numeCompetitie;
    }
    public String getLocatie(){
        return this.locatie;
    }
    public LocalDate getDataInceput(){
        return this.dataInceput;
    }
    public LocalDate getDataFinal(){
        return this.dataFinal;
    }
    /*
    Set-eri
     */
    public void setNumeCompetitie(String numeCompetitie){
        this.numeCompetitie = numeCompetitie;
    }

    public void setLocatie(String locatie){
        this.locatie = locatie;
    }

    public void setDataInceput(LocalDate dataInceput){
        this.dataInceput = dataInceput;
    }

    public void setDataFinal(LocalDate dataFinal){
        this.dataFinal = dataFinal;
    }

    /*
    Metode specifice clasei
     */
    public String toString(){
        return this.getNumeCompetitie();
    }
    public Competitie(String numeCompetitie, String locatie, LocalDate dataInceput, LocalDate dataFinal){
        this.numeCompetitie = numeCompetitie;
        this.locatie = locatie;
        this.dataInceput = dataInceput;
        this.dataFinal = dataFinal;
    }
}