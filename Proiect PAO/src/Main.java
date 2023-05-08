import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/*
    Clasa Main. De aici facem apel catre servicii

    De adaugat pachete pentru clase, Interfete, sortari
 */
public class Main {
    public static void main(String[] args) {
        Service service = Service.getInstance();
        System.out.println("Bine ati venit!");
        System.out.println("Va rugam ca atunci cand introduceti date calendaristice, ele sa respecte formatul dd-MM-yyyy, de exemplu 25-05-2000");
        System.out.println("Va rugam ca atunci cand introduceti numere cu virgula, sa folositi virgula, nu punctul");
        System.out.println("Va rugam ca atunci cand vi se cere sa alegeti intrarea pe care doriti sa o accesati, sa introduceti numarul de ordine din lista corespunzator intrarii");
        System.out.println("Acestea sunt actiunile/interogarile ce pot fi efectuate. Introduceti numarul corespunzator cerintei dumneavoastra");
        int comanda = -1;
        service.populare();
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        while(comanda != 0){
            System.out.println("1. Adaugati o intrare in baza de date");
            System.out.println("2. Modificati o intrare din baza de date");
            System.out.println("3. Stergeti o intrare din baza de date");
            System.out.println("4. Vizualizati informatii despre o intrare din baza de date");
            System.out.println("5. Obtineti lista angajatilor unui club");
            System.out.println("6. Verificati integritatea bazei de date");
            System.out.println("7. Obtineti lista jucatorilor din toate cluburile sortati dupa salariu");
            System.out.println("8. Simulati un meci de fotbal");
            System.out.println("0. Terminati programul");
            System.out.print("Comanda dumneavoastra este = ");
            comanda = scanner.nextInt();
            System.out.println();
            if(comanda == 0)
                break;
            if(comanda == 1)
                service.comanda1();
            if(comanda == 2)
                service.comanda2();
            if(comanda == 3)
                service.comanda3();
            if(comanda == 4)
                service.comanda4();
            if(comanda == 5)
                service.comanda5();
            if(comanda == 6)
                service.comanda6();
            if(comanda == 7)
                service.comanda7();
            if(comanda == 8)
                service.comanda8();
            if(comanda < 0 || comanda > 8)
                System.out.println("!!! Introduceti o comanda valida !!!");

            }
        System.out.println("Sesiunea s-a terminat. Programul a fost inchis");
    }
}