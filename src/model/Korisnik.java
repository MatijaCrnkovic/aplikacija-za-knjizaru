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
public class Korisnik extends Osoba {
    
    private Integer brojClanskeKarte;
    
    public Korisnik() {
        
    }
    
    public Korisnik(Integer ID, String JMBG, String ime, String prezime, String adresa, String grad, String brTelefona, String email) {
        super(ID, JMBG, ime, prezime, adresa, grad, brTelefona, email);
        this.brojClanskeKarte = brojClanskeKarte;
    }
    
    public Integer getBrojClanskeKarte() {
        return brojClanskeKarte;
    }
    
    public void setBrojClanskeKarte(Integer brojClanskeKarte) {
        this.brojClanskeKarte = brojClanskeKarte;
    }
}
