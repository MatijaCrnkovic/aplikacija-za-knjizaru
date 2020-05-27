/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import dao.implementacija.DBConnect;
import dao.interfaces.DAOKorisnici;
import java.util.ArrayList;
import java.util.List;
import model.Korisnik;

/**
 *
 * @author Matija
 */
public class KorisniciKontroler {
    
    private final DAOKorisnici daoKorisnici;

    public KorisniciKontroler() {
        daoKorisnici = new DBConnect().getKorisnikDAO();
    }
    
    public Korisnik pretragaID(Integer brojClanskeKarte) {
        return daoKorisnici.pretragaID(brojClanskeKarte);
    }
    
    public boolean dodajKorisnika(String JMBG, String ime, String prezime, String adresa, String grad, String brTelefona, String email) {
        
        Korisnik korisnik = new Korisnik();

        korisnik.setJMBG(JMBG);
        korisnik.setIme(ime);
        korisnik.setPrezime(prezime);
        korisnik.setAdresa(adresa);
        korisnik.setGrad(grad);
        korisnik.setBrTelefona(brTelefona);
        korisnik.setEmail(email);

        return daoKorisnici.dodajKorisnika(korisnik);
    }
    
    public boolean izmeniKorisnika(String JMBG, String ime, String prezime, String adresa, String grad, String brTelefona, String email) {

        Korisnik korisnik = new Korisnik();

        korisnik.setJMBG(JMBG);
        korisnik.setIme(ime);
        korisnik.setPrezime(prezime);
        korisnik.setAdresa(adresa);
        korisnik.setGrad(grad);
        korisnik.setBrTelefona(brTelefona);
        korisnik.setEmail(email);

        return daoKorisnici.izmeniKorisnika(korisnik);
    }
    
    public boolean obrisiKorisnika(Integer brojClanskeKarte) {
        return daoKorisnici.obrisiKorisnika(daoKorisnici.pretragaID(brojClanskeKarte));
    }
    
    public ArrayList<Korisnik> izlistajTabelu() {
        return new ArrayList(daoKorisnici.izlistajTabelu());
    }
}
