/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementacija;

import dao.interfaces.DAOZaposleni;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Zaposleni;

/**
 *
 * @author Matija
 */
public class ZaposleniDAOImpl implements DAOZaposleni {

    @Override
    public boolean dodajZaposlenog(Zaposleni zaposleni) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = DBConnect.getConnection().prepareStatement(
                    "INSERT INTO zaposleni (JMBG, ime, prezime, adresa, grad, "
                            + "brTelefona, email, datumZaposlenja, pozicija, plata, "
                            + "username, password) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, zaposleni.getJMBG());
            preparedStatement.setString(2, zaposleni.getIme());
            preparedStatement.setString(3, zaposleni.getPrezime());
            preparedStatement.setString(4, zaposleni.getAdresa());
            preparedStatement.setString(5, zaposleni.getGrad());
            preparedStatement.setString(6, zaposleni.getBrTelefona());
            preparedStatement.setString(7, zaposleni.getEmail());
            preparedStatement.setDate(8, (Date) zaposleni.getDatumZaposlenja());
            preparedStatement.setString(9, zaposleni.getPozicija());
            preparedStatement.setInt(10, zaposleni.getPlata());
            preparedStatement.setString(11, zaposleni.getUsername());
            preparedStatement.setString(12, zaposleni.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }
        return true;
    }

    @Override
    public boolean obrisiZaposlenog(Zaposleni zaposleni) {
        try {
            Statement statement = DBConnect.getConnection().createStatement();
            statement.executeUpdate("delete from zaposleni where IDzaposleni = " 
                    + zaposleni.getID());
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }
        return true;
    }

    @Override
    public boolean izmeniZaposlenog(Zaposleni zaposleni) {
       try {
            PreparedStatement preparedStatement;
            preparedStatement = DBConnect.getConnection().prepareStatement(
                    "update zaposleni set ime = ?, prezime = ?, adresa = ?, grad = ?, "
                            + "brTelefona = ?, email = ?, JMBG = ?, pozicija = ?, plata = ? "
                            + "where IDzaposleni = ?");
            preparedStatement.setString(1, zaposleni.getIme());
            preparedStatement.setString(2, zaposleni.getPrezime());
            preparedStatement.setString(3, zaposleni.getAdresa());
            preparedStatement.setString(4, zaposleni.getGrad());
            preparedStatement.setString(5, zaposleni.getBrTelefona());
            preparedStatement.setString(6, zaposleni.getEmail());
            preparedStatement.setString(7, zaposleni.getJMBG());
            preparedStatement.setString(8, zaposleni.getPozicija());
            preparedStatement.setInt(9, zaposleni.getPlata());
            preparedStatement.setInt(10, zaposleni.getID());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }
        return true;
    }
    
