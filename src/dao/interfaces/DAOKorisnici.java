/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import java.util.List;
import model.Korisnik;

/**
 *
 * @author Matija
 */
public interface DAOKorisnici {
    
    public boolean dodajKorisnika(Korisnik korisnik);

    public boolean obrisiKorisnika(Korisnik korisnik);

    public boolean izmeniKorisnika(Korisnik korisnik);
    
    public List<Korisnik> izlistajTabelu();
    
    public Korisnik pretragaID(Integer brojClanskeKarte);
    
}
