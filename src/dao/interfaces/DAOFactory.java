/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

/**
 *
 * @author Matija
 */
public interface DAOFactory {
    
    public DAOKnjiga getKnjigaDAO();

    public DAOKorisnici getKorisnikDAO();

    public DAOZaposleni getZaposleniDAO();
    
}
