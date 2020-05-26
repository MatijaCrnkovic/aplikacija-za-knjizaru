/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import java.util.List;
import model.Knjiga;

/**
 *
 * @author Matija
 */
public interface DAOKnjiga {
    
    public boolean dodajKnjigu(Knjiga knjiga);

    public boolean obrisiKnjigu(Knjiga knjiga);

    public boolean izmeniKnjigu(Knjiga knjiga);
    
    public List<Knjiga> pretragaKnjiga(Knjiga knjiga);
    
    public List<Knjiga> izlistajTabelu();
    
    public Knjiga pretragaPoISBN(String ISBN);
    
    public boolean dodavanjeUKorpu(Knjiga knjiga);
    
}
