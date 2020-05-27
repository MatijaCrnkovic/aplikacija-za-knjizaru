/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementacija;

import dao.interfaces.DAOKorisnici;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Korisnik;

/**
 *
 * @author Matija
 */
public final class KorisnikDAOImpl implements DAOKorisnici{

    @Override
    public boolean dodajKorisnika(Korisnik korisnik) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = DBConnect.getConnection().prepareStatement(
                    "insert into korisnici (JMBG, ime, prezime, adresa, grad, brTelefona, email) values (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, korisnik.getJMBG());
            preparedStatement.setString(2, korisnik.getIme());
            preparedStatement.setString(3, korisnik.getPrezime());
            preparedStatement.setString(4, korisnik.getAdresa());
            preparedStatement.setString(5, korisnik.getGrad());
            preparedStatement.setString(6, korisnik.getBrTelefona());
            preparedStatement.setString(7, korisnik.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }
        return true;
    }

    @Override
    public boolean izmeniKorisnika(Korisnik korisnik) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = DBConnect.getConnection().prepareStatement(
                    "UPDATE korisnici SET ime = ?, prezime = ?, adresa = ?, grad = ?, brTelefona = ?, email = ? where JMBG = ?");
            preparedStatement.setString(1, korisnik.getIme());
            preparedStatement.setString(2, korisnik.getPrezime());
            preparedStatement.setString(3, korisnik.getAdresa());
            preparedStatement.setString(4, korisnik.getGrad());
            preparedStatement.setString(5, korisnik.getBrTelefona());
            preparedStatement.setString(6, korisnik.getEmail());
            preparedStatement.setString(7, korisnik.getJMBG());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }
        return true;
    }
    
    @Override
    public boolean obrisiKorisnika(Korisnik korisnik) {
        try {
            Statement statement = DBConnect.getConnection().createStatement();
            statement.executeUpdate("delete from korisnici where brojClanskeKarte = " + korisnik.getBrojClanskeKarte());
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }
        return true;
    }

    @Override
    public Korisnik pretragaID(Integer brojClanskeKarte) {
        Korisnik res = new Korisnik();
        try {
            Statement statement = DBConnect.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from korisnici where brojClanskeKarte = " + brojClanskeKarte);

            if (resultSet.next()) {
                res.setBrojClanskeKarte(resultSet.getInt("brojClanskeKarte"));
                res.setJMBG(resultSet.getString("JMBG"));
                res.setIme(resultSet.getString("ime"));
                res.setPrezime(resultSet.getString("prezime"));
                res.setAdresa(resultSet.getString("adresa"));
                res.setGrad(resultSet.getString("grad"));
                res.setBrTelefona(resultSet.getString("brTelefona"));
                res.setEmail(resultSet.getString("email"));
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
    public List<Korisnik> izlistajTabelu() {
        ArrayList<Korisnik> result = new ArrayList<>();
        try {
            Statement statement = DBConnect.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from korisnici order by brojClanskeKarte");

            while (resultSet.next()) {
                Korisnik korisnik = new Korisnik();
                korisnik.setBrojClanskeKarte(resultSet.getInt("brojClanskeKarte"));
                korisnik.setJMBG(resultSet.getString("JMBG"));
                korisnik.setIme(resultSet.getString("ime"));
                korisnik.setPrezime(resultSet.getString("prezime"));
                korisnik.setAdresa(resultSet.getString("adresa"));
                korisnik.setGrad(resultSet.getString("grad"));
                korisnik.setBrTelefona(resultSet.getString("brTelefona"));
                korisnik.setEmail(resultSet.getString("email"));
                result.add(korisnik);
            }
        } catch (SQLException ex) {
            return null;
        }
        return result;
    }
}
