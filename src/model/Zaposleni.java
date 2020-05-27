/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Matija
 */
public class Zaposleni extends Osoba {
    private Date datumZaposlenja;
    private String pozicija;
    private Integer plata;
    private String username;
    private String password;
    
    public Zaposleni() {
        
    }
    
    public Zaposleni(Integer ID, String JMBG, String ime, String prezime, 
            String adresa, String grad, String brTelefona, String email){
        super(ID, JMBG, ime, prezime, adresa, grad, brTelefona, email);
        this.datumZaposlenja = datumZaposlenja;
        this.pozicija = pozicija;
        this.plata = plata;
        this.username = username;
        this.password = password;
    }
    
    public Date getDatumZaposlenja() {
        return datumZaposlenja;
    }
    
    public void setDatumZaposlenja(Date datumZaposlenja) {
        this.datumZaposlenja = datumZaposlenja;
    }

    public String getPozicija() {
        return pozicija;
    }

    public void setPozicija(String pozicija) {
        this.pozicija = pozicija;
    }

    public Integer getPlata() {
        return plata;
    }

    public void setPlata(Integer plata) {
        this.plata = plata;
    }
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
