package sda.jdbc.zadania.zadanie3;

import java.sql.*;

class DataAccessObject {
    private String url = DBProperties.getUrl();
    private String user = DBProperties.getUser();
    private String password = DBProperties.getPassword();

    Ksiazka findById(int id) {
        try(Connection connection = DriverManager.getConnection(url, user, password)) {
            final String sqlSelectById = "SELECT * FROM ksiazka WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Ksiazka(resultSet.getInt(1), resultSet.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    void newBooks(String tytul) {
        try(Connection connection = DriverManager.getConnection(url, user, password)) {
            final String sqlInsert = "INSERT INTO ksiazka(tytul) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setString(1, tytul);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void updateById(int id, String tytul) {
        try(Connection connection = DriverManager.getConnection(url, user, password)) {
            final String sqlUpdate = "UPDATE ksiazka SET tytul=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, tytul);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void deleteById(int id) {
        try(Connection connection = DriverManager.getConnection(url, user, password)) {
            final String sqlDelete = "DELETE FROM ksiazka WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void getAllBooks() {
        try(Connection connection = DriverManager.getConnection(url, user, password)) {
            final String sqlSelectAll = "SELECT * FROM ksiazka";
            Statement statement = connection.createStatement();
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
        try(Connection connection = DriverManager.getConnection(url, user, password)) {
            final String sqlSelectLastId = "SELECT max(id) FROM ksiazka";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelectLastId);
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
