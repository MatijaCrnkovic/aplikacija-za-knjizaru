/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import dao.implementacija.DBConnect;
import dao.interfaces.DAOKnjiga;
import java.util.ArrayList;
import java.util.List;
import model.Knjiga;

/**
 *
 * @author Matija
 */
public class KnjigaKontroler {
    
    private final DAOKnjiga daoKnjiga;

    public KnjigaKontroler() {
        daoKnjiga = new DBConnect().getKnjigaDAO();
    }
    
    public Knjiga pretragaISBN(String ISBN) {
        return daoKnjiga.pretragaPoISBN(ISBN);
    }
    
    public boolean dodajKnjigu(String ISBN, String imeKnjige, String pisac, String kategorija, Integer godinaIzdavanja, double cena) {
        
        Knjiga knjiga = new Knjiga();

        knjiga.setISBN(ISBN);
        knjiga.setImeKnjige(imeKnjige);
        knjiga.setPisac(pisac);
        knjiga.setKategorija(kategorija);
        knjiga.setGodinaIzdavanja(godinaIzdavanja);
        knjiga.setCena(cena);

        return daoKnjiga.dodajKnjigu(knjiga);
    }
    
    public boolean izmeniKnjigu(String ISBN, String imeKnjige, String pisac, String kategorija, Integer godinaIzdavanja, double cena) {

        Knjiga knjiga = new Knjiga();

        knjiga.setISBN(ISBN);
        knjiga.setImeKnjige(imeKnjige);
        knjiga.setPisac(pisac);
        knjiga.setKategorija(kategorija);
        knjiga.setGodinaIzdavanja(godinaIzdavanja);
        knjiga.setCena(cena);

        return daoKnjiga.izmeniKnjigu(knjiga);
    }
    
    public boolean obrisiKnjigu(String ISBN) {
        return daoKnjiga.obrisiKnjigu(daoKnjiga.pretragaPoISBN(ISBN));
    }
    
    public ArrayList<Knjiga> pretragaKnjiga(String ISBN, String imeKnjige, String pisac, String kategorija, Integer godinaIzdavanja, double cena) {
        Knjiga knjiga = new Knjiga();

        knjiga.setISBN(ISBN);
        knjiga.setImeKnjige(imeKnjige);
        knjiga.setPisac(pisac);
        knjiga.setKategorija(kategorija);
        knjiga.setGodinaIzdavanja(godinaIzdavanja);
        knjiga.setCena(cena);

        List<Knjiga> result = daoKnjiga.pretragaKnjiga(knjiga);
        
        return new ArrayList(result);
    }
    
    public ArrayList<Knjiga> izlistajTabelu() {
        return new ArrayList(daoKnjiga.izlistajTabelu());
    }
}
