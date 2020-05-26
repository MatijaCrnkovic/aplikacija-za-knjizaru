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
public class Osoba {
    public Integer ID;
    public String JMBG;
    public String ime;
    public String prezime;
    public String adresa;
    public String grad;
    public String brTelefona;
    public String email;
    
    public Osoba() {
        
    }
    
    public Osoba(Integer ID, String JMBG, String ime, String prezime, 
            String adresa, String grad, String brTelefona, String email) {
        this.ID = ID;
        this.JMBG = JMBG;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.grad = grad;
        this.brTelefona = brTelefona;
        this.email = email;
   }
    
    public Integer getID() {
        return ID;
    }
    
    public void setID(Integer ID) {
        this.ID = ID;
    }
   
    public String getJMBG() {
        return JMBG;
    }
    
    public void setJMBG(String JMBG) {
        this.JMBG = JMBG;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
 
    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getBrTelefona() {
        return brTelefona;
    }
    
    public void setBrTelefona(String brTelefona) {
        this.brTelefona = brTelefona;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}
