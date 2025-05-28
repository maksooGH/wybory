package utils;
import model.Glos;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class FileHelper {
    public static List<Glos> wczytajGlosy(String sciezka) {
        List<Glos> glosy = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(sciezka))) {
            String linia;
            boolean naglowek = true;
            while ((linia = br.readLine()) != null) {
                if (naglowek) { naglowek = false; continue; }
                String[] dane = linia.split(",");
                Glos g = new Glos(dane[0], dane[1]);
                glosy.add(g);
            }
        } catch (IOException e) {
            System.out.println("Błąd odczytu głosów: " + e.getMessage());
        }
        return glosy;
    }

    public static void zapiszGlosy(List<Glos> glosy, String sciezka) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(sciezka))) {
            pw.println("PESEL_WYBORCY,PESEL_KANDYDATA,DATA");
            for (Glos g : glosy) {
                pw.println(g.getWyborcaPesel() + "," + g.getWybranyPesel() + "," + g.getDataOddania());
            }
        } catch (IOException e) {
            System.out.println("Błąd zapisu głosów: " + e.getMessage());
        }
    }
}