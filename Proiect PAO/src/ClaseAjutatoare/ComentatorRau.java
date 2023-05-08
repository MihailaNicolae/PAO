package ClaseAjutatoare;

import ClaseAjutatoare.Comentator;
import ClaseBazaDeDate.Competitie;
import ClaseBazaDeDate.Echipa;
import ClaseBazaDeDate.Jucator;

public class ComentatorRau implements Comentator {
    /*
    Metodele interfetei
     */
    public void inceputMeci(Competitie competitie, Echipa echipa1, Echipa echipa2, String nume, String prenume){
        System.out.println(this.numeComentator + " " + this.prenumeComentator +": " + "Bine v-am gasit la " + competitie +". Numele meu este " + this.numeComentator + " " + this.prenumeComentator + ", iar alaturi de mine este" + nume + " "+ prenume);
        System.out.println(this.numeComentator + " " + this.prenumeComentator +": " + "In aceasta seara " + echipa1.getNumeEchipa() + " joaca impotriva " + echipa2.getNumeEchipa() +".");
        System.out.println(this.numeComentator + " " + this.prenumeComentator +": " + echipa2.getNumeEchipa() + " cu siguranta se gandeste la victorie, dar oare pot ei sa bata " + echipa1.getNumeEchipa() + "? Pana acum nu ne-au aratat niste meciuri impresionante, iar " + echipa1.getNumeEchipa() + " a infrant echipe cu renume mai bun. Daca " + echipa2.getNumeEchipa() + " castiga, cu siguranta ne vor da pe spate.");

    }
    public void sfarsitMeci(Competitie competitie, Echipa echipa1, Echipa echipa2, String nume, String prenume){
        System.out.println(this.numeComentator + " " + this.prenumeComentator +": " + "Doamnelor si domnilor, astfel se incheie " + competitie + ". Am vazut un meci intre " + echipa1.getNumeEchipa() + " si " + echipa2.getNumeEchipa() + ". Eu si colegul meu " + nume + " " + prenume + " speram ca v-a placut si va uram o seara buna!");
    }
    public void reactieFault(Jucator jucator1, Jucator jucator2){
        System.out.println(this.numeComentator + " " + this.prenumeComentator +": " + jucator1.getNume() + " si " + jucator2.getNume() + " par a avea niste neintelegeri. Absolut neasteptat! Fault din partea lui " + jucator1.getNume() + ". Arbitrul ar face bine sa-i dea cartonasul rosu. " + jucator2.getNume() + " este inca la pamant. Avand in vedere fault-ul superficial suferit, pare ca profita de aceasta ocazie ca sa isi incerce norocul in teatru.");

    }
    public void reactieGol(Jucator jucator){
        System.out.println(this.numeComentator + " " + this.prenumeComentator +": " + jucator.getNume() + " se aproprie de poarta, suteaza si da gol. O aparare foarte slaba din partea echipei adverse");

    }
    public void interactiuneColeg(String nume, String prenume){
        System.out.println(this.numeComentator + " " + this.prenumeComentator +": " + prenume + ", iti admir entuziasmul. Pari mai implicat in meci decat insisi jucatorii. Pana acum nu au oferit cea mai buna prestatie. Sa speram ca meciul va deveni mai interesant de acum incolo");
    }
    public void comentariuJucator(Jucator jucator){
        System.out.println(this.numeComentator + " " + this.prenumeComentator +": " + jucator.getNume() + " nu pare a se putea concentra. A avut multe rateuri meciul asta si chiar si in meciurile anterioare. Va trebui sa se adune sau risca sa stea pe banca.");
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

    public ComentatorRau(String numeComentator, String prenumeComentator){
        this.numeComentator = numeComentator;
        this.prenumeComentator = prenumeComentator;
    }
}
