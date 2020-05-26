/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementacija;

import dao.interfaces.DAOKnjiga;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Knjiga;

/**
 *
 * @author Matija
 */
public final class KnjigaDAOImpl implements DAOKnjiga {
    
    @Override
    public boolean dodajKnjigu(Knjiga knjiga) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = DBConnect.getConnection().prepareStatement(
                    "INSERT INTO knjiga (ISBN, imeKnjige, pisac, kategorija, godinaIzdavanja, cena) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, knjiga.getISBN());
            preparedStatement.setString(2, knjiga.getImeKnjige());
            preparedStatement.setString(3, knjiga.getPisac());
            preparedStatement.setString(4, knjiga.getKategorija());
            preparedStatement.setInt(5, knjiga.getGodinaIzdavanja());
            preparedStatement.setDouble(6, knjiga.getCena());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }
        return true;
    }

    @Override
    public boolean obrisiKnjigu(Knjiga knjiga) {
        try {
            Statement statement = DBConnect.getConnection().createStatement();
            statement.executeUpdate("delete from knjiga where ISBN = " + knjiga.getISBN());
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }
        return true;
    }

    @Override
    public boolean izmeniKnjigu(Knjiga knjiga) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = DBConnect.getConnection().prepareStatement(
                    "UPDATE knjiga SET imeKnjige = ?, pisac = ?, kategorija = ?, godinaIzdavanja = ?, cena = ? where ISBN = ?");
            preparedStatement.setString(1, knjiga.getImeKnjige());
            preparedStatement.setString(2, knjiga.getPisac());
            preparedStatement.setString(3, knjiga.getKategorija());
            preparedStatement.setInt(4, knjiga.getGodinaIzdavanja());
            preparedStatement.setDouble(5, knjiga.getCena());
            preparedStatement.setString(6, knjiga.getISBN());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }
        return true;
    }

    @Override
    public Knjiga pretragaPoISBN(String ISBN) {
        Knjiga res = new Knjiga();
        try {

            Statement statement = DBConnect.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from knjiga where ISBN = " + ISBN);

            if (resultSet.next()) {
                res.setISBN(resultSet.getString("ISBN"));
                res.setImeKnjige(resultSet.getString("imeKnjige"));
                res.setPisac(resultSet.getString("pisac"));
                res.setKategorija(resultSet.getString("kategorija"));
                res.setGodinaIzdavanja(resultSet.getInt("godinaIzdavanja"));
                res.setCena(resultSet.getDouble("cena"));
            }
            if (resultSet.next()) {
                throw new SQLException();
            }
        } catch (SQLException ex) {
            return null;
        }
        return res;
    }

    @Override
    public boolean dodavanjeUKorpu(Knjiga knjiga) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Knjiga> izlistajTabelu() {
        ArrayList<Knjiga> result = new ArrayList<>();
        try {
            Statement statement = DBConnect.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from knjiga order by imeKnjige");

            while (resultSet.next()) {
                Knjiga knjiga = new Knjiga();
                knjiga.setISBN(resultSet.getString("ISBN"));
                knjiga.setImeKnjige(resultSet.getString("imeKnjige"));
                knjiga.setPisac(resultSet.getString("pisac"));
                knjiga.setKategorija(resultSet.getString("kategorija"));
                knjiga.setGodinaIzdavanja(resultSet.getInt("godinaIzdavanja"));
                knjiga.setCena(resultSet.getDouble("cena"));
                result.add(knjiga);
            }
        } catch (SQLException ex) {
            return null;
        }
        return result;
    }

    @Override
    public List<Knjiga> pretragaKnjiga(Knjiga knjiga) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
