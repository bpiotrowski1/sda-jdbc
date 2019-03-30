/*
 * przyklad10
 * Przyklad pokazuje zastosowanie try-with-resources, automatycznie zamykane są Connection i Statement.
 * */

package sda.jdbc;

import sda.jdbc.przyklad9.Ksiazka;

import java.sql.*;
import java.util.ArrayList;

public class przyklad10 {
    private final static String sqlSelect = "SELECT * FROM ksiazka ";

    public static void main(String arg[]) {
        ArrayList<Ksiazka> ksiazki = new ArrayList<Ksiazka>();

        String url = "jdbc:mysql://localhost:3306/ksiegarnia";
        String user = "sdatest";
        String password = "Start123!";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();) {

            ResultSet resultSet = stmt.executeQuery(sqlSelect);
            while (resultSet.next()) {
                Ksiazka ksiazkaDB = new Ksiazka();
                ksiazkaDB.setId(resultSet.getInt("id"));
                ksiazkaDB.setTytul(resultSet.getString("tytul"));
                ksiazki.add(ksiazkaDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Dane o pobranych ksiazkach. Pobrano " + ksiazki.size() + " ksiazek.");
        System.out.println("Lista ksiazek w bazie danych: ");
        for (Ksiazka ksiazkaLista : ksiazki) {
            System.out.println(ksiazkaLista.getId() + " " + ksiazkaLista.getTytul());
        }
    }
}
