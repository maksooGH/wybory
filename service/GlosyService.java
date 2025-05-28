package service;
import model.*;
import utils.*;

import java.util.*;

public class GlosyService {
    public static List<Glos> listaGlosow = new ArrayList<>();

    public static void oddajGlos(String peselWyborcy, String peselKandydata) {
        listaGlosow.add(new Glos(peselWyborcy, peselKandydata));
    }

    public static void wyswietlWyniki() {
        Map<String, Integer> wyniki = new HashMap<>();
        for (Glos g : listaGlosow) {
            wyniki.put(g.getWybranyPesel(), wyniki.getOrDefault(g.getWybranyPesel(), 0) + 1);
        }
        for (Kandydat k : SprawdzKandy.listaKandydatow) {
            int liczba = wyniki.getOrDefault(k.getPesel(), 0);
            System.out.println(k.getImie() + " " + k.getNazwisko() + " - " + liczba + " głosów");
        }
    }

    public static void wczytajGlosyZPliku(String sciezka) {
        listaGlosow = FileHelper.wczytajGlosy(sciezka);
    }

    public static void zapiszGlosyDoPliku(String sciezka) {
        FileHelper.zapiszGlosy(listaGlosow, sciezka);
    }
}