package repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.*;
public class StaffRepository implements IStaffRepository{
    private final Connection connection;

    public StaffRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Staff addMember(Staff staff) {
        String queryInsert= "INSERT INTO staff (name, surname, salary, password) VALUES (?, ?, ?,?)";
        String selectQuery = "SELECT id FROM staff WHERE name = ? AND surname = ?";
        try (PreparedStatement statementInsert = connection.prepareStatement(queryInsert);
        PreparedStatement statementSelect = connection.prepareStatement(selectQuery)) {
            statementInsert.setString(1, staff.getName());
            statementInsert.setString(2, staff.getSurname());
            statementInsert.setInt(3, staff.getSalary());
            statementInsert.setString(4, staff.getPassword());
            statementInsert.execute();

            statementSelect.setString(1, staff.getName());
            statementSelect.setString(2, staff.getSurname());
            try (ResultSet rs = statementSelect.executeQuery()) {
                if (rs.next()) {
                    int id= rs.getInt("id");
                    staff.setId(id);

                }
            }
            return staff;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Staff> showAllMembers() {
        List<Staff> staffList = new ArrayList<>();
        String query = "SELECT * FROM staff ORDER BY id ASC";
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Staff staff = new Staff(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("salary")
                );
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    @Override
    public Staff findMemberByIdPassword(int id, String password) {
        String query = "SELECT * FROM staff WHERE id = ? and password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Staff(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("salary"),
                        resultSet.getString("password")
                );
            }
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

