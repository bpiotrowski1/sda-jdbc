/*
* przyklad1
* Przyklad pokazuje jak podlaczyc sie do bazy danych, wykonac zapytanie SELECT
* wykozystujac interface Statement i wyswietla wynik na konsoli.
* */

package sda.jdbc;

import java.sql.*;

public class przyklad1 {
    public static void main(String arg[]) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ksiegarnia";
            String user = "sdatest";
            String password = "Start123!";
            connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT count(*) FROM autor;");
            while (resultSet.next()) {
                Integer countRecord = resultSet.getInt(1);
                System.out.println("liczba rekordow w tabeli autor: "+countRecord);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
