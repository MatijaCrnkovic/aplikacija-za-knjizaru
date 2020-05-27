package table.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import kontroler.ZaposleniKontroler;
import model.Zaposleni;

public final class ZaposleniTableModel extends AbstractTableModel {

    private final ZaposleniKontroler zaposleniKontroler;
    private final ArrayList<Zaposleni> zaposleni;
        
    public ZaposleniTableModel(ZaposleniKontroler zaposleniKontroler) {
        super();
        this.zaposleniKontroler = zaposleniKontroler;
        zaposleni = zaposleniKontroler.pronadjiSve();
    }

    @Override
    public int getRowCount() {
        return zaposleni.size();
    }

    @Override
    public int getColumnCount() {
        return 11;
    }
    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "ID";
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
            case 8:
                return "Datum Zaposlenja";
            case 9:
                return "Pozicija";
            case 10:
                return "Plata (dinar)";
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
        if (columnIndex == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return zaposleni.get(rowIndex).getID();
            case 1:
                return zaposleni.get(rowIndex).getIme();
            case 2:
                return zaposleni.get(rowIndex).getPrezime();
            case 3:
                return zaposleni.get(rowIndex).getAdresa();
            case 4:
                return zaposleni.get(rowIndex).getGrad();
            case 5:
                return zaposleni.get(rowIndex).getJMBG();
            case 6:
                return zaposleni.get(rowIndex).getBrTelefona();
            case 7:
                return zaposleni.get(rowIndex).getEmail();
            case 8:
                return zaposleni.get(rowIndex).getDatumZaposlenja();
            case 9:
                return zaposleni.get(rowIndex).getPozicija();
            case 10:
                return zaposleni.get(rowIndex).getPlata();
            default:
                return "------";
        }

    }
}
