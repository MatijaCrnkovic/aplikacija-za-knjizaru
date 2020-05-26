package table.model;

import kontroler.KnjigaKontroler;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.Knjiga;

public final class KnjigeTableModel extends AbstractTableModel {

    private KnjigaKontroler knjigaKontroler;
    private final ArrayList<Knjiga> knjige;

    public KnjigeTableModel(KnjigaKontroler knjigaKontroler) {
        super();
        this.knjigaKontroler = knjigaKontroler;
        knjige = knjigaKontroler.izlistajTabelu();
    }

    @Override
    public int getRowCount() {
        return knjige.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "ISBN";
            case 1:
                return "Ime Knjige";
            case 2:
                return "Pisac";
            case 3:
                return "Kategorija";
            case 4:
                return "Godina Izdavanja";
            case 5:
                return "Cena";
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
                return knjige.get(rowIndex).getISBN();
            case 1:
                return knjige.get(rowIndex).getImeKnjige();
            case 2:
                return knjige.get(rowIndex).getPisac();
            case 3:
                return knjige.get(rowIndex).getKategorija();
            case 4:
                return knjige.get(rowIndex).getGodinaIzdavanja();
            case 5:
                return knjige.get(rowIndex).getCena();
            default:
                return "------";
        }
    }
}
