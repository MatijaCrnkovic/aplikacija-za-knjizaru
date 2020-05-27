/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import dao.implementacija.DBConnect;
import dao.interfaces.DAOZaposleni;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import model.Zaposleni;

/**
 *
 * @author Matija
 */
public class ZaposleniKontroler {
    private final DAOZaposleni daoZaposleni;

    public ZaposleniKontroler() {
        daoZaposleni = new DBConnect().getZaposleniDAO();
    }
    public Zaposleni getByID(Integer ID) {
        return daoZaposleni.pretragaID(ID);
    }
    
    public boolean dodajZaposlenog(String JMBG, String ime, String prezime, String adresa, 
            String grad, String brTelefona, String email, Date datumZaposlenja, 
            String pozicija, Integer plata, String username, String password) {
        Zaposleni zaposleni = new Zaposleni();
        
        zaposleni.setJMBG(JMBG);
        zaposleni.setIme(ime);
        zaposleni.setPrezime(prezime);
        zaposleni.setAdresa(adresa);
        zaposleni.setGrad(grad);
        zaposleni.setBrTelefona(brTelefona);
        zaposleni.setEmail(email);
        zaposleni.setDatumZaposlenja(datumZaposlenja);
        zaposleni.setPozicija(pozicija);
        zaposleni.setPlata(plata);
        zaposleni.setUsername(username);
        zaposleni.setPassword(password);

        return daoZaposleni.dodajZaposlenog(zaposleni);
    }
    
    public boolean obrisiZaposlenog(Integer ID) {
        return daoZaposleni.obrisiZaposlenog(daoZaposleni.pretragaID(ID));
    }
    
    public ArrayList<Zaposleni> pronadjiSve() {
        return new ArrayList(daoZaposleni.pronadjiSve());
    }
    
    public ArrayList<Zaposleni> proveriZaposlenog(Integer ID, String JMBG, String ime, String prezime, 
            String adresa, String grad, String brTelefona, String email, Date datumZaposlenja, 
            String pozicija, Integer plata, String username, String password) {

            Zaposleni zaposleni = new Zaposleni();
            
            zaposleni.setID(ID);
            zaposleni.setJMBG(JMBG);
            zaposleni.setIme(ime);
            zaposleni.setPrezime(prezime);
            zaposleni.setAdresa(adresa);
            zaposleni.setGrad(grad);
            zaposleni.setBrTelefona(brTelefona);
            zaposleni.setEmail(email);
            zaposleni.setDatumZaposlenja(datumZaposlenja);
            zaposleni.setPozicija(pozicija);
            zaposleni.setPlata(plata);
            zaposleni.setUsername(username);
            zaposleni.setPassword(password);

            List<Zaposleni> result = daoZaposleni.proveriZaposlenog(zaposleni);
            return new ArrayList(result);
    }
    
    public boolean izmeniZaposlenog(Integer ID, String JMBG, String ime, String prezime, 
            String adresa, String grad, String brTelefona, String email, String pozicija, Integer plata) {

        Zaposleni zaposleni = new Zaposleni();
        zaposleni.setID(ID);
        zaposleni.setJMBG(JMBG);
        zaposleni.setIme(ime);
        zaposleni.setPrezime(prezime);
        zaposleni.setAdresa(adresa);
        zaposleni.setGrad(grad);
        zaposleni.setBrTelefona(brTelefona);
        zaposleni.setEmail(email);
        zaposleni.setPozicija(pozicija);
        zaposleni.setPlata(plata);

        return daoZaposleni.izmeniZaposlenog(zaposleni);
    }
    
    public String ispisZaposlenog(Integer ID) {
        Zaposleni zaposleni = daoZaposleni.pretragaID(ID);
        if (zaposleni == null) {
            return null;
        }
        return zaposleni.getIme()+ " " + zaposleni.getPrezime();
    }
    
    public boolean promeniSifru(Integer ID, String password) {
        Zaposleni zaposleni = daoZaposleni.pretragaID(ID);
        
        zaposleni.setID(ID);
        zaposleni.setPassword(password);
        
        return daoZaposleni.promeniPassword(zaposleni);
    }
}
