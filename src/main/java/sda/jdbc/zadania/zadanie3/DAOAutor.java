package sda.jdbc.zadania.zadanie3;

import java.sql.*;

class DAOAutor {
    private String url = DBProperties.getUrl();
    private String user = DBProperties.getUser();
    private String password = DBProperties.getPassword();
    private final String sqlInsert = "INSERT INTO autor (imie, nazwisko) VALUES (?, ?)";
    private final String sqlSelect = "SELECT tytul FROM ksiazka k, autor_ksiazka a WHERE k.id = a.ksiazka AND a.idautor=?";
    private final String sqlSelectTytulAutora = "SELECT tytul FROM ksiazka k, autor_ksiazka a WHERE k.id = a.ksiazka AND a.idautor=? AND k.tytul=?";

    int newAuthor(String imie, String nazwisko) {
        try(Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, imie);
            preparedStatement.setString(2, nazwisko);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            return generatedKeys.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    void getBooksById(int id) {
        try(Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    boolean getBooksByTitle(int idAutora, String tytul) {
        try(Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectTytulAutora)) {
            preparedStatement.setInt(1, idAutora);
            preparedStatement.setString(2, tytul);
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
