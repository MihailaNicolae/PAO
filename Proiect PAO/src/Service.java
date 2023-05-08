import ClaseAjutatoare.ComentatorBun;
import ClaseAjutatoare.ComentatorRau;
import ClaseAjutatoare.JucatorSlariuCompare;
import ClaseBazaDeDate.*;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/*
    Aceasta este clasa Service care este folosita pentru a expune operatiile sistemului
 */
public class Service {
    /*
    Formarea clasei Singleton
     */
    private static Service instance = null;
    public static Service getInstance(){
        if(instance == null)
            instance = new Service();
        return instance;
    }
    /*
    Date membre folosite pentru gestiune
     */
    private ArrayList<Antrenor> antrenori = new ArrayList<Antrenor>();
    private ArrayList<Club> cluburi = new ArrayList<Club>();
    private ArrayList<Competitie> competitii = new ArrayList<Competitie>();
    private ArrayList<Contract> contracte = new ArrayList<Contract>();
    private ArrayList<Echipa> echipe = new ArrayList<Echipa>();
    private ArrayList<Jucator> jucatori = new ArrayList<Jucator>();
    private ArrayList<Sponsor> sponsori = new ArrayList<Sponsor>();
    private Map<Club, ArrayList<Angajat>> clubAngajatMap = new HashMap<>();

    /*
    Get-eri
     */
    public ArrayList<Antrenor> getAntrenori(){
        return this.antrenori;
    }

    public ArrayList<Club> getCluburi(){
        return this.cluburi;
    }

    public ArrayList<Competitie> getCompetitii(){
        return this.competitii;
    }

    public ArrayList<Contract> getContracte(){
        return this.contracte;
    }

    public ArrayList<Echipa> getEchipe(){
        return this.echipe;
    }

    public ArrayList<Jucator> getJucatori(){
        return this.jucatori;
    }

    public ArrayList<Sponsor> getSponsori(){
        return this.sponsori;
    }

    /*
    Metode folosite pentru actiuni/interogari
     */
    public void comanda1(){
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        int optiune = -1;
        System.out.println("Introduceti numarul corespunzator intrarii pe care doriti sa o adaugati sau 0 pentru a anula comanda");
        System.out.println("1. Antrenor");
        System.out.println("2. Club");
        System.out.println("3. Competitie");
        System.out.println("4. Contract");
        System.out.println("5. Echipa");
        System.out.println("6. Jucator");
        System.out.println("7. Sponsor");
        System.out.print("Optiunea dumneavoastra este = ");
        optiune = scanner.nextInt();
        scanner.nextLine();
        while(optiune <=0 || optiune >=8){
            System.out.println("!!!Introduceti o comanda valida!!!");
            System.out.print("Optiunea dumneavoastra este = ");
            optiune = scanner.nextInt();
            scanner.nextLine();
        }
        if(optiune == 1){
            if(this.cluburi.size() == 0){
                System.out.println("Nu se poate adauga un antrenor, fiindca nu exista niciun club");
                return;
            }
            System.out.println("Introduceti datele intrarii pe care doriti sa o adaugati");
            System.out.print("Nume = ");
            String nume = scanner.nextLine();
            System.out.print("Prenume = ");
            String prenume = scanner.nextLine();
            System.out.print("Salariu = ");
            double salariu = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Data nasterii = ");
            LocalDate dataNasterii = LocalDate.parse(scanner.nextLine(), formatter); ///Trebuie prelucrata data cumva
            System.out.print("Data angajarii = ");
            LocalDate dataAngajarii = LocalDate.parse(scanner.nextLine(), formatter); ///La fel ca mai sus
            System.out.println("Alegeti din lista clubul la care este angajat antrenorul");
            this.showClub();
            System.out.print("Clubul pe care il doriti este = ");
            int index_club = scanner.nextInt();
            scanner.nextLine();
            while(index_club < 0 || index_club > this.cluburi.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Clubul pe care il doriti este = ");
                index_club = scanner.nextInt();
                scanner.nextLine();
            }
            Antrenor aux = new Antrenor(nume, prenume, salariu, dataNasterii, dataAngajarii, this.cluburi.get(index_club));///service.getCluburi().get(index_club));
            this.addAntrenor(aux);
        }
        if(optiune == 2){
            System.out.println("Introduceti datele intrarii pe care doriti sa o adaugati");
            System.out.print("Nume club = ");
            String numeClub = scanner.nextLine();
            System.out.print("Tara club = ");
            String taraClub = scanner.nextLine();
            Club club = new Club(numeClub, taraClub);
            this.addClub(club);
        }
        if(optiune == 3){
            System.out.println("Introduceti datele intrarii pe care doriti sa o adaugati");
            System.out.print("Nume competitie = ");
            String numeCompetitie = scanner.nextLine();
            System.out.print("Locatie = ");
            String locatie = scanner.nextLine();
            System.out.print("Data inceperii competitiei = ");
            LocalDate dataInceput = LocalDate.parse(scanner.nextLine(), formatter); ///Trebuie prelucrata data cumva
            System.out.print("Data terminarii competitiei = ");
            LocalDate dataFinal = LocalDate.parse(scanner.nextLine(), formatter); ///La fel ca mai sus
            Competitie competitie = new Competitie(numeCompetitie, locatie, dataInceput, dataFinal);
            this.addCompetitie(competitie);
        }
        if(optiune == 4){
            if(this.sponsori.size() == 0){
                System.out.println("Nu se poate adauga un contract, fiindca nu exista niciun sponsor");
                return;
            }
            if(this.echipe.size() == 0){
                System.out.println("Nu se poate adauga un contract, fiindca nu exista nicio echipa");
                return;
            }
            System.out.println("Introduceti datele intrarii pe care doriti sa o adaugati");
            System.out.println("Alegeti din lista echipa beneficiara a sponsorizarii");
            this.showEchipa();
            System.out.print("Echipa pe care o doriti este = ");
            int index_echipa = scanner.nextInt();
            scanner.nextLine();
            while(index_echipa < 0 || index_echipa > this.echipe.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Echipa pe care o doriti este = ");
                index_echipa = scanner.nextInt();
                scanner.nextLine();
            }
            System.out.println("Alegeti din lista sponsorul echipei");
            this.showSponsor();
            System.out.print("Sponsorul pe care il doriti este = ");
            int index_sponsor = scanner.nextInt();
            scanner.nextLine();
            while(index_sponsor < 0 || index_sponsor > this.sponsori.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Sponsorul pe care il doriti este = ");
                index_sponsor = scanner.nextInt();
                scanner.nextLine();
            }
            System.out.print("Suma sponsorizarii este = ");
            double suma = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Data inceperii sponsorizarii = ");
            LocalDate dataInceput = LocalDate.parse(scanner.nextLine(), formatter);
            System.out.print("Data terminarii sponsorizarii = ");
            LocalDate dataFinal = LocalDate.parse(scanner.nextLine(), formatter);
            Contract contract = new Contract(this.sponsori.get(index_sponsor), this.echipe.get(index_echipa), suma, dataInceput, dataFinal);
            this.contracte.add(contract);
        }
        if(optiune == 5){
            if(this.antrenori.size() == 0){
                System.out.println("Nu se poate adauga o echipa, fiindca nu exista niciun antrenor");
                return;
            }
            if(this.cluburi.size() == 0){
                System.out.println("Nu se poate adauga o echipa, fiindca nu exista niciun club");
                return;
            }
            System.out.println("Introduceti datele intrarii pe care doriti sa o adaugati");
            System.out.print("Numele echipei = ");
            String numeEchipa = scanner.nextLine();
            System.out.println();
            System.out.println("Alegeti din lista clubul caruia echipa ii apartine");
            this.showClub();
            System.out.print("Clubul pe care il doriti este = ");
            int index_club = scanner.nextInt();
            scanner.nextLine();
            while(index_club < 0 || index_club > this.cluburi.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Clubul pe care il doriti este = ");
                index_club = scanner.nextInt();
                scanner.nextLine();
            }
            System.out.println("Alegeti din lista antrenorul echipei");
            this.showAntrenor();
            System.out.print("Antrenorul pe care il doriti este = ");
            int index_antrenor = scanner.nextInt();
            scanner.nextLine();
            while(index_antrenor < 0 || index_antrenor > this.antrenori.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Antrenorul pe care il doriti este = ");
                index_antrenor = scanner.nextInt();
                scanner.nextLine();
            }
            Echipa echipa = new Echipa(numeEchipa, this.cluburi.get(index_club), this.antrenori.get(index_antrenor));
            this.echipe.add(echipa);

        }
        if(optiune == 6){
            if(this.echipe.size() == 0){
                System.out.println("Nu se poate adauga un jucator, fiindca nu exista nicio echipa");
                return;
            }
            System.out.println("Introduceti datele intrarii pe care doriti sa o adaugati");
            System.out.print("Nume = ");
            String nume = scanner.nextLine();
            System.out.print("Prenume = ");
            String prenume = scanner.nextLine();
            System.out.print("Salariu = ");
            double salariu = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Data nasterii = ");
            LocalDate dataNasterii = LocalDate.parse(scanner.nextLine(), formatter); ///Trebuie prelucrata data cumva
            System.out.print("Data angajarii = ");
            LocalDate dataAngajarii = LocalDate.parse(scanner.nextLine(), formatter); ///La fel ca mai sus
            System.out.println("Alegeti din lista rolul jucatorului");
            System.out.println("1. Atacant\n2. Mijlocas\n3. Fundas\n4. Portar\n5. Rezerva");
            int index_rol = scanner.nextInt();
            scanner.nextLine();
            String rol = "";
            if(index_rol == 1)
                rol = "Atacant";
            if(index_rol == 2)
                rol = "Mijlocas";
            if(index_rol == 3)
                rol = "Fundas";
            if(index_rol == 4)
                rol = "Portar";
            if(index_rol == 5)
                rol = "Rezerva";
            System.out.println("Alegeti din lista echipa in care joaca jucatorul");
            this.showEchipa();
            System.out.print("Echipa pe care o doriti este = ");
            int index_echipa = scanner.nextInt();
            scanner.nextLine();
            while(index_echipa < 0 || index_echipa > this.echipe.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Echipa pe care o doriti este = ");
                index_echipa = scanner.nextInt();
                scanner.nextLine();
            }
            Jucator jucator = new Jucator(nume, prenume, salariu, dataNasterii, dataAngajarii, rol, this.echipe.get(index_echipa));///service.getCluburi().get(index_club));
            this.addJucator(jucator);
        }
        if(optiune == 7){
            System.out.println("Introduceti datele intrarii pe care doriti sa o adaugati");
            System.out.print("Nume sponsor = ");
            String numeSponsor = scanner.nextLine();
            Sponsor sponsor = new Sponsor(numeSponsor);
            this.sponsori.add(sponsor);
        }
        System.out.println("Comanda a fost efectuata");
    }

