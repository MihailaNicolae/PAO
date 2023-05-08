package ClaseBazaDeDate;

import ClaseBazaDeDate.Angajat;

import java.time.LocalDate;

public class Antrenor extends Angajat {
    /*
    Datele membre
     */
    private Club club;

    /*
    Get-eri
     */
    public Club getClub(){
        return this.club;
    }

    /*
    Set-eri
     */
    public void setClub(Club club){
        this.club = club;
    }

    /*
    Metode specifice clasei
     */
    public String toString(){
        return this.getNume() + " " + this.getPrenume();
    }
    public Antrenor(String nume, String prenume, double salariu, LocalDate dataNasterii, LocalDate dataAngajarii, Club club){
        super(nume,prenume,salariu,dataNasterii,dataAngajarii);
        this.club = club;
    }
}
