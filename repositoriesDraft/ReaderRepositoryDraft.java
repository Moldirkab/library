package repositoriesDraft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelsDraft.ReaderDraft;
import repositoriesDraft.interfaces.IReaderRepositoryDraft;


public class ReaderRepositoryDraft implements IReaderRepositoryDraft {
    private Connection connection;

    public ReaderRepositoryDraft(Connection connection) {
        this.connection = connection;
    }

    public void addReader(ReaderDraft reader) {
        String sqlInsert = "INSERT INTO readers (name, surname, email, login, password) VALUES (?, ?, ?, ?, ?)";
        String sqlSelect = "SELECT id, name, surname, email, login, password FROM readers WHERE email = ?";

        try (PreparedStatement insertStatement = connection.prepareStatement(sqlInsert);
             PreparedStatement selectStatement = connection.prepareStatement(sqlSelect)) {


            insertStatement.setString(1, reader.getName());
            insertStatement.setString(2, reader.getSurname());
            insertStatement.setString(3, reader.getEmail());
            insertStatement.setString(5, reader.getPassword());
            insertStatement.setString(4, reader.getLogin());
            insertStatement.executeUpdate();

            selectStatement.setString(1, reader.getEmail());
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {

                    reader = (ReaderDraft) new ReaderDraft.ReaderBuilder()
                            .setEmail(resultSet.getString("email"))
                            .setId(resultSet.getInt("id"))
                            .setName(resultSet.getString("name"))
                            .setSurname(resultSet.getString("surname"))
                            .setPassword(resultSet.getString("password"))
                            .setLogin(resultSet.getString("login"))
                            .build();
                } else {

                    System.err.println("Error: Unable to retrieve the generated login.");
                }
            }

        } catch (SQLException e) {
            System.out.println("SQL error occurred while adding reader: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }





    public ReaderDraft findReaderByLoginPassword(String login, String password) {
        String sql = "SELECT id, name, surname, email, password, login FROM readers WHERE login = ? and password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                ReaderDraft reader = null;
                while(resultSet.next()) {
                     reader = (ReaderDraft) new ReaderDraft.ReaderBuilder()
                             .setEmail(resultSet.getString("email"))
                            .setId(resultSet.getInt("id"))
                            .setName(resultSet.getString("name"))
                            .setSurname(resultSet.getString("surname"))
                             .setLogin(resultSet.getString("login"))
                            .setPassword(resultSet.getString("password"))
                            .build();
                } return reader;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        return null;
    }
}
