/*
 * przyklad6
 * Przyklad pokazuje jak podlaczyc sie do bazy danych, wykozystac kilkukrotnie
 * PreparedStatement do dodania kilku rekordow do tabeli.
 * */

package sda.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class przyklad6 {
    private static String sqlInsert = "INSERT INTO uzytkownik" +
            "(`imie`,`nazwisko`)" +
            "VALUES (?,?)";

    public static void main(String arg[]) {
        String[] nazwiska = {"Nowak", "Polak", "Dudek", "Wielki", "Wilki"};
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ksiegarnia";
            String user = "sdatest";
            String password = "Start123!";
            connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preStmt = connection.prepareStatement(sqlInsert);
            for (int i = 0; i < 5; i++) {
                preStmt.setString(1, "Jan");
                preStmt.setString(2, nazwiska[i]);
                preStmt.execute();
            }

        } catch (
                ClassNotFoundException e) {
            e.printStackTrace();
        } catch (
                SQLException e) {
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