    public void comanda2(){
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        int optiune = -1;
        System.out.println("Introduceti numarul corespunzator intrarii pe care doriti sa o modificati sau 0 pentru a anula comanda");
        System.out.println("1. Antrenor");
        System.out.println("2. Club");
        System.out.println("3. Competitie");
        System.out.println("4. Contract");
        System.out.println("5. Echipa");
        System.out.println("6. Jucator");
        System.out.println("7. Sponsor");
        System.out.print("Optiunea dumneavoastra este = ");
        optiune = scanner.nextInt();
        scanner.nextLine();
        while(optiune <=0 || optiune >=8){
            System.out.println("!!!Introduceti o comanda valida!!!");
            System.out.print("Optiunea dumneavoastra este = ");
            optiune = scanner.nextInt();
            scanner.nextLine();
        }
        if(optiune == 1){
            if(this.antrenori.size() == 0){
                System.out.println("Nu exista intrari de acest tip in baza de date. Nu se pot efectua modificari");
                return;
            }
            System.out.println("Alegeti antrenorul ale carui date doriti sa le modificati");
            this.showAntrenor();
            int index = scanner.nextInt();
            scanner.nextLine();
            while(index < 0 || index >= this.antrenori.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Optiunea dumneavoastra este = ");
                index = scanner.nextInt();
                scanner.nextLine();
            }
            System.out.println("Daca doriti sa nu modificati un anumit camp, introduceti -1 in campul respectiv");
            System.out.print("Nume = ");
            String nume = scanner.nextLine();
            System.out.print("Prenume = ");
            String prenume = scanner.nextLine();
            System.out.print("Salariu = ");
            double salariu = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Data nasterii = ");
            String dn = scanner.nextLine();
            LocalDate dataNasterii = LocalDate.parse("11-06-2002",formatter);
            if(!dn.equals("-1"))
                dataNasterii = LocalDate.parse(dn, formatter); ///La fel ca mai sus
            System.out.print("Data angajarii = ");
            String da = scanner.nextLine();
            LocalDate dataAngajarii = LocalDate.parse("11-06-2002",formatter);
            if(!da.equals("-1"))
                dataAngajarii = LocalDate.parse(da, formatter); ///La fel ca mai sus
            System.out.println("Alegeti din lista clubul la care este angajat antrenorul");
            this.showClub();
            System.out.print("Clubul pe care il doriti este = ");
            int index_club = scanner.nextInt();
            scanner.nextLine();
            while(index_club < -1 || index_club >= this.cluburi.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Clubul pe care il doriti este = ");
                index_club = scanner.nextInt();
                scanner.nextLine();
            }
            if(!nume.equals("-1")){
                this.antrenori.get(index).setNume(nume);
            }
            if(!prenume.equals("-1")){
                this.antrenori.get(index).setPrenume(prenume);
            }
            if(salariu != -1){
                this.antrenori.get(index).setSalariu(salariu);
            }
            if(!dn.equals("-1")){
                this.antrenori.get(index).setDataNasterii(dataNasterii);
            }
            if(!da.equals("-1")){
                this.antrenori.get(index).setDataAngajarii(dataAngajarii);
            }
            if(index_club != -1){
                this.antrenori.get(index).setClub(this.cluburi.get(index_club));
            }

        }
        if(optiune == 2){
            if(this.cluburi.size() == 0){
                System.out.println("Nu exista intrari de acest tip in baza de date. Nu se pot efectua modificari");
                return;
            }
            System.out.println("Alegeti clubul ale carui date doriti sa le modificati");
            this.showClub();
            int index = scanner.nextInt();
            scanner.nextLine();
            while(index < 0 || index >= this.cluburi.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Optiunea dumneavoastra este = ");
                index = scanner.nextInt();
                scanner.nextLine();
            }
            System.out.println("Daca doriti sa nu modificati un anumit camp, introduceti -1 in campul respectiv");
            System.out.print("Nume club = ");
            String numeClub = scanner.nextLine();
            System.out.print("Tara club = ");
            String taraClub = scanner.nextLine();
            if(!numeClub.equals("-1")){
                this.cluburi.get(index).setNumeClub(numeClub);
            }
            if(!taraClub.equals("-1")){
                this.cluburi.get(index).setTaraClub(taraClub);
            }
        }
        if(optiune == 3){
            if(this.competitii.size() == 0){
                System.out.println("Nu exista intrari de acest tip in baza de date. Nu se pot efectua modificari");
                return;
            }
            System.out.println("Alegeti competitia ale carei date doriti sa le modificati");
            this.showCompetitie();
            int index = scanner.nextInt();
            scanner.nextLine();
            while(index < 0 || index >= this.competitii.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Optiunea dumneavoastra este = ");
                index = scanner.nextInt();
                scanner.nextLine();
            }
            System.out.println("Daca doriti sa nu modificati un anumit camp, introduceti -1 in campul respectiv");
            System.out.print("Nume competitie = ");
            String numeCompetitie = scanner.nextLine();
            System.out.print("Locatie = ");
            String locatie = scanner.nextLine();
            System.out.print("Data inceperii competitiei = ");
            String di = scanner.nextLine();
            LocalDate dataInceput = LocalDate.parse("11-06-2002",formatter);
            if(!di.equals("-1"))
                dataInceput = LocalDate.parse(di, formatter); ///La fel ca mai sus
            System.out.print("Data terminarii competitiei = ");
            String df = scanner.nextLine();
            LocalDate dataFinal = LocalDate.parse("11-06-2002",formatter);
            if(!df.equals("-1"))
                dataFinal = LocalDate.parse(df, formatter); ///La fel ca mai sus
            if(!numeCompetitie.equals("-1")){
                this.competitii.get(index).setNumeCompetitie(numeCompetitie);
            }
            if(!locatie.equals("-1")){
                this.competitii.get(index).setLocatie(locatie);
            }
            if(!di.equals("-1")){
                this.competitii.get(index).setDataInceput(dataInceput);
            }
            if(!df.equals("-1")){
                this.competitii.get(index).setDataFinal(dataFinal);
            }
        }
        if(optiune == 4){
            if(this.contracte.size() == 0){
                System.out.println("Nu exista intrari de acest tip in baza de date. Nu se pot efectua modificari");
                return;
            }
            System.out.println("Alegeti contractul ale carui date doriti sa le modificati");
            this.showContract();
            int index = scanner.nextInt();
            scanner.nextLine();
            while(index < 0 || index >= this.contracte.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Optiunea dumneavoastra este = ");
                index = scanner.nextInt();
                scanner.nextLine();
            }
            System.out.println("Daca doriti sa nu modificati un anumit camp, introduceti -1 in campul respectiv");
            System.out.println("Alegeti din lista sponsorul din contract");
            this.showSponsor();
            System.out.print("Sponsorul pe care il doriti este = ");
            int index_sponsor = scanner.nextInt();
            scanner.nextLine();
            while(index_sponsor < -1 || index_sponsor >= this.sponsori.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Sponsorul pe care il doriti este = ");
                index_sponsor = scanner.nextInt();
                scanner.nextLine();
            }
            System.out.println("Alegeti din lista echipa beneficiara a contractului");
            this.showEchipa();
            System.out.print("Echipa pe care o doriti este = ");
            int index_echipa = scanner.nextInt();
            scanner.nextLine();
            while(index_echipa < -1 || index_echipa >= this.echipe.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Echipa pe care o doriti este = ");
                index_echipa = scanner.nextInt();
                scanner.nextLine();
            }
            System.out.print("Suma = ");
            double suma = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Data inceperii contractului = ");
            String di = scanner.nextLine();
            LocalDate dataInceput = LocalDate.parse("11-06-2002",formatter);
            if(!di.equals("-1"))
                dataInceput = LocalDate.parse(di, formatter); ///La fel ca mai sus
            System.out.print("Data terminarii contractului = ");
            String df = scanner.nextLine();
            LocalDate dataFinal = LocalDate.parse("11-06-2002",formatter);
            if(!df.equals("-1"))
                dataFinal = LocalDate.parse(df, formatter); ///La fel ca mai sus
            if(index_sponsor == -1){
                this.contracte.get(index).setSponsor(this.sponsori.get(index_sponsor));
            }
            if(index_echipa != -1){
                this.contracte.get(index).setEchipa(this.echipe.get(index_echipa));
            }
            if(suma != -1){
                this.contracte.get(index).setSuma(suma);
            }
            if(!di.equals("-1")){
                this.contracte.get(index).setDataInceput(dataInceput);
            }
            if(!df.equals("-1")){
                this.contracte.get(index).setDataFinal(dataFinal);
            }

        }
        if(optiune == 5){
            if(this.echipe.size() == 0){
                System.out.println("Nu exista intrari de acest tip in baza de date. Nu se pot efectua modificari");
                return;
            }
            System.out.println("Alegeti echipa ale carei date doriti sa le modificati");
            this.showEchipa();
            int index = scanner.nextInt();
            scanner.nextLine();
            while(index < 0 || index >= this.echipe.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Optiunea dumneavoastra este = ");
                index = scanner.nextInt();
                scanner.nextLine();
            }
            System.out.println("Daca doriti sa nu modificati un anumit camp, introduceti -1 in campul respectiv");
            System.out.print("Nume echipa = ");
            String numeEchipa = scanner.nextLine();
            System.out.print("Numar membri = ");
            int nrMembri = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Alegeti din lista clubul caruia ii apartine echipa");
            this.showClub();
            System.out.print("Clubul pe care il doriti este = ");
            int index_club = scanner.nextInt();
            scanner.nextLine();
            while(index_club < -1 || index_club >= this.cluburi.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Clubul pe care il doriti este = ");
                index_club = scanner.nextInt();
                scanner.nextLine();
            }
            System.out.println("Alegeti din lista antrenorul acestei echipe");
            this.showAntrenor();
            System.out.print("Antrenorul pe care il doriti este = ");
            int index_antrenor = scanner.nextInt();
            scanner.nextLine();
            while(index_antrenor < -1 || index_antrenor >= this.antrenori.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Antrenorul pe care il doriti este = ");
                index_antrenor = scanner.nextInt();
                scanner.nextLine();
            }
            if(!numeEchipa.equals("-1")){
                this.echipe.get(index).setNumeEchipa(numeEchipa);
            }
            if(nrMembri != -1){
                this.echipe.get(index).setNrMembri(nrMembri);
            }
            if(index_club != -1){
                this.echipe.get(index).setClub(this.cluburi.get(index_club));
            }
            if(index_antrenor != -1){
                this.echipe.get(index).setAntrenor(this.antrenori.get(index_antrenor));
            }
        }
        if(optiune == 6){
            if(this.jucatori.size() == 0){
                System.out.println("Nu exista intrari de acest tip in baza de date. Nu se pot efectua modificari");
                return;
            }
            System.out.println("Alegeti jucatorul ale carui date doriti sa le modificati");
            this.showJucator();
            int index = scanner.nextInt();
            scanner.nextLine();
            while(index < 0 || index >= this.jucatori.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Optiunea dumneavoastra este = ");
                index = scanner.nextInt();
                scanner.nextLine();
            }
            System.out.println("Daca doriti sa nu modificati un anumit camp, introduceti -1 in campul respectiv");
            System.out.print("Nume = ");
            String nume = scanner.nextLine();
            System.out.print("Prenume = ");
            String prenume = scanner.nextLine();
            System.out.print("Salariu = ");
            double salariu = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Data nasterii = ");
            String dn = scanner.nextLine();
            LocalDate dataNasterii = LocalDate.parse("11-06-2002",formatter);
            if(!dn.equals("-1"))
                dataNasterii = LocalDate.parse(dn, formatter); ///La fel ca mai sus
            System.out.print("Data angajarii = ");
            String da = scanner.nextLine();
            LocalDate dataAngajarii = LocalDate.parse("11-06-2002",formatter);
            if(!da.equals("-1"))
                dataAngajarii = LocalDate.parse(da, formatter); ///La fel ca mai sus
            System.out.println("Alegeti din lista echipa in care joaca jucatorul");
            this.showEchipa();
            System.out.print("Echipa pe care o doriti este = ");
            int index_echipa = scanner.nextInt();
            scanner.nextLine();
            while(index_echipa < -1 || index_echipa >= this.echipe.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Echipa pe care o doriti este = ");
                index_echipa = scanner.nextInt();
                scanner.nextLine();
            }
            if(!nume.equals("-1")){
                this.jucatori.get(index).setNume(nume);
            }
            if(!prenume.equals("-1")){
                this.jucatori.get(index).setPrenume(prenume);
            }
            if(salariu != -1){
                this.jucatori.get(index).setSalariu(salariu);
            }
            if(!dn.equals("-1")){
                this.jucatori.get(index).setDataNasterii(dataNasterii);
            }
            if(!da.equals("-1")){
                this.jucatori.get(index).setDataAngajarii(dataAngajarii);
            }
            if(index_echipa != -1){
                this.jucatori.get(index).setEchipa(this.echipe.get(index_echipa));
            }

        }
        if(optiune == 7){
            if(this.sponsori.size() == 0){
                System.out.println("Nu exista intrari de acest tip in baza de date. Nu se pot efectua modificari");
                return;
            }
            System.out.println("Alegeti sponsorul ale carui date doriti sa le modificati");
            this.showSponsor();
            int index = scanner.nextInt();
            scanner.nextLine();
            while(index < 0 || index >= this.sponsori.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Optiunea dumneavoastra este = ");
                index = scanner.nextInt();
                scanner.nextLine();
            }
            System.out.println("Daca doriti sa nu modificati un anumit camp, introduceti -1 in campul respectiv");
            System.out.print("Nume sponsor = ");
            String numeSponsor = scanner.nextLine();
            if(!numeSponsor.equals("-1"))
                this.sponsori.get(index).setNumeSponsor(numeSponsor);
        }
        System.out.println("Comanda a fost efectuata");
    }

