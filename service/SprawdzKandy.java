package service;
import model.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class SprawdzKandy {
    public static List<Kandydat> listaKandydatow = new ArrayList<>();

    public static void wczytajKandydatowZPliku(String sciezka) {
        try (BufferedReader br = new BufferedReader(new FileReader(sciezka))) {
            String linia;
            boolean naglowek = true;
            while ((linia = br.readLine()) != null) {
                if (naglowek) { naglowek = false; continue; }
                String[] dane = linia.split(",");
                Kandydat k = new Kandydat(dane[1], dane[2], dane[0], LocalDate.parse(dane[3]));
                k.setPartia(dane[4]);
                k.setHobby(dane[5]);
                listaKandydatow.add(k);
            }
        } catch (IOException e) {
            System.out.println("Błąd wczytywania kandydatów: " + e.getMessage());
        }
    }
}