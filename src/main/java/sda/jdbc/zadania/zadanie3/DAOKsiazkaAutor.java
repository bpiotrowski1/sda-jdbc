package sda.jdbc.zadania.zadanie3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOKsiazkaAutor {
    private String url = DBProperties.getUrl();
    private String user = DBProperties.getUser();
    private String password = DBProperties.getPassword();

    void newKsiazkaAutor(int idKsiazki, int idAutora) {
        try(Connection connection = DriverManager.getConnection(url, user, password)) {
            final String sqlInsert = "INSERT INTO autor_ksiazka VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setInt(1, idAutora);
            preparedStatement.setInt(2, idKsiazki);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
