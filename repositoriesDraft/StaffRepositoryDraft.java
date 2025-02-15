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
        String sqlInsert = "INSERT INTO staff (name, surname, salary, login, password) VALUES (?, ?, ?, ?, ?)";
        String sqlSelect = "SELECT id, name, surname, salary, login, password FROM staff WHERE name = ? and surname=?";

        try (PreparedStatement insertStatement = connection.prepareStatement(sqlInsert);
             PreparedStatement selectStatement = connection.prepareStatement(sqlSelect)) {


            insertStatement.setString(1, staff.getName());
            insertStatement.setString(2, staff.getSurname());
            insertStatement.setInt(3, staff.getSalary());
            insertStatement.setString(5, staff.getPassword());
            insertStatement.setString(4, staff.getLogin());
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
                            .setLogin(resultSet.getString("login"))
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
    public StaffDraft findMemberByLoginPassword(String login, String password) {
        String query = "SELECT * FROM staff WHERE login = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            statement.setString(2, password);
            StaffDraft staff=null;
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                staff = (StaffDraft) new StaffDraft.StaffBuilder()
                        .setSalary(resultSet.getInt("salary"))
                        .setName(resultSet.getString("name"))
                        .setSurname(resultSet.getString("surname"))
                        .setPassword(password)
                        .setLogin(login)
                        .build();
            }
            return staff;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void deleteMemberByLogin(String login) {
        String query = "DELETE FROM staff WHERE login = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Person with login " + login + " successfully deleted!");
            } else {
                System.out.println("Person with such login not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
