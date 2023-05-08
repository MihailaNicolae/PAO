package ClaseBazaDeDate;/////
// FOLOSESC ACEST LOC PENTRU A MENTIONA UNELE BUG-URI SAU LUCRURI NEDORITE CARE TREBUIE RECTIFICATE
/*
    Eu folosesc get-eri pe string. Asta nu cumva ne ofera referinta? Poate trebuie sa fac return new String(this.nume);
 */
////

public class Club {
    /*
    Datele membre
     */
    private String numeClub;
    private String taraClub;

    /*
    Get-eri pentru date
     */
    public String getNumeClub() {
        return this.numeClub;
    }

    public String getTaraClub(){
        return this.taraClub;
    }
    /*
    Set-eri pentru date
     */
    public void setNumeClub(String numeClub){
        this.numeClub = numeClub;
    }

    public void setTaraClub(String taraClub){
        this.taraClub = taraClub;
    }
    /*
    Metode specifice clasei
     */
    public String toString(){
        return this.getNumeClub();
    }
    public Club(String numeClub, String taraClub){
        this.numeClub = numeClub;
        this.taraClub = taraClub;
    }
}
