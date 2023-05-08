package ClaseAjutatoare;

import ClaseAjutatoare.Comentator;
import ClaseBazaDeDate.Competitie;
import ClaseBazaDeDate.Echipa;
import ClaseBazaDeDate.Jucator;

public class ComentatorBun implements Comentator {
    /*
    Metodele interfetei
     */
    public void inceputMeci(Competitie competitie, Echipa echipa1, Echipa echipa2, String nume, String prenume){
        System.out.println(this.numeComentator + " " + this.prenumeComentator +": " + "Bine v-am gasit la " + competitie +". Numele meu este " + this.numeComentator + " " + this.prenumeComentator + ", iar alaturi de mine este " + nume + " "+ prenume);
        System.out.println(this.numeComentator + " " + this.prenumeComentator +": " + "In aceasta seara " + echipa1.getNumeEchipa() + " joaca impotriva " + echipa2.getNumeEchipa() + ". Miza e mare, iar spiritele sunt incinse.");
        System.out.println(this.numeComentator + " " + this.prenumeComentator +": " + "Pentru " + echipa2.getNumeEchipa() + " este o foarte buna ocazia de a se afirma in lumea fotbalului. Daca reuseste sa invinga " + echipa1.getNumeEchipa() + " atunci cu siguranta isi va pune numele pe harta marilor echipe. Insa, " + echipa1.getNumeEchipa() + " are o reputatie foarte buna si ne-a aratat meciuri frumoase pana acum. Ea lupta pentru a ramane in top. Cu siguranta va fi un meci interesant.");
    }
    public void sfarsitMeci(Competitie competitie, Echipa echipa1, Echipa echipa2, String nume, String prenume){
        System.out.println(this.numeComentator + " " + this.prenumeComentator +": " + "Doamnelor si domnilor, astfel se incheie " + competitie + ". Am vazut un meci superb intre " + echipa1.getNumeEchipa() + " si " + echipa2.getNumeEchipa() + ". Eu si colegul meu " + nume + " " + prenume + " speram ca v-a placut si va uram o seara buna!");
    }
    public void reactieFault(Jucator jucator1, Jucator jucator2){
        System.out.println(this.numeComentator + " " + this.prenumeComentator +": " + "Situatia devine incurcata intre " + jucator1.getNume() + " si " + jucator2.getNume() + ". Ce se intampla... INCREDIBIL! Fault din partea lui " + jucator1.getNume() + ". Arbitrul cu siguranta nu va trece asta cu vederea. " + jucator2.getNume() + " este inca la pamant, dar nu pare sa se fi ranit grav.");
    }
    public void reactieGol(Jucator jucator){
        System.out.println(this.numeComentator + " " + this.prenumeComentator +": " + jucator.getNume() + " se aproprie de poarta. Isi face loc printre adversari. Se aproprie... se aproprie... si... GOOOOOOOL! Un gol foarte frumos din partea lui " + jucator.getNume());
    }

    public void comentariuJucator(Jucator jucator){
        System.out.println(this.numeComentator + " " + this.prenumeComentator +": " + jucator.getNume() + " joaca foarte bine in aceasta seara. A avut o serie de meciuri bune si daca il castiga si pe asta, cu siguranta va primi multe oferte din partea celorlalte echipe. Cine nu l-ar vrea pe " + jucator.getNume() + "?");
    }
    public void interactiuneColeg(String nume, String prenume){
        System.out.println(this.numeComentator + " " + this.prenumeComentator +": " + "Nu pari a fi cel mai mare fan al echipelor din seara asta, " + prenume + ". Totusi, nu poti nega ca e un meci frumos.");
    }
    /*
    Date membre
     */
    private String numeComentator;
    private String prenumeComentator;
    /*
    Get-eri
     */
    public String getNumeComentator(){
        return this.numeComentator;
    }

    public String getPrenumeComentator(){
        return this.prenumeComentator;
    }
    /*
    Set-eri
     */
    public void setNumeComentator(String numeComentator){
        this.numeComentator = numeComentator;
    }

    public void setPrenumeComentator(String prenumeComentator){
        this.prenumeComentator = prenumeComentator;
    }

    public ComentatorBun(String numeComentator, String prenumeComentator){
        this.numeComentator = numeComentator;
        this.prenumeComentator = prenumeComentator;
    }
}
