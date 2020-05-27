/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Matija
 */
public class Knjiga {
    private String ISBN;
    private String imeKnjige;
    private String pisac;
    private String kategorija;
    private Integer godinaIzdavanja;
    private double cena;
    
    public Knjiga() {
    }
    
    public Knjiga(String ISBN, String imeKnjige, String pisac, String kategorija, 
            Integer godinaIzdavanja, double cena) {
        this.ISBN = ISBN;
        this.imeKnjige = imeKnjige;
        this.pisac = pisac;
        this.kategorija = kategorija;
        this.godinaIzdavanja = godinaIzdavanja;
        this.cena = cena;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    
    public String getImeKnjige() {
        return imeKnjige;
    }

    public void setImeKnjige(String imeKnjige) {
        this.imeKnjige = imeKnjige;
    }
    
    public String getPisac() {
        return pisac;
    }

    public void setPisac(String pisac) {
        this.pisac = pisac;
    }
    
    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }
    
    public Integer getGodinaIzdavanja() {
        return godinaIzdavanja;
    }

    public void setGodinaIzdavanja(Integer godinaIzdavanja) {
        this.godinaIzdavanja = godinaIzdavanja;
    }
    
    public double getCena() {
        return cena;
    }
    
    public void setCena(double cena) {
        this.cena = cena;
    }
}