    public void comanda3(){
        Scanner scanner = new Scanner(System.in);
        int optiune = -1;
        System.out.println("Introduceti numarul corespunzator intrarii pe care doriti sa o stergeti sau 0 pentru a anula comanda");
        System.out.println("1. Antrenor");
        System.out.println("2. Club");
        System.out.println("3. Competitie");
        System.out.println("4. Contract");
        System.out.println("5. Echipa");
        System.out.println("6. Jucator");
        System.out.println("7. Sponsor");
        System.out.print("Optiunea dumneavoastra este = ");
        optiune = scanner.nextInt();
        scanner.nextLine();
        while(optiune <=0 || optiune >=8){
            System.out.println("!!!Introduceti o comanda valida!!!");
            System.out.print("Optiunea dumneavoastra este = ");
            optiune = scanner.nextInt();
            scanner.nextLine();
        }
        if(optiune == 1){
            if(this.antrenori.size() == 0){
                System.out.println("Nu exista intrari de acest tip in baza de date. Nu se pot efectua stergeri");
                return;
            }
            System.out.println("Alegeti din lista antrenorul pe care doriti sa-l stergeti");
            this.showAntrenor();
            System.out.print("Optiunea dumneavoastra este = ");
            int index = scanner.nextInt();
            scanner.nextLine();
            while(index < 0 || index >= this.antrenori.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Optiunea dumneavoastra este = ");
                index = scanner.nextInt();
                scanner.nextLine();
            }
            this.antrenori.remove(index);

        }
        if(optiune == 2){
            if(this.cluburi.size() == 0){
                System.out.println("Nu exista intrari de acest tip in baza de date. Nu se pot efectua stergeri");
                return;
            }
            System.out.println("Alegeti din lista clubul pe care doriti sa-l stergeti");
            this.showClub();
            System.out.print("Optiunea dumneavoastra este = ");
            int index = scanner.nextInt();
            scanner.nextLine();
            while(index < 0 || index >= this.cluburi.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Optiunea dumneavoastra este = ");
                index = scanner.nextInt();
                scanner.nextLine();
            }
            this.cluburi.remove(index);
        }
        if(optiune == 3){
            if(this.competitii.size() == 0){
                System.out.println("Nu exista intrari de acest tip in baza de date. Nu se pot efectua stergeri");
                return;
            }
            System.out.println("Alegeti din lista competitia pe care doriti sa o stergeti");
            this.showCompetitie();
            System.out.print("Optiunea dumneavoastra este = ");
            int index = scanner.nextInt();
            scanner.nextLine();
            while(index < 0 || index >= this.competitii.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Optiunea dumneavoastra este = ");
                index = scanner.nextInt();
                scanner.nextLine();
            }
            this.competitii.remove(index);
        }
        if(optiune == 4){
            if(this.contracte.size() == 0){
                System.out.println("Nu exista intrari de acest tip in baza de date. Nu se pot efectua stergeri");
                return;
            }
            System.out.println("Alegeti din lista contractul pe care doriti sa-l stergeti");
            this.showContract();
            System.out.print("Optiunea dumneavoastra este = ");
            int index = scanner.nextInt();
            scanner.nextLine();
            while(index < 0 || index >= this.contracte.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Optiunea dumneavoastra este = ");
                index = scanner.nextInt();
                scanner.nextLine();
            }
            this.contracte.remove(index);
        }
        if(optiune == 5){
            if(this.echipe.size() == 0){
                System.out.println("Nu exista intrari de acest tip in baza de date. Nu se pot efectua stergeri");
                return;
            }
            System.out.println("Alegeti din lista echipa pe care doriti sa o stergeti");
            this.showEchipa();
            System.out.print("Optiunea dumneavoastra este = ");
            int index = scanner.nextInt();
            scanner.nextLine();
            while(index < 0 || index >= this.echipe.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Optiunea dumneavoastra este = ");
                index = scanner.nextInt();
                scanner.nextLine();
            }
            this.echipe.remove(index);

        }
        if(optiune == 6){
            if(this.jucatori.size() == 0){
                System.out.println("Nu exista intrari de acest tip in baza de date. Nu se pot efectua stergeri");
                return;
            }
            System.out.println("Alegeti din lista jucatorul pe care doriti sa-l stergeti");
            this.showJucator();
            System.out.print("Optiunea dumneavoastra este = ");
            int index = scanner.nextInt();
            scanner.nextLine();
            while(index < 0 || index >= this.jucatori.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Optiunea dumneavoastra este = ");
                index = scanner.nextInt();
                scanner.nextLine();
            }
            this.jucatori.get(index).getEchipa().setNrMembri(this.jucatori.get(index).getEchipa().getNrMembri()-1);
            this.jucatori.remove(index);
        }
        if(optiune == 7){
            if(this.sponsori.size() == 0){
                System.out.println("Nu exista intrari de acest tip in baza de date. Nu se pot efectua stergeri");
                return;
            }
            System.out.println("Alegeti din lista sponsorul pe care doriti sa-l stergeti");
            this.showSponsor();
            System.out.print("Optiunea dumneavoastra este = ");
            int index = scanner.nextInt();
            scanner.nextLine();
            while(index < 0 || index >= this.antrenori.size()){
                System.out.println("!!!Introduceti o optiune valida!!!");
                System.out.print("Optiunea dumneavoastra este = ");
                index = scanner.nextInt();
                scanner.nextLine();
            }
            this.sponsori.remove(index);
        }
        System.out.println("Comanda a fost efectuata");
    }

