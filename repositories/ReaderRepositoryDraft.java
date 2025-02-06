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
        String sqlInsert = "INSERT INTO readers (name, surname, email, password) VALUES (?, ?, ?, ?)";
        String sqlSelect = "SELECT id, name, surname, email, password FROM readers WHERE email = ?";  // Select all needed columns

        try (PreparedStatement insertStatement = connection.prepareStatement(sqlInsert);
             PreparedStatement selectStatement = connection.prepareStatement(sqlSelect)) {


            insertStatement.setString(1, reader.getName());
            insertStatement.setString(2, reader.getSurname());
            insertStatement.setString(3, reader.getEmail());
            insertStatement.setString(4, reader.getPassword());
            insertStatement.executeUpdate();  // Execute the insert

            selectStatement.setString(1, reader.getEmail());
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {

                    reader = (ReaderDraft) new ReaderDraft.ReaderBuilder()
                            .setEmail(resultSet.getString("email"))
                            .setId(resultSet.getInt("id"))
                            .setName(resultSet.getString("name"))
                            .setSurname(resultSet.getString("surname"))
                            .setPassword(resultSet.getString("password"))
                            .build();
                } else {

                    System.err.println("Error: Unable to retrieve the generated ID.");
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





    public ReaderDraft findReaderByIdPassword(int id, String password) {
        String sql = "SELECT id, name, surname, email, password FROM readers WHERE id = ? and password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                ReaderDraft reader = null;
                while(resultSet.next()) {
                     reader = (ReaderDraft) new ReaderDraft.ReaderBuilder()
                             .setEmail(resultSet.getString("email"))
                            .setId(resultSet.getInt("id"))
                            .setName(resultSet.getString("name"))
                            .setSurname(resultSet.getString("surname"))
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
