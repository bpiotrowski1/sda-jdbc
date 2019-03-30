/*
 * przyklad8
 * Przyklad pokazuje prosty CRUD.Pobierany jest jeden rekord z tabeli ksiązki, Jego id przekazane w nastepnej operacji Insert.
 * Na koncu usuwamy nadmiarowe rekordy w tabeli uzytkownicy.
 * */

package sda.jdbc.przyklad8;

import sda.jdbc.przyklad8.Ksiazka;

import java.sql.*;

public class przyklad8 {
    private final static String sqlSelect = "SELECT * FROM ksiazka WHERE tytul='Java. Techniki zaawansowane.'";
    private final static String sqlInsert = "INSERT INTO zakupy" +
            "(`iduzytkownik`,`idksiazki`)" +
            " VALUES (?,?)";
    private final static String sqlDelete = "DELETE FROM uzytkownik WHERE id>10";

    public static void main(String arg[]) {

        Connection connection = null;
        Statement stmt = null;
        PreparedStatement preStmt = null;
        try {
            String url = "jdbc:mysql://localhost:3306/ksiegarnia";
            String user = "sdatest";
            String password = "Start123!";
            connection = DriverManager.getConnection(url, user, password);
            stmt = connection.createStatement();

            ResultSet resultSet = stmt.executeQuery(sqlSelect);
            Ksiazka ksiazkaDB = new Ksiazka();
            while (resultSet.next()) {
                ksiazkaDB.setId(resultSet.getInt("id"));
                ksiazkaDB.setTytul(resultSet.getString("tytul"));
            }
            System.out.println("pobrano ksiazke o tytule: Java. Techniki zaawansowane.");

            preStmt = connection.prepareStatement(sqlInsert);
            preStmt.setInt(1, 3);
            preStmt.setInt(2, ksiazkaDB.getId());
            int result = preStmt.executeUpdate();
            System.out.println("dodano rekordow: " + result);

            stmt.executeUpdate(sqlDelete);
            System.out.println("usunieto rekordy w tabeli uzystkownicy z id>10");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
