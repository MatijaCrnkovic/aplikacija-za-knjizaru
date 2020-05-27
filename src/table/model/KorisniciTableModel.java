/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import kontroler.KorisniciKontroler;
import model.Korisnik;

/**
 *
 * @author Matija
 */
public class KorisniciTableModel extends AbstractTableModel {
    private final KorisniciKontroler korisniciKontroler;
    private final ArrayList<Korisnik> korisnik;
        
    public KorisniciTableModel(KorisniciKontroler korisniciKontroler) {
        super();
        this.korisniciKontroler = korisniciKontroler;
        korisnik = korisniciKontroler.izlistajTabelu();
    }

    @Override
    public int getRowCount() {
        return korisnik.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }
    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Broj članske karte";
            case 1:
                return "Ime";
            case 2:
                return "Prezime";
            case 3:
                return "Adresa";
            case 4:
                return "Grad";
            case 5:
                return "JMBG";
            case 6:
                return "Broj Telefona";
            case 7:
                return "Email";
            default:
                return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return korisnik.get(rowIndex).getBrojClanskeKarte();
            case 1:
                return korisnik.get(rowIndex).getIme();
            case 2:
                return korisnik.get(rowIndex).getPrezime();
            case 3:
                return korisnik.get(rowIndex).getAdresa();
            case 4:
                return korisnik.get(rowIndex).getGrad();
            case 5:
                return korisnik.get(rowIndex).getJMBG();
            case 6:
                return korisnik.get(rowIndex).getBrTelefona();
            case 7:
                return korisnik.get(rowIndex).getEmail();
            default:
                return "------";
        }
    }
}
