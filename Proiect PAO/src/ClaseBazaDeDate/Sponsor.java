package ClaseBazaDeDate;

public class Sponsor {
    /*
    Datele membre
     */
    private String numeSponsor;

    /*
    Get-eri
     */
    public String getNumeSponsor(){
        return this.numeSponsor;
    }

    /*
    Set-eri
     */
    public void setNumeSponsor(String numeSponsor){
        this.numeSponsor = numeSponsor;
    }

    /*
    Metode specifice clasei
     */
    public String toString(){
        return this.getNumeSponsor();
    }
    public Sponsor(String numeSponsor){
        this.numeSponsor = numeSponsor;
    }
}
