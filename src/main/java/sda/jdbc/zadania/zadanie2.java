package sda.jdbc.zadania;

import java.sql.*;

public class zadanie2 {
    private static String url = "jdbc:mysql://localhost:3306/ksiegarnia";
    private static String user = "sdatest";
    private static String password = "Start123!";

    public static void main(String[] args) {
        pobierzUzytkownikow();
        dodajUzytkownika();
        pobierzUzytkownikow();
        updateUzytkownika();
        pobierzUzytkownikow();
    }

    private static void pobierzUzytkownikow() {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            final String sqlSelect = "SELECT * FROM uzytkownik";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                String imie = resultSet.getString("imie");
                String nazwisko = resultSet.getString("nazwisko");
                System.out.println(imie + " " + nazwisko);
            }
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void dodajUzytkownika() {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            final String sqlInsert = "INSERT INTO uzytkownik(imie, nazwisko) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setString(1, "Janek");
            preparedStatement.setString(2, "Kowalski");
            int dodanychUzytkownikow = preparedStatement.executeUpdate();
            System.out.println("Dodanych uzytkownikow: " + dodanychUzytkownikow);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int ostatniUzytkownik() {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            final String sqlLastUser = "SELECT max(id) FROM uzytkownik";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlLastUser);
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static void updateUzytkownika() {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            final String sqlUpdate = "UPDATE uzytkownik SET nazwisko=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, "NoweNazwisko");
            preparedStatement.setInt(2, ostatniUzytkownik());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