    public void comanda4(){
        Scanner scanner = new Scanner(System.in);
        int optiune = -1;
        System.out.println("Introduceti numarul corespunzator intrarii pe care doriti sa o vizualizati sau 0 pentru a anula comanda");
        System.out.println("1. Antrenor");
        System.out.println("2. Club");
        System.out.println("3. Competitie");
        System.out.println("4. Contract");
        System.out.println("5. Echipa");
        System.out.println("6. Jucator");
        System.out.println("7. Sponsor");
        System.out.print("Optiunea dumneavoastra este = ");
        optiune = scanner.nextInt();
        scanner.nextLine();
        while(optiune <=0 || optiune >=8){
            System.out.println("!!!Introduceti o comanda valida!!!");
            System.out.print("Optiunea dumneavoastra este = ");
            optiune = scanner.nextInt();
            scanner.nextLine();
        }
        if(optiune == 1){
            if(this.antrenori.size() == 0){
                System.out.println("Nu exista intrari de acest tip in baza de date");
                return;
            }
            System.out.println("Alegeti din lista antrenorul pe care doriti sa-l vizualizati");
            this.showAntrenor();
            System.out.print("Optiunea dumneavoastra este = ");
            int index = scanner.nextInt();
            this.showAntrenor(index);

        }
        if(optiune == 2){
            if(this.cluburi.size() == 0){
                System.out.println("Nu exista intrari de acest tip in baza de date");
                return;
            }
            System.out.println("Alegeti din lista clubul pe care doriti sa-l vizualizati");
            this.showClub();
            System.out.print("Optiunea dumneavoastra este = ");
            int index = scanner.nextInt();
            this.showClub(index);
        }
        if(optiune == 3){
            if(this.competitii.size() == 0){
                System.out.println("Nu exista intrari de acest tip in baza de date");
                return;
            }
            System.out.println("Alegeti din lista competitia pe care doriti sa o vizualizati");
            this.showCompetitie();
            System.out.print("Optiunea dumneavoastra este = ");
            int index = scanner.nextInt();
            this.showCompetitie(index);
        }
        if(optiune == 4){
            if(this.contracte.size() == 0){
                System.out.println("Nu exista intrari de acest tip in baza de date");
                return;
            }
            System.out.println("Alegeti din lista contractul pe care doriti sa-l vizualizati");
            this.showContract();
            System.out.print("Optiunea dumneavoastra este = ");
            int index = scanner.nextInt();
            this.showContract(index);
        }
        if(optiune == 5){
            if(this.echipe.size() == 0){
                System.out.println("Nu exista intrari de acest tip in baza de date");
                return;
            }
            System.out.println("Alegeti din lista echipa pe care doriti sa o vizualizati");
            this.showEchipa();
            System.out.print("Optiunea dumneavoastra este = ");
            int index = scanner.nextInt();
            this.showEchipa(index);

        }
        if(optiune == 6){
            if(this.jucatori.size() == 0){
                System.out.println("Nu exista intrari de acest tip in baza de date");
                return;
            }
            System.out.println("Alegeti din lista jucatorul pe care doriti sa-l vizualizati");
            this.showJucator();
            System.out.print("Optiunea dumneavoastra este = ");
            int index = scanner.nextInt();
            this.showJucator(index);
        }
        if(optiune == 7){
            if(this.sponsori.size() == 0){
                System.out.println("Nu exista intrari de acest tip in baza de date");
                return;
            }
            System.out.println("Alegeti din lista sponsorul pe care doriti sa-l vizualizati");
            this.showSponsor();
            System.out.print("Optiunea dumneavoastra este = ");
            int index = scanner.nextInt();
            this.showSponsor(index);
        }

    }

    public void comanda5(){
        Scanner scanner = new Scanner(System.in);
        int optiune = -1;
        System.out.println("Introduceti numarul corespunzator clubului caruia doriti sa ii vizualizati angajatii");
        this.showClub();
        System.out.print("Optiunea dumneavoastra este = ");
        optiune = scanner.nextInt();
        scanner.nextLine();
        clubAngajatMap.clear();
        ///TREBUIE PUSE CU COMANDA MAP.PUT() CA SA EXISTE IN MAP. ALTFEL DA NULL POINTER EXCEPTION
        ///clubAngajatMap.put(clubAles, lista vida)
        for(int i = 0; i < this.cluburi.size(); i++)
            clubAngajatMap.put(this.cluburi.get(i),new ArrayList<Angajat>());
        for(int i = 0; i < this.cluburi.size(); i++)
            for(int j = 0; j < this.antrenori.size(); j++)
                if(this.cluburi.get(i).equals(this.antrenori.get(j).getClub()))
                    clubAngajatMap.get(this.cluburi.get(i)).add(this.antrenori.get(j));
        for(int i = 0; i < this.cluburi.size(); i++)
            for(int j = 0; j < this.jucatori.size(); j++)
                if(this.cluburi.get(i).equals(this.jucatori.get(j).getEchipa().getClub()))
                    clubAngajatMap.get(this.cluburi.get(i)).add(this.jucatori.get(j));
        System.out.println("La clubul ales lucreaza urmatorii angajati:");
        for(int i = 0; i < clubAngajatMap.get(this.cluburi.get(optiune)).size(); i++)
            System.out.println(clubAngajatMap.get(this.cluburi.get(optiune)).get(i));
    }