    @Override
    public Zaposleni pretragaID(Integer ID) {
        Zaposleni res = null;
        try {

            Statement statement = DBConnect.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from zaposleni where IDzaposleni = " + ID);

            if (resultSet.next()) {
                res = new Zaposleni();
                res.setID(resultSet.getInt("IDzaposleni"));
                res.setJMBG(resultSet.getString("JMBG"));
                res.setIme(resultSet.getString("ime"));
                res.setPrezime(resultSet.getString("prezime"));
                res.setAdresa(resultSet.getString("adresa"));
                res.setGrad(resultSet.getString("grad"));
                res.setBrTelefona(resultSet.getString("brTelefona"));
                res.setEmail(resultSet.getString("email"));
                res.setDatumZaposlenja(resultSet.getDate("datumZaposlenja"));
                res.setPozicija(resultSet.getString("pozicija"));
                res.setPlata(resultSet.getInt("plata"));
                res.setUsername(resultSet.getString("username"));
                res.setPassword(resultSet.getString("password"));
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
    public List<Zaposleni> pronadjiSve() {
        ArrayList<Zaposleni> result = new ArrayList<>();
        try {
            Statement statement = DBConnect.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from zaposleni order by IDzaposleni");

            while (resultSet.next()) {
                Zaposleni zaposleni = new Zaposleni();
                zaposleni.setID(resultSet.getInt("IDzaposleni"));
                zaposleni.setJMBG(resultSet.getString("JMBG"));
                zaposleni.setIme(resultSet.getString("ime"));
                zaposleni.setPrezime(resultSet.getString("prezime"));
                zaposleni.setAdresa(resultSet.getString("adresa"));
                zaposleni.setGrad(resultSet.getString("grad"));
                zaposleni.setBrTelefona(resultSet.getString("brTelefona"));
                zaposleni.setEmail(resultSet.getString("email"));
                zaposleni.setDatumZaposlenja(resultSet.getDate("datumZaposlenja"));
                zaposleni.setPozicija(resultSet.getString("pozicija"));
                zaposleni.setPlata(resultSet.getInt("plata"));
                zaposleni.setUsername(resultSet.getString("username"));
                zaposleni.setPassword(resultSet.getString("password"));
                result.add(zaposleni);
            }
        } catch (SQLException ex) {
            return null;
        }
        return result;
    }

    @Override
    public List<Zaposleni> proveriZaposlenog(Zaposleni filterZaposleni) {
        ArrayList<Zaposleni> result = new ArrayList();
        PreparedStatement preparedStatement;
        try {
        preparedStatement = DBConnect.getConnection().prepareStatement(
                    "select * from zaposleni "
                    + "where (IDzaposleni = ? or ? = -2147483648)"
                    + "and (JMBG = ? or ? is null)"
                    + "and (ime = ? or ? is null) "
                    + "and (prezime = ? or ? is null)"
                    + "and (adresa = ? or ? is null)"
                    + "and (grad = ? or ? is null)"
                    + "and (brTelefona = ? or ? is null)"
                    + "and (email = ? or ? is null)"
                    + "and (datumZaposlenja = ? or ? = '1900-01-01')"
                    + "and (pozicija = ? or ? is null)"
                    + "and (plata = ? or ? = -2147483648)"
                    + "and (username = ? or ? is null)"
                    + "and (password = ? or ? is null)"
                    + "order by ime");

            if (filterZaposleni.getID()== null) {
                preparedStatement.setInt(1, Integer.MIN_VALUE);
                preparedStatement.setInt(2, Integer.MIN_VALUE);
            } else {
                preparedStatement.setInt(1, filterZaposleni.getID());
                preparedStatement.setInt(2, filterZaposleni.getID());
            }
            preparedStatement.setString(3, filterZaposleni.getJMBG());
            preparedStatement.setString(4, filterZaposleni.getJMBG());
            preparedStatement.setString(5, filterZaposleni.getIme());
            preparedStatement.setString(6, filterZaposleni.getIme());
            preparedStatement.setString(7, filterZaposleni.getPrezime());
            preparedStatement.setString(8, filterZaposleni.getPrezime());
            preparedStatement.setString(9, filterZaposleni.getAdresa());
            preparedStatement.setString(10, filterZaposleni.getAdresa());
            preparedStatement.setString(11, filterZaposleni.getGrad());
            preparedStatement.setString(12, filterZaposleni.getGrad());
            preparedStatement.setString(13, filterZaposleni.getBrTelefona());
            preparedStatement.setString(14, filterZaposleni.getBrTelefona());
            preparedStatement.setString(15, filterZaposleni.getEmail());
            preparedStatement.setString(16, filterZaposleni.getEmail());
            if (filterZaposleni.getDatumZaposlenja() == null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                java.util.Date parsed = format.parse("19000101");
                preparedStatement.setDate(17, new Date(parsed.getTime()));
                preparedStatement.setDate(18, new Date(parsed.getTime()));
            } else {
                preparedStatement.setDate(17, (Date) filterZaposleni.getDatumZaposlenja());
                preparedStatement.setDate(28, (Date) filterZaposleni.getDatumZaposlenja());
            }
            preparedStatement.setString(19, filterZaposleni.getPozicija());
            preparedStatement.setString(20, filterZaposleni.getPozicija());
            if (filterZaposleni.getPlata() == null) {
                preparedStatement.setInt(21, Integer.MIN_VALUE);
                preparedStatement.setInt(22, Integer.MIN_VALUE);
            } else {
                preparedStatement.setInt(21, filterZaposleni.getPlata());
                preparedStatement.setInt(22, filterZaposleni.getPlata());
            }
            preparedStatement.setString(23, filterZaposleni.getUsername());
            preparedStatement.setString(24, filterZaposleni.getUsername());
            preparedStatement.setString(25, filterZaposleni.getPassword());
            preparedStatement.setString(26, filterZaposleni.getPassword());
            
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Zaposleni zaposleni = new Zaposleni();
                zaposleni.setID(resultSet.getInt("IDzaposleni"));
                zaposleni.setJMBG(resultSet.getString("JMBG"));
                zaposleni.setIme(resultSet.getString("ime"));
                zaposleni.setPrezime(resultSet.getString("prezime"));
                zaposleni.setAdresa(resultSet.getString("adresa"));
                zaposleni.setGrad(resultSet.getString("grad"));
                zaposleni.setBrTelefona(resultSet.getString("brTelefona"));
                zaposleni.setEmail(resultSet.getString("email"));
                zaposleni.setDatumZaposlenja(resultSet.getDate("datumZaposlenja"));
                zaposleni.setPozicija(resultSet.getString("pozicija"));
                zaposleni.setPlata(resultSet.getInt("plata"));
                zaposleni.setUsername(resultSet.getString("username"));
                zaposleni.setPassword(resultSet.getString("password"));
                result.add(zaposleni);
            }
        } catch (SQLException | ParseException ex) {
            return null;
        }
        return result;
    }
    
    @Override
    public boolean promeniPassword(Zaposleni zaposleni) {
        try {
            PreparedStatement preparedStatement = DBConnect.getConnection().prepareStatement(
                    "update zaposleni set password = ? where JMBG = ?");
            
            preparedStatement.setString(1, zaposleni.getPassword());
            preparedStatement.setString(2, zaposleni.getJMBG());
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }
}
