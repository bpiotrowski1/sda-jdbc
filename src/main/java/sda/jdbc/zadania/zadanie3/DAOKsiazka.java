package sda.jdbc.zadania.zadanie3;

import java.sql.*;

class DAOKsiazka {
    private String url = DBProperties.getUrl();
    private String user = DBProperties.getUser();
    private String password = DBProperties.getPassword();
    private final String sqlSelectById = "SELECT * FROM ksiazka WHERE id=?";
    private final String sqlInsert = "INSERT INTO ksiazka(tytul) VALUES (?)";
    private final String sqlUpdate = "UPDATE ksiazka SET tytul=? WHERE id=?";
    private final String sqlDelete = "DELETE FROM ksiazka WHERE id=?";
    private final String sqlSelectAll = "SELECT * FROM ksiazka";
    private final String sqlSelectLastId = "SELECT max(id) FROM ksiazka";

    Ksiazka findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectById)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Ksiazka(resultSet.getInt(1), resultSet.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    int newBooks(String tytul) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, tytul);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            return generatedKeys.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    void updateById(int id, String tytul) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
            preparedStatement.setString(1, tytul);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void deleteById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void getAllBooks() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlSelectAll);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + ". " + resultSet.getString(2));
            }
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    int getLastId() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(sqlSelectLastId);
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