    public void comanda6(){
        int superOk=1;
        int ok = 1;
        for(int i = 0; i < this.antrenori.size(); i++){
            int nume = 0;
            int prenume = 0;
            int salariu = 0;
            int dataNasterii = 0;
            int dataAngajarii = 0;
            int club = 0;
            if(this.antrenori.get(i).getNume().equals(""))
                nume = 1;
            if(this.antrenori.get(i).getNume() == null)
                nume = 2;
            if(this.antrenori.get(i).getPrenume().equals(""))
                prenume = 1;
            if(this.antrenori.get(i).getPrenume() == null)
                prenume = 2;
            if(this.antrenori.get(i).getSalariu() < 0)
                salariu = 1;
            if(this.antrenori.get(i).getDataNasterii().isAfter(this.antrenori.get(i).getDataAngajarii()))
                dataNasterii = 1;
            if(this.antrenori.get(i).getDataAngajarii().isBefore(this.antrenori.get(i).getDataNasterii()))
                dataNasterii = 1;
            if(this.antrenori.get(i).getClub() == null)
                club = 1;
            if(club != 1){
                club = 2;
                for(int j = 0; j < this.cluburi.size(); j++)
                    if(this.antrenori.get(i).getClub().equals(this.cluburi.get(j)))
                        club = 0;
            }
            if(nume != 0 || prenume != 0 || salariu != 0 || dataNasterii != 0 || dataAngajarii != 0 || club != 0){
                if(ok == 1){
                    System.out.println("Au fost detectate date invalide la urmatorii Antrenori:");
                    ok = 0;
                    superOk = 0;
                }
                System.out.println("Antrenorul cu indicele " + i);
                if(nume == 1)
                    System.out.println("-Numele este vid");
                if(nume == 2)
                    System.out.println("-Numele este null");
                if(prenume == 1)
                    System.out.println("-Prenumele este vid");
                if(prenume == 2)
                    System.out.println("-Prenumele este null");
                if(salariu == 1)
                    System.out.println("-Salariul este negativ");
                if(dataNasterii == 1 || dataAngajarii == 1)
                    System.out.println("-Data angajarii este inainte de data nasterii");
                if(club == 1)
                    System.out.println("-Antrenorul nu apartine unui club (camp null)");
                if(club == 2)
                    System.out.println("-Antrenorul apartine unui club inexistent (poate clubul a fost sters)");
            }
        }
        if(ok == 0)
            System.out.println();
        ok = 1;
        for(int i = 0; i < this.cluburi.size(); i++){
            int numeClub = 0;
            int taraClub = 0;
            if(this.cluburi.get(i).getNumeClub().equals(""))
                numeClub = 1;
            if(this.cluburi.get(i).getNumeClub() == null)
                numeClub = 2;
            if(this.cluburi.get(i).getTaraClub().equals(""))
                taraClub = 1;
            if(this.cluburi.get(i).getTaraClub() == null)
                taraClub = 2;
            if(numeClub != 0 || taraClub != 0){
                if(ok == 1){
                    System.out.println("Au fost detectate date invalide la urmatoarele cluburi:");
                    ok = 0;
                    superOk = 0;
                }
                System.out.println("Clubul cu indicele " + i);
                if(numeClub == 1)
                    System.out.println("-Numele clubului este vid");
                if(numeClub == 2)
                    System.out.println("-Numele clubului este null");
                if(taraClub == 1)
                    System.out.println("-Numele tarii clubului este vid");
                if(taraClub == 2)
                    System.out.println("-Numele tarii clubului este null");
            }
        }
        if(ok == 0)
            System.out.println();
        ok = 1;
        for(int i = 0; i < this.competitii.size(); i++){
            int numeCompetitie = 0;
            int locatie = 0;
            int dataInceput = 0;
            int dataFinal = 0;
            if(this.competitii.get(i).getNumeCompetitie().equals(""))
                numeCompetitie = 1;
            if(this.competitii.get(i).getNumeCompetitie() == null)
                numeCompetitie = 2;
            if(this.competitii.get(i).getLocatie().equals(""))
                locatie = 1;
            if(this.competitii.get(i).getLocatie() == null)
                locatie = 2;
            if(this.competitii.get(i).getDataInceput().isAfter(this.competitii.get(i).getDataFinal()))
                dataInceput = 1;
            if(this.competitii.get(i).getDataFinal().isBefore(this.competitii.get(i).getDataInceput()))
                dataFinal = 1;
            if(numeCompetitie != 0 || locatie != 0 || dataInceput != 0 || dataFinal != 0){
                if(ok == 1){
                    System.out.println("Au fost detectate date invalide la urmatoarele competitii:");
                    ok = 0;
                    superOk = 0;
                }
                System.out.println("Competitia cu indicele " + i);
                if(numeCompetitie == 1)
                    System.out.println("-Numele competitiei este vid");
                if(numeCompetitie == 2)
                    System.out.println("-Numele competitiei este null");
                if(locatie == 1)
                    System.out.println("-Numele locatiei este vid");
                if(locatie == 2)
                    System.out.println("-numele locatiei este null");
                if(dataInceput == 1 || dataFinal == 1)
                    System.out.println("-Data sfarsitului competitiei este inainte de data inceperii ei");
            }
        }
        if(ok == 0)
            System.out.println();
        ok = 1;
        for(int i = 0; i < this.contracte.size(); i++){
            int sponsor = 0;
            int echipa = 0;
            int suma = 0;
            int dataInceput = 0;
            int dataFinal = 0;
            if(this.contracte.get(i).getSuma() < 0)
                suma = 1;
            if(this.contracte.get(i).getDataInceput().isAfter(this.contracte.get(i).getDataFinal()))
                dataInceput = 1;
            if(this.contracte.get(i).getDataFinal().isBefore(this.contracte.get(i).getDataInceput()))
                dataInceput = 1;
            if(this.contracte.get(i).getSponsor() == null)
                sponsor = 1;
            if(sponsor != 1){
                sponsor = 2;
                for(int j = 0; j < this.sponsori.size(); j++)
                    if(this.contracte.get(i).getSponsor().equals(this.sponsori.get(j)))
                        sponsor = 0;
            }
            if(this.contracte.get(i).getEchipa() == null)
                echipa = 1;
            if(echipa != 1){
                echipa = 2;
                for(int j = 0; j < this.echipe.size(); j++)
                    if(this.contracte.get(i).getEchipa().equals(this.echipe.get(j)))
                        echipa = 0;
            }
            if(sponsor != 0 || echipa != 0 || suma != 0 || dataInceput != 0 || dataFinal != 0){
                if(ok == 1){
                    System.out.println("Au fost detectate date invalide la urmatoarele contracte:");
                    ok = 0;
                    superOk = 0;
                }
                System.out.println("Contractul cu indicele " + i);
                if(sponsor == 1)
                    System.out.println("-Contractul nu are un sponsor (camp null)");
                if(sponsor == 2)
                    System.out.println("-Contractul este semnat cu un sponsor inexistent (poate sponsorul a fost sters)");
                if(echipa == 1)
                    System.out.println("-Contractul nu are o echipa (camp null)");
                if(echipa == 2)
                    System.out.println("-Contractul este semnat cu o echipa inexistenta (poate echipa a fost stearsa)");
                if(suma == 1)
                    System.out.println("-Suma sponsorizarii este negativa");
                if(dataInceput == 1 || dataFinal == 1)
                    System.out.println("-Data inceperii contractului de sponsorizare este inainte de data terminarii contractului");
            }
        }
        if(ok == 0)
            System.out.println();
        ok = 1;
        for(int i = 0; i < this.echipe.size(); i++){
            int numeEchipa = 0;
            int nrMembri = 0;
            int club = 0;
            int antrenor = 0;
            if(this.echipe.get(i).getNumeEchipa().equals(""))
                numeEchipa = 1;
            if(this.echipe.get(i).getNrMembri() < 0)
                nrMembri = 1;
            if(this.echipe.get(i).getClub() == null)
                club = 1;
            if(club != 1){
                club = 2;
                for(int j = 0; j < this.cluburi.size(); j++)
                    if(this.echipe.get(i).getClub().equals(this.cluburi.get(j)))
                        club = 0;
            }
            if(this.echipe.get(i).getAntrenor() == null)
                antrenor = 1;
            if(antrenor != 1){
                antrenor = 2;
                for(int j = 0; j < this.antrenori.size(); j++)
                    if(this.echipe.get(i).getAntrenor().equals(this.antrenori.get(j)))
                        antrenor = 0;
            }
            if(numeEchipa != 0 || nrMembri != 0 || club != 0 || antrenor != 0){
                if(ok == 1){
                    System.out.println("Au fost detectate date invalide la urmatoarele echipe:");
                    ok = 0;
                    superOk = 0;
                }
                System.out.println("Echipa cu indicele " + i);
                if(numeEchipa == 1)
                    System.out.println("-Numele echipei este vid");
                if(nrMembri == 1)
                    System.out.println("-Numarul de membri al echipei este negativ");
                if(club == 1)
                    System.out.println("-Echipa nu apartine unui club (camp null)");
                if(club == 2)
                    System.out.println("-Echipa este inscrisa la un club inexistent (poate clubul a fost sters)");
                if(antrenor == 1)
                    System.out.println("-Echipa nu are un antrenor (camp null)");
                if(antrenor == 2)
                    System.out.println("-Echipa este antrenata de un antrenor inexistent (poate antrenorul a fost sters)");

            }
        }
        if(ok == 0)
            System.out.println();
        ok = 1;
        for(int i = 0; i < this.jucatori.size(); i++){
            int nume = 0;
            int prenume = 0;
            int salariu = 0;
            int dataNasterii = 0;
            int dataAngajarii = 0;
            int rol = 0;
            int echipa = 0;
            if(this.jucatori.get(i).getNume().equals(""))
                nume = 1;
            if(this.jucatori.get(i).getNume() == null)
                nume = 2;
            if(this.jucatori.get(i).getPrenume().equals(""))
                prenume = 1;
            if(this.jucatori.get(i).getPrenume() == null)
                prenume = 2;
            if(this.jucatori.get(i).getSalariu() < 0)
                salariu = 1;
            if(this.jucatori.get(i).getDataNasterii().isAfter(this.jucatori.get(i).getDataAngajarii()))
                dataNasterii = 1;
            if(this.jucatori.get(i).getDataAngajarii().isBefore(this.jucatori.get(i).getDataNasterii()))
                dataNasterii = 1;
            if(this.jucatori.get(i).getRol().equals(""))
                rol = 1;
            if(this.jucatori.get(i).getRol() == null)
                rol = 2;
            if(this.jucatori.get(i).getEchipa() == null)
                echipa = 1;
            if(echipa != 1){
                echipa = 2;
                for(int j = 0; j < this.echipe.size(); j++)
                    if(this.jucatori.get(i).getEchipa().equals(this.echipe.get(j)))
                        echipa = 0;
            }
            if(nume != 0 || prenume != 0 || salariu != 0 || dataNasterii != 0 || dataAngajarii != 0 || echipa != 0 || rol != 0){
                if(ok == 1){
                    System.out.println("Au fost detectate date invalide la urmatorii Jucatori:");
                    ok = 0;
                    superOk = 0;
                }
                System.out.println("Jucatorul cu indicele " + i);
                if(nume == 1)
                    System.out.println("-Numele este vid");
                if(nume == 2)
                    System.out.println("-Numele este null");
                if(prenume == 1)
                    System.out.println("-Prenumele este vid");
                if(prenume == 2)
                    System.out.println("-Prenumele este null");
                if(salariu == 1)
                    System.out.println("-Salariul este negativ");
                if(dataNasterii == 1 || dataAngajarii == 1)
                    System.out.println("-Data angajarii este inainte de data nasterii");
                if(rol == 1)
                    System.out.println("-Rolul este vid");
                if(rol == 2)
                    System.out.println("-Rolul este null");
                if(echipa == 1)
                    System.out.println("-Jucatorul nu apartine unei echipe (camp null)");
                if(echipa == 2)
                    System.out.println("-Jucatorul apartine unei echipe inexistente (poate echipa a fost stearsa)");
            }
        }
        if(ok == 0)
            System.out.println();
        ok = 1;
        for(int i = 0; i < this.sponsori.size(); i++){
            int numeSponsor = 0;
            if(this.sponsori.get(i).getNumeSponsor() == null)
                numeSponsor = 2;
            else if(this.sponsori.get(i).getNumeSponsor().equals(""))
                numeSponsor = 1;

            if(numeSponsor != 0){
                if(ok == 1){
                    System.out.println("Au fost detectate date invalide la urmatorii Sponsori:");
                    ok = 0;
                    superOk = 0;
                }
                System.out.println("Sponsorul cu indicele " + i);
                if(numeSponsor == 1)
                    System.out.println("-Numele este vid");
                if(numeSponsor == 2)
                    System.out.println("-Numele este null");
            }
        }
        if(ok == 0)
            System.out.println();
        if(superOk == 1)
            System.out.println("Baza de date are toate datele valide\n");
    }

