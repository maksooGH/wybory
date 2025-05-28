package model;
import java.time.LocalDate;

public class Glos {
    private String wyborcaPesel;
    private String wybranyPesel;
    private LocalDate dataOddania;

    public Glos(String wyborcaPesel, String wybranyPesel) {
        this.wyborcaPesel = wyborcaPesel;
        this.wybranyPesel = wybranyPesel;
        this.dataOddania = LocalDate.now();
    }

    public String getWyborcaPesel() {
        return wyborcaPesel;
    }

    public String getWybranyPesel() {
        return wybranyPesel;
    }

    public LocalDate getDataOddania() {
        return dataOddania;
    }
}