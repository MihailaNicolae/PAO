package ClaseBazaDeDate;

import ClaseBazaDeDate.Sponsor;

import java.time.LocalDate;

public class Contract {
    /*
    Date membre
     */
    private Sponsor sponsor;
    private Echipa echipa;
    private double suma;
    private LocalDate dataInceput;
    private LocalDate dataFinal;
    /*
    Get-eri
     */
    public Sponsor getSponsor(){
        return this.sponsor;
    }
    public Echipa getEchipa(){
        return this.echipa;
    }
    public double getSuma(){
        return this.suma;
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
    public void setSponsor(Sponsor sponsor){
        this.sponsor = sponsor;
    }

    public void setEchipa(Echipa echipa){
        this.echipa = echipa;
    }
    public void setSuma(double suma){
        this.suma = suma;
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
        return this.getSponsor().getNumeSponsor() + " - " + this.getEchipa().getNumeEchipa();
    }
    public Contract(Sponsor sponsor, Echipa echipa, double suma, LocalDate dataInceput, LocalDate dataFinal){
        this.sponsor = sponsor;
        this.echipa = echipa;
        this.suma = suma;
        this.dataInceput = dataInceput;
        this.dataFinal = dataFinal;
    }
}