    public void comanda7(){
        System.out.println("Acestia sunt toti jucatorii, sortati descrescator dupa salariul lor");
        JucatorSlariuCompare jucatorSlariuCompare = new JucatorSlariuCompare();
        ArrayList<Jucator> aux = new ArrayList<Jucator>();
        for(int i = 0; i < this.jucatori.size(); i++){
            Jucator auxJuc = new Jucator(this.jucatori.get(i).getNume(),this.jucatori.get(i).getPrenume(),this.jucatori.get(i).getSalariu(),this.jucatori.get(i).getDataNasterii(),this.jucatori.get(i).getDataAngajarii(),this.jucatori.get(i).getRol(),this.jucatori.get(i).getEchipa());
            aux.add(auxJuc);
        }
        Collections.sort(aux, jucatorSlariuCompare);
        Collections.reverse(aux);
        for(int i = 0; i < aux.size(); i++)
            System.out.println(i + ". " + aux.get(i) + " " + aux.get(i).getSalariu());
    }

    public void comanda8(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pentru a simula un meci de fotbal, alegeti o competitie si doua echipe");
        System.out.println("Alegeti competitia");
        this.showCompetitie();
        System.out.println("Competitia pe care o doriti este = ");
        int indexCompetitie = scanner.nextInt();
        scanner.nextLine();
        Competitie competitie = this.competitii.get(indexCompetitie);

        System.out.println("Alegeti prima echipa");
        this.showEchipa();
        System.out.println("Echipa pe care o doriti este = ");
        int indexEchipa1 = scanner.nextInt();
        scanner.nextLine();
        Echipa echipa1 = this.echipe.get(indexEchipa1);

        System.out.println("Alegeti a doua echipa");
        this.showEchipa();
        System.out.println("Echipa pe care o doriti este = ");
        int indexEchipa2 = scanner.nextInt();
        scanner.nextLine();
        while(indexEchipa2 == indexEchipa1){
            System.out.println("A doua echipa aleasa trebuie sa fie diferita de prima");
            System.out.println("Echipa pe care o doriti este = ");
            indexEchipa2 = scanner.nextInt();
            scanner.nextLine();
        }
        Echipa echipa2 = this.echipe.get(indexEchipa2);
        ComentatorBun bun = new ComentatorBun("Tyler","Martin");
        ComentatorRau rau = new ComentatorRau("Drury","Peter");
        int care = -1;
        Random random = new Random();
        care = random.nextInt(1,3);
        if(care == 1)
            bun.inceputMeci(competitie,echipa1,echipa2,rau.getNumeComentator(),rau.getPrenumeComentator());
        else
            rau.inceputMeci(competitie,echipa1,echipa2,bun.getNumeComentator(),bun.getPrenumeComentator());
        care = random.nextInt(1,3);
        Jucator jucator = null;
        Jucator jucator1 = null;
        Jucator jucator2 = null;
        Jucator aux2 = null;
        Jucator aux1 = null;
        for(int i = 0; i < this.jucatori.size(); i++)
        {
            if(random.nextInt(1,21) <= 7 && this.jucatori.get(i).getEchipa().equals(this.echipe.get(indexEchipa2)))
                jucator = this.jucatori.get(i);
            if(this.jucatori.get(i).getEchipa().equals(this.echipe.get(indexEchipa2)))
                aux2 = this.jucatori.get(i);
        }
        if(jucator == null)
            jucator = aux2;
        if(care == 1)
            bun.reactieGol(jucator);
        else
            rau.reactieGol(jucator);
        care = random.nextInt(1,3);
        for(int i = 0; i < this.jucatori.size(); i++)
        {
            if(random.nextInt(1,21) <= 7 && this.jucatori.get(i).getEchipa().equals(this.echipe.get(indexEchipa2)))
                jucator2 = this.jucatori.get(i);
            if(random.nextInt(1,21) <= 7 && this.jucatori.get(i).getEchipa().equals(this.echipe.get(indexEchipa1)))
                jucator1 = this.jucatori.get(i);
            if(this.jucatori.get(i).getEchipa().equals(this.echipe.get(indexEchipa2)) && jucator2 == null)
                aux2 = this.jucatori.get(i);
            if(this.jucatori.get(i).getEchipa().equals(this.echipe.get(indexEchipa1)) && jucator1 == null)
                aux1 = this.jucatori.get(i);
        }
        if(jucator2 == null)
            jucator2 = aux2;
        if(jucator1 == null)
            jucator1 = aux1;
        if(care == 1)
            bun.reactieFault(jucator1, jucator2);
        else
            rau.reactieFault(jucator1, jucator2);
        care = random.nextInt(1,3);
        for(int i = 0; i < this.jucatori.size(); i++)
        {
            if(random.nextInt(1,21) <= 7 && this.jucatori.get(i).getEchipa().equals(this.echipe.get(indexEchipa2)))
                jucator = this.jucatori.get(i);
            if(this.jucatori.get(i).getEchipa().equals(this.echipe.get(indexEchipa2)))
                aux2 = this.jucatori.get(i);
        }
        if(jucator == null)
            jucator = aux2;
        if(care == 1)
            bun.reactieGol(jucator);
        else
            rau.reactieGol(jucator);
        care = random.nextInt(1,3);
        for(int i = 0; i < this.jucatori.size(); i++)
        {
            if(random.nextInt(1,21) <= 7 && this.jucatori.get(i).getEchipa().equals(this.echipe.get(indexEchipa2)))
                jucator = this.jucatori.get(i);
            if(this.jucatori.get(i).getEchipa().equals(this.echipe.get(indexEchipa2)))
                aux2 = this.jucatori.get(i);
        }
        if(jucator == null)
            jucator = aux2;
        if(care == 1){
            bun.comentariuJucator(jucator);
            rau.interactiuneColeg(bun.getNumeComentator(),bun.getPrenumeComentator());
        }
        else{
            rau.comentariuJucator(jucator);
            bun.interactiuneColeg(rau.getNumeComentator(),rau.getPrenumeComentator());
        }
        care = random.nextInt(1,3);
        if(care == 1)
            bun.sfarsitMeci(competitie,echipa1,echipa2,rau.getNumeComentator(),rau.getPrenumeComentator());
        else
            rau.sfarsitMeci(competitie,echipa1,echipa2,bun.getNumeComentator(),bun.getPrenumeComentator());
    }
    /*
    PENTRU ADD
     */
    public void addAntrenor(Antrenor antrenor){
        this.antrenori.add(antrenor);
    }

    public void addClub(Club club){
        this.cluburi.add(club);
    }

    public void addCompetitie(Competitie competitie){
        this.competitii.add(competitie);
    }

    public void addContract(Contract contract){
        this.contracte.add(contract);
    }

    public void addEchipa(Echipa echipa){
        this.echipe.add(echipa);
    }

    public void addJucator(Jucator jucator){
        this.jucatori.add(jucator);
    }

    public void addSponsor(Sponsor sponsor){
        this.sponsori.add(sponsor);
    }

    /*
    PENTRU SHOW
     */

    public void showAntrenor(){
        for(int i = 0; i < this.antrenori.size(); i++)
            System.out.println(i + ". " + this.antrenori.get(i));
    }
    public void showAntrenor(int i){
        System.out.println(this.antrenori.get(i));
        System.out.println("-Nume: " + this.antrenori.get(i).getNume());
        System.out.println("-Prenume: " + this.antrenori.get(i).getPrenume());
        System.out.println("-Salariu: " + this.antrenori.get(i).getSalariu());
        System.out.println("-Data nasterii: " + this.antrenori.get(i).getDataNasterii());
        System.out.println("-Data angajarii: " + this.antrenori.get(i).getDataAngajarii());
        System.out.println("-Club: " + this.antrenori.get(i).getClub());
    }

