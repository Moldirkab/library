package repositoriesDraft;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelsDraft.*;
import repositoriesDraft.interfaces.IStaffRepositoryDraft;

public class StaffRepositoryDraft implements IStaffRepositoryDraft {
    private final Connection connection;

    public StaffRepositoryDraft(Connection connection) {
        this.connection = connection;
    }

    @Override
    public StaffDraft addMember(StaffDraft staff) {
        String sqlInsert = "INSERT INTO staff (name, surname, salary, password) VALUES (?, ?, ?, ?)";
        String sqlSelect = "SELECT id, name, surname, salary, password FROM staff WHERE name = ? and surname=?";  // Select all needed columns

        try (PreparedStatement insertStatement = connection.prepareStatement(sqlInsert);
             PreparedStatement selectStatement = connection.prepareStatement(sqlSelect)) {


            insertStatement.setString(1, staff.getName());
            insertStatement.setString(2, staff.getSurname());
            insertStatement.setInt(3, staff.getSalary());
            insertStatement.setString(4, staff.getPassword());
            insertStatement.executeUpdate();

            selectStatement.setString(1, staff.getName());
            selectStatement.setString(2, staff.getSurname());
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {

                    staff = (StaffDraft) new StaffDraft.StaffBuilder()
                            .setSalary(resultSet.getInt("salary"))
                            .setId(resultSet.getInt("id"))
                            .setName(resultSet.getString("name"))
                            .setSurname(resultSet.getString("surname"))
                            .setPassword(resultSet.getString("password"))
                            .build();
                    return staff;
                } else {
                    System.err.println("Error: Unable to retrieve the generated ID.");
                }
                return null;
            }

        } catch (SQLException e) {
            System.out.println("SQL error occurred while adding reader: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    return null;}




    @Override
    public List<StaffDraft> showAllMembers() {
        List<StaffDraft> staffList = new ArrayList<>();
        String query = "SELECT * FROM staff ORDER BY id ASC";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                StaffDraft staff = (StaffDraft) new StaffDraft.StaffBuilder()
                        .setSalary(resultSet.getInt("salary"))
                        .setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("name"))
                        .setSurname(resultSet.getString("surname"))
                        .setPassword(resultSet.getString("password"))
                        .build();
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    @Override
    public StaffDraft findMemberByIdPassword(int id, String password) {
        String query = "SELECT * FROM staff WHERE id = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2, password);
            StaffDraft staff=null;
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                staff = (StaffDraft) new StaffDraft.StaffBuilder()
                        .setSalary(resultSet.getInt("salary"))
                        .setId(id)
                        .setName(resultSet.getString("name"))
                        .setSurname(resultSet.getString("surname"))
                        .setPassword(password)
                        .build();
            }
            return staff;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void deleteMemberById(int id) {
        String query = "DELETE FROM staff WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Person with ID " + id + " successfully deleted!");
            } else {
                System.out.println("Person with such ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
