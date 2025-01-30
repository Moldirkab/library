    package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Reader;
public class ReaderRepository implements IReaderRepository {
    private Connection connection;

    public ReaderRepository(Connection connection) {
        this.connection = connection;
    }

    public void addReader(Reader reader) {
        String sqlInsert = "INSERT INTO readers (name, surname, email, password) VALUES (?, ?, ?, ?)";
        String sqlSelect = "SELECT id FROM readers WHERE email = ?";

        try (PreparedStatement insertStatement = connection.prepareStatement(sqlInsert);
             PreparedStatement selectStatement = connection.prepareStatement(sqlSelect)) {

            insertStatement.setString(1, reader.getName());
            insertStatement.setString(2, reader.getSurname());
            insertStatement.setString(3, reader.getEmail());
            insertStatement.setString(4, reader.getPassword());
            insertStatement.executeUpdate();

            selectStatement.setString(1, reader.getEmail());
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    reader.setId(id);
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




    public Reader findReaderByIdPassword(int id, String password) {
        String sql = "SELECT * FROM readers WHERE id = ? and password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Reader(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getString("email"),
                            resultSet.getString("password")
                    );
                } else {
                    return null;
                }
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