    public void showClub(){
        for(int i = 0; i < this.cluburi.size(); i++)
            System.out.println(i + ". " + this.cluburi.get(i));
    }
    public void showClub(int i){
        System.out.println(this.cluburi.get(i));
        System.out.println("-Nume club: " + this.cluburi.get(i).getNumeClub());
        System.out.println("-Tara club: " + this.cluburi.get(i).getTaraClub());
    }

    public void showCompetitie(){
        for(int i = 0; i < this.competitii.size(); i++)
            System.out.println(i + ". " + this.competitii.get(i));
    }
    public void showCompetitie(int i){
        System.out.println(this.competitii.get(i));
        System.out.println("-Nume competitie: " + this.competitii.get(i).getNumeCompetitie());
        System.out.println("-Nume locatie: " + this.competitii.get(i).getLocatie());
        System.out.println("-Data inceperii competitiei: " + this.competitii.get(i).getDataInceput());
        System.out.println("-Data sfarsitului competitiei: " + this.competitii.get(i).getDataFinal());
    }

    public void showContract(){
        for(int i = 0; i < this.contracte.size(); i++)
            System.out.println(i + ". " + this.contracte.get(i));
    }
    public void showContract(int i){
        System.out.println(this.contracte.get(i));
        System.out.println("-Sponsor: " + this.contracte.get(i).getSponsor());
        System.out.println("-Echipa: " + this.contracte.get(i).getEchipa());
        System.out.println("-Suma suponsorizarii: " + this.contracte.get(i).getSuma());
        System.out.println("-Data inceperii sponsorizarii: " + this.contracte.get(i).getDataInceput());
        System.out.println("-Data finalizarii sponsorizarii: " + this.contracte.get(i).getDataFinal());
    }

    public void showEchipa(){
        for(int i = 0; i < this.echipe.size(); i++)
            System.out.println(i + ". " + this.echipe.get(i));
    }
    public void showEchipa(int i){
        System.out.println(this.echipe.get(i));
        System.out.println("-Nume echipa: " + this.echipe.get(i).getNumeEchipa());
        System.out.println("-Numarul de membri: " + this.echipe.get(i).getNrMembri());
        System.out.println("-Clubul echipei: " + this.echipe.get(i).getClub());
        System.out.println("-Antrenorul echipei: " + this.echipe.get(i).getAntrenor());
    }

    public void showJucator(){
        for(int i = 0; i < this.jucatori.size(); i++)
            System.out.println(i + ". " + this.jucatori.get(i));
    }
    public void showJucator(int i){
        System.out.println(this.jucatori.get(i));
        System.out.println("-Nume: " + this.jucatori.get(i).getNume());
        System.out.println("-Prenume: " + this.jucatori.get(i).getPrenume());
        System.out.println("-Salariu: " + this.jucatori.get(i).getSalariu());
        System.out.println("-Data nasterii: " + this.jucatori.get(i).getDataNasterii());
        System.out.println("-Data angajarii: " + this.jucatori.get(i).getDataAngajarii());
        System.out.println("-Rol: " + this.jucatori.get(i).getRol());
        System.out.println("-Echipa: " + this.jucatori.get(i).getEchipa());
    }

    public void showJucatorSalariu(){
        for(int i = 0; i < this.jucatori.size(); i++)
            System.out.println(i + ". " + this.jucatori.get(i) + " " + this.jucatori.get(i).getSalariu());
    }
    public void showSponsor(){
        for(int i = 0; i < this.sponsori.size(); i++){
            System.out.println(i + ". " + this.sponsori.get(i));
            System.out.println("-Nume sponsor: " + this.sponsori.get(i).getNumeSponsor());
        }

    }

    public void showSponsor(int i){
        System.out.println(this.sponsori.get(i));

    }

    /*
    PENTRU EDIT
     */
    public void editAntrenor(int i){

    }
    public void editClub(int i){

    }
    public void editCompetitie(int i){

    }
    public void editContract(int i){

    }
    public void editEchipa(int i){

    }
    public void editJucator(int i){

    }
    public void editSponsor(int i){

    }
    /*
    PENTRU DELETE
     */
    public void deleteAntrenor(int i){
        this.antrenori.remove(i);
    }

    public void deleteClub(int i){
        this.cluburi.remove(i);
    }

    public void deleteCompetitie(int i){
        this.competitii.remove(i);
    }

    public void deleteContract(int i){
        this.contracte.remove(i);
    }

    public void deleteEchipa(int i){
        this.echipe.remove(i);
    }

    public void deleteJucator(int i){
        this.jucatori.remove(i);
    }

    public void deleteSponsor(int i){
        this.sponsori.remove(i);
    }

    /*
    POPULAREA BAZEI DE DATE CU VALORI
     */
    public void populare(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);

        Club club = new Club("FC Voluntari","Romania");
        this.cluburi.add(club);
        club = new Club("CS Otopeni","Romania");
        this.cluburi.add(club);
        club = new Club("FC Snagov","Germania");
        this.cluburi.add(club);
        club = new Club("CS Buftea","SUA");
        this.cluburi.add(club);
        club = new Club("Inter Pantelimon","Romania");
        this.cluburi.add(club);
        ///CLUBURI INVALIDE
        club = new Club("","Narnia");
        this.cluburi.add(club);

        Competitie competitie = new Competitie("FIFA ClaseBazaDeDate.Club World Cup","Maroc",LocalDate.parse("01-02-2023",formatter),LocalDate.parse("11-02-2023",formatter));
        this.competitii.add(competitie);
        competitie = new Competitie("AFC Asian Cup","China",LocalDate.parse("16-06-2023",formatter),LocalDate.parse("16-07-2023",formatter));
        this.competitii.add(competitie);
        competitie = new Competitie("UEFA Super Cup","Ak Bars Arena - Kazan",LocalDate.parse("20-08-2023",formatter),LocalDate.parse("29-08-2023",formatter));
        this.competitii.add(competitie);
        competitie = new Competitie("Euro 2024","Germania",LocalDate.parse("14-06-2024",formatter),LocalDate.parse("14-07-2024",formatter));
        this.competitii.add(competitie);
        competitie = new Competitie("Women's World Cup","Australia",LocalDate.parse("10-07-2023",formatter),LocalDate.parse("20-08-2023",formatter));
        this.competitii.add(competitie);
        ///COMPETITII INVALIDE
        competitie = new Competitie("Liga smecherilor","Luna (satelitul natural)",LocalDate.parse("10-07-2077",formatter),LocalDate.parse("20-08-2023",formatter));
        this.competitii.add(competitie);

        Sponsor sponsor = new Sponsor("Sony");
        this.sponsori.add(sponsor);
        sponsor = new Sponsor("Pepsi");
        this.sponsori.add(sponsor);
        sponsor = new Sponsor("Shell");
        this.sponsori.add(sponsor);
        sponsor = new Sponsor("Coca Cola");
        this.sponsori.add(sponsor);
        sponsor = new Sponsor("Joma");
        this.sponsori.add(sponsor);
        sponsor = new Sponsor("Serenity");
        this.sponsori.add(sponsor);
        ///SPONSORI INVALIZI
        sponsor = new Sponsor(null);
        this.sponsori.add(sponsor);


        Antrenor antrenor = new Antrenor("Lucesc","Mircea",500,LocalDate.parse("29-07-1945",formatter),LocalDate.parse("01-01-2023",formatter),this.cluburi.get(0));
        this.antrenori.add(antrenor);
        antrenor = new Antrenor("Iordanescu","Anghel",750,LocalDate.parse("04-05-1950",formatter),LocalDate.parse("02-01-2023",formatter),this.cluburi.get(1));
        this.antrenori.add(antrenor);
        antrenor = new Antrenor("Petrescu","Dan",590,LocalDate.parse("22-12-1967",formatter),LocalDate.parse("03-01-2023",formatter),this.cluburi.get(2));
        this.antrenori.add(antrenor);
        antrenor = new Antrenor("Olaroiu","Cosmin",600,LocalDate.parse("11-06-1969",formatter),LocalDate.parse("04-01-2023",formatter),this.cluburi.get(3));
        this.antrenori.add(antrenor);
        antrenor = new Antrenor("Rednic","Mircea",600,LocalDate.parse("09-04-1962",formatter),LocalDate.parse("05-01-2023",formatter),this.cluburi.get(4));
        this.antrenori.add(antrenor);
        ///ANTRENORI INVALIZI
        antrenor = new Antrenor("Ghilgames","Enkidu",-600,LocalDate.parse("09-04-1962",formatter),LocalDate.parse("05-01-1937",formatter),null);
        this.antrenori.add(antrenor);

