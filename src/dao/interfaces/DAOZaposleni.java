/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import java.util.List;
import model.Zaposleni;

/**
 *
 * @author Matija
 */
public interface DAOZaposleni {
    
    public boolean dodajZaposlenog(Zaposleni zaposleni);

    public boolean obrisiZaposlenog(Zaposleni zaposleni);

    public boolean izmeniZaposlenog(Zaposleni zaposleni);
    
    public Zaposleni pretragaID(Integer ID);

    public List<Zaposleni> pronadjiSve();
            
    public List<Zaposleni> proveriZaposlenog(Zaposleni zaposleni);
    
    public boolean promeniPassword(Zaposleni zaposleni);
}
