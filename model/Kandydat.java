package model;

import java.time.LocalDate;

public class Kandydat extends Obywatel {
    private String partia;
    private String hobby;

    public Kandydat(String imie, String nazwisko, String pesel, LocalDate dataUrodzenia) {
        super(imie, nazwisko, pesel, dataUrodzenia);
        this.partia = "Bezpartyjny";
        this.hobby = "Brak";
    }

    public void setPartia(String partia) {
        this.partia = partia;
    }
    
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getPartia() {
        return partia;
    }

    public String getHobby() {
        return hobby;
    }
}