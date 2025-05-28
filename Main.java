import model.*;
import service.*;
import utils.*;

import java.math.RoundingMode;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SprawdzKandy.wczytajKandydatowZPliku("data/kandydaci.csv");
        GlosyService.wczytajGlosyZPliku("data/glosy.csv");

        System.out.println("--- WYBORY PREZYDENCKIE 2025 ---");

        boolean running = true;
        while (running) {
            System.out.println("\nWybierz opcję:");
            System.out.println("1. Oddaj głos");
            System.out.println("2. Wyświetl wyniki głosowania");
            System.out.println("3. Zakup głosy (debug)");
            System.out.println("4. Wyjście");
            System.out.print("Wybieram opcję: ");
            int wybor = scanner.nextInt();
            scanner.nextLine();

            switch (wybor) {
                case 1:
                    System.out.print("Podaj swój PESEL: ");
                    String pesel = scanner.nextLine();
                    System.out.println("Lista kandydatów:");
                    for (int i = 0; i < SprawdzKandy.listaKandydatow.size(); i++) {
                        System.out.println((i + 1) + ". " + SprawdzKandy.listaKandydatow.get(i).getImie() + " " + SprawdzKandy.listaKandydatow.get(i).getNazwisko());
                    }
                    System.out.print("Wybierz numer kandydata: ");
                    int index = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (index >= 0 && index < SprawdzKandy.listaKandydatow.size()) {
                        GlosyService.oddajGlos(pesel, SprawdzKandy.listaKandydatow.get(index).getPesel());
                        System.out.println("Głos został zapisany.");
                    } else {
                        System.out.println("Nieprawidłowy numer kandydata.");
                    }
                    break;

                case 2:
                    GlosyService.wyswietlWyniki();
                    break;

                case 3:
                    BigDecimal cenaZaGlos = BigDecimal.valueOf(199.99);
                    System.out.println("Cena za głos: " + cenaZaGlos + " PLN");

                    System.out.print("Podaj kwotę PLN, którą chcesz przeznaczyć na zakup głosów (np. 900,00): ");
                    String budzet_str = scanner.nextLine();
                    BigDecimal budzet = new BigDecimal(budzet_str.replace(",", "."));
                    if (budzet.compareTo(cenaZaGlos) < 0) {
                        System.out.println("Podana kwota jest za mała na zakup choćby jednego głosu.");
                        break;
                    }
                    BigDecimal reszta = budzet.remainder(cenaZaGlos);
                    
                    System.out.println("Reszta do zwrotu: " + reszta + " PLN");
                    int liczbaGlosow = budzet.divide(cenaZaGlos,  0, RoundingMode.DOWN).intValue();
                    System.out.println("Liczba głosów do zakupu: " + liczbaGlosow);
                    System.out.println("Lista kandydatów:");
                    for (int i = 0; i < SprawdzKandy.listaKandydatow.size(); i++) {
                        System.out.println((i + 1) + ". " + SprawdzKandy.listaKandydatow.get(i).getImie() + " " + SprawdzKandy.listaKandydatow.get(i).getNazwisko());
                    }
                    System.out.print("Wybierz numer kandydata do zakupu głosów: ");
                    int kandydatIndex = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (kandydatIndex >= 0 && kandydatIndex < SprawdzKandy.listaKandydatow.size()) {
                        String peselKandydata = SprawdzKandy.listaKandydatow.get(kandydatIndex).getPesel();
                        for (int i = 0; i < liczbaGlosow; i++) {
                            GlosyService.oddajGlos("FALSZYWY" + i, peselKandydata);
                        }
                        System.out.println("Dodano " + liczbaGlosow + " kupionych głosów na wybranego kandydata.");
                    } else {
                        System.out.println("Nieprawidłowy numer kandydata.");
                    }
                    break;

                case 4:
                    running = false;
                    GlosyService.zapiszGlosyDoPliku("data/glosy.csv");
                    System.out.println("Dziękujemy za skorzystanie z systemu.");
                    break;

                default:
                    System.out.println("Nieznana opcja.");
            }
        }
    }
}