        Echipa echipa = new Echipa("FCSB",this.cluburi.get(0),this.antrenori.get(0));
        this.echipe.add(echipa);
        echipa = new Echipa("CFR Cluj",this.cluburi.get(1),this.antrenori.get(1));
        this.echipe.add(echipa);
        echipa = new Echipa("FC Dinamo Bucuresti",this.cluburi.get(2),this.antrenori.get(2));
        this.echipe.add(echipa);
        echipa = new Echipa("FC Rapid Bucuresti",this.cluburi.get(3),this.antrenori.get(3));
        this.echipe.add(echipa);
        echipa = new Echipa("FC Voluntari",this.cluburi.get(4),this.antrenori.get(4));
        this.echipe.add(echipa);
        ///ECHIPE INVALIDE
        echipa = new Echipa("Otelu Galati",this.cluburi.get(4),null);
        this.echipe.add(echipa);

        Contract contract = new Contract(this.sponsori.get(0),this.echipe.get(0),1307,LocalDate.parse("01-05-2023",formatter),LocalDate.parse("01-05-2024",formatter));
        this.contracte.add(contract);
        contract = new Contract(this.sponsori.get(1),this.echipe.get(0),1582,LocalDate.parse("01-06-2023",formatter),LocalDate.parse("01-06-2024",formatter));
        this.contracte.add(contract);
        contract = new Contract(this.sponsori.get(2),this.echipe.get(0),1999,LocalDate.parse("01-07-2023",formatter),LocalDate.parse("01-07-2024",formatter));
        this.contracte.add(contract);
        contract = new Contract(this.sponsori.get(3),this.echipe.get(1),1036,LocalDate.parse("01-08-2023",formatter),LocalDate.parse("01-08-2024",formatter));
        this.contracte.add(contract);
        contract = new Contract(this.sponsori.get(4),this.echipe.get(2),1585,LocalDate.parse("01-09-2023",formatter),LocalDate.parse("01-09-2024",formatter));
        this.contracte.add(contract);
        contract = new Contract(this.sponsori.get(0),this.echipe.get(3),1813,LocalDate.parse("01-10-2023",formatter),LocalDate.parse("01-10-2024",formatter));
        this.contracte.add(contract);
        contract = new Contract(this.sponsori.get(0),this.echipe.get(4),1005,LocalDate.parse("01-11-2023",formatter),LocalDate.parse("01-11-2024",formatter));
        this.contracte.add(contract);
        contract = new Contract(this.sponsori.get(1),this.echipe.get(1),1225,LocalDate.parse("01-12-2023",formatter),LocalDate.parse("01-12-2024",formatter));
        this.contracte.add(contract);
        contract = new Contract(this.sponsori.get(0),this.echipe.get(4),1617,LocalDate.parse("01-05-2024",formatter),LocalDate.parse("01-05-2025",formatter));
        this.contracte.add(contract);
        contract = new Contract(this.sponsori.get(4),this.echipe.get(0),1392,LocalDate.parse("01-06-2024",formatter),LocalDate.parse("01-06-2025",formatter));
        this.contracte.add(contract);
        ///CONTRACTE INVALIDE
        contract = new Contract(null,this.echipe.get(0),-1392,LocalDate.parse("01-06-2024",formatter),LocalDate.parse("01-06-2025",formatter));
        this.contracte.add(contract);

        Jucator jucator = new Jucator("Marin","Razvan",723,LocalDate.parse("23-05-1996",formatter),LocalDate.parse("06-01-2023",formatter),"Atacant",this.echipe.get(0));
        this.jucatori.add(jucator);
        jucator = new Jucator("Hagi","Gheorghe",618,LocalDate.parse("05-02-1965",formatter),LocalDate.parse("07-01-2023",formatter),"Mijlocas",this.echipe.get(0));
        this.jucatori.add(jucator);
        jucator = new Jucator("Man","Dennis",719,LocalDate.parse("26-07-1998",formatter),LocalDate.parse("08-01-2023",formatter),"Fundas",this.echipe.get(0));
        this.jucatori.add(jucator);
        jucator = new Jucator("Popescu","Gheorghe",840,LocalDate.parse("09-10-1967",formatter),LocalDate.parse("09-01-2023",formatter),"Portar",this.echipe.get(0));
        this.jucatori.add(jucator);
        jucator = new Jucator("Chivu","Cristian",734,LocalDate.parse("26-10-1980",formatter),LocalDate.parse("10-01-2023",formatter),"Rezerva",this.echipe.get(0));
        this.jucatori.add(jucator);
        jucator = new Jucator("Deac","Ciprian",686,LocalDate.parse("26-10-2000",formatter),LocalDate.parse("11-01-2023",formatter),"Atacant",this.echipe.get(1));
        this.jucatori.add(jucator);
        jucator = new Jucator("Adjei-Boateng","Bismark",637,LocalDate.parse("01-03-1995",formatter),LocalDate.parse("12-01-2023",formatter),"Mijlocas",this.echipe.get(1));
        this.jucatori.add(jucator);
        jucator = new Jucator("Letica","Karlo",928,LocalDate.parse("08-07-1993",formatter),LocalDate.parse("13-01-2023",formatter),"Fundas",this.echipe.get(1));
        this.jucatori.add(jucator);
        jucator = new Jucator("Manea","Cristian",559,LocalDate.parse("11-05-2000",formatter),LocalDate.parse("14-01-2023",formatter),"Portar",this.echipe.get(1));
        this.jucatori.add(jucator);
        jucator = new Jucator("Petrila","Claudiu",956,LocalDate.parse("11-05-2000",formatter),LocalDate.parse("15-01-2023",formatter),"Rezerva",this.echipe.get(1));
        this.jucatori.add(jucator);
        jucator = new Jucator("Hindrich","Otto",876,LocalDate.parse("14-04-1999",formatter),LocalDate.parse("16-01-2023",formatter),"Atacant",this.echipe.get(1));
        this.jucatori.add(jucator);
        jucator = new Jucator("Torje","Gabriel",832,LocalDate.parse("05-03-1960",formatter),LocalDate.parse("17-01-2023",formatter),"Atacant",this.echipe.get(2));
        this.jucatori.add(jucator);
        jucator = new Jucator("Pendo","Jaime",735,LocalDate.parse("01-09-1977",formatter),LocalDate.parse("18-01-2023",formatter),"Mijlocas",this.echipe.get(2));
        this.jucatori.add(jucator);
        jucator = new Jucator("Irobiso","Christian",725,LocalDate.parse("29-11-1991",formatter),LocalDate.parse("19-01-2023",formatter),"Fundas",this.echipe.get(2));
        this.jucatori.add(jucator);
        jucator = new Jucator("Filip","Steliano",682,LocalDate.parse("11-06-1995",formatter),LocalDate.parse("20-01-2023",formatter),"Portar",this.echipe.get(2));
        this.jucatori.add(jucator);
        jucator = new Jucator("Desire","Azankpo",955,LocalDate.parse("06-05-1998",formatter),LocalDate.parse("21-01-2023",formatter),"Rezerva",this.echipe.get(2));
        this.jucatori.add(jucator);
        jucator = new Jucator("Ilie","Rares",773,LocalDate.parse("05-05-1987",formatter),LocalDate.parse("22-01-2023",formatter),"Atacant",this.echipe.get(3));
        this.jucatori.add(jucator);
        jucator = new Jucator("Sapunaru","Cristian",741,LocalDate.parse("10-07-1966",formatter),LocalDate.parse("23-01-2023",formatter),"Mijlocas",this.echipe.get(3));
        this.jucatori.add(jucator);
        jucator = new Jucator("Costache","Valentin",754,LocalDate.parse("15-09-1985",formatter),LocalDate.parse("24-01-2023",formatter),"Fundas",this.echipe.get(3));
        this.jucatori.add(jucator);
        jucator = new Jucator("Ciobanu","Andrei",748,LocalDate.parse("20-11-1994",formatter),LocalDate.parse("25-01-2023",formatter),"Portar",this.echipe.get(3));
        this.jucatori.add(jucator);
        jucator = new Jucator("Sefer","Antonio",676,LocalDate.parse("27-01-1983",formatter),LocalDate.parse("26-01-2023",formatter),"Rezerva",this.echipe.get(3));
        this.jucatori.add(jucator);
        jucator = new Jucator("Meleke","Ulrich",927,LocalDate.parse("13-02-1972",formatter),LocalDate.parse("27-01-2023",formatter),"Atacant",this.echipe.get(4));
        this.jucatori.add(jucator);
        jucator = new Jucator("Rata","Vadim",884,LocalDate.parse("19-04-1991",formatter),LocalDate.parse("28-01-2023",formatter),"Mijlocas",this.echipe.get(4));
        this.jucatori.add(jucator);
        jucator = new Jucator("Popa","Mihai",947,LocalDate.parse("21-06-2000",formatter),LocalDate.parse("29-01-2023",formatter),"Fundas",this.echipe.get(4));
        this.jucatori.add(jucator);
        jucator = new Jucator("Budescu","Constantin",691,LocalDate.parse("30-08-1969",formatter),LocalDate.parse("30-01-2023",formatter),"Portar",this.echipe.get(4));
        this.jucatori.add(jucator);
        jucator = new Jucator("Nemec","Adam",853,LocalDate.parse("05-10-1988",formatter),LocalDate.parse("31-01-2023",formatter),"Rezerva",this.echipe.get(4));
        this.jucatori.add(jucator);
        ///JUCATORI INVALIZI
        jucator = new Jucator("Romeo","Fantastik",69,LocalDate.parse("05-10-1988",formatter),LocalDate.parse("31-01-1700",formatter),"Rezerva",this.echipe.get(4));
        this.jucatori.add(jucator);
    }
}
