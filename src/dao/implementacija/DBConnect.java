package dao.implementacija;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import dao.interfaces.DAOFactory;
import dao.interfaces.DAOKnjiga;
import dao.interfaces.DAOKorisnici;
import dao.interfaces.DAOZaposleni;

/*
 * @author Matija Crnkovic
 * @date August 2019
 * @version 1.0
 */

public final class DBConnect implements DAOFactory {
    public static Connection getConnection() {
        return DataBaseConnection.getConnection();
    }
    
    @Override
    public DAOKnjiga getKnjigaDAO() {
        return new KnjigaDAOImpl();
    }
    
    @Override
    public DAOKorisnici getKorisnikDAO() {
        return new KorisnikDAOImpl();
    }

    @Override
    public DAOZaposleni getZaposleniDAO() {
        return new ZaposleniDAOImpl();
    }
    
    private static class DataBaseConnection {

        private static DataBaseConnection DataBaseConnection;
        private static Connection connection;

        private DataBaseConnection() throws Exception {
            String url = "jdbc:mysql://localhost:3306/knjizara";
            String name = "Matija";
            String password = "BazaPassword123.";
            try {
                connection = DriverManager.getConnection(url, name, password);
            } catch (SQLException ex) {
                throw ex;
            }
        }

        public static Connection getConnection() {
            if (DataBaseConnection == null) {
                try {
                    DataBaseConnection = new DataBaseConnection();
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                    return null;
                }
            }
            return DataBaseConnection.connection;
        }

        public static void closeConnection() {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception ex) {
                }
            }
        }
    }
}
