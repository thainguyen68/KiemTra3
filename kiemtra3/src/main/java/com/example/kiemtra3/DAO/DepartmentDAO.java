package com.example.kiemtra3.DAO;

import com.example.kiemtra3.DAO.connection.MyConnection;
import com.example.kiemtra3.model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    private final Connection connection;
    private final String SELECT_ALL = "select * from department;";
    private final String SELECT_BY_ID = "select * from department where id = ?;";
    private final String INSERT_INTO = "insert into department(name) value (?);";
    private final String UPDATE_BY_ID = "update department set name = ? where id = ?;";
    private final String DELETE_BY_ID = "delete from department where id = ?";

    public DepartmentDAO() {
        connection = MyConnection.getConnection();
    }

    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Department department = new Department(id, name);
                departments.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    public Department findById(int id) {
        Department department = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                department = new Department(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }

    public void addDepartment(Department department) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO)) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDepartment(Department department) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID)) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.setInt(2, department.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
