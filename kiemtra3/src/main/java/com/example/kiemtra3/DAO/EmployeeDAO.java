package com.example.kiemtra3.DAO;

import com.example.kiemtra3.DAO.connection.MyConnection;
import com.example.kiemtra3.model.Department;
import com.example.kiemtra3.model.Employee;
import com.example.kiemtra3.service.DepartmentService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private final Connection connection;
    private static EmployeeDAO employeeDAO;
    private final DepartmentService departmentService = DepartmentService.getInstance();
    private final String SELECT_ALL = "select * from employee;";
    private final String SELECT_BY_ID = "select * from employee where id = ?;";
    private final String INSERT_INTO = "insert into employee(name,email,address,phone_number,salary,department_id) value (?,?,?,?,?,?);";
    private final String UPDATE_BY_ID = "update employee set name = ?, email = ?,  address = ?,  phone_number = ?, salary = ?, department_id = ? where id = ?;";
    private final String DELETE_BY_ID = "delete from employee where id = ?;";
    private final String SEARCH_BY_NAME = "select * from employee where name like ?;";

    public EmployeeDAO() {
        connection = MyConnection.getConnection();
    }

    public static EmployeeDAO getInstance() {
        if (employeeDAO == null) {
            employeeDAO = new EmployeeDAO();
        }
        return employeeDAO;
    }


    public List<Employee> findAll() {
        List<Employee> employeeList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                String address = resultSet.getString(4);
                String phoneNumber = resultSet.getString(5);
                double salary = resultSet.getDouble(6);
                int department_id = resultSet.getInt(7);
                Department department = departmentService.getById(department_id);
                Employee employee = new Employee(id, name, email, address, phoneNumber,salary,department);
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public Employee findById(int id) {
        Employee employee = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                String address = resultSet.getString(4);
                String phoneNumber = resultSet.getString(5);
                double salary = resultSet.getDouble(6);
                int department_id = resultSet.getInt(7);
                Department department = departmentService.getById(department_id);
                employee = new Employee(id, name, email, address, phoneNumber,salary,department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public void addEmployee(Employee employee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setString(3, employee.getAddress());
            preparedStatement.setString(4, employee.getPhoneNumber());
            preparedStatement.setDouble(5, employee.getSalary());
            preparedStatement.setInt(6, employee.getDepartment().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee employee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setString(3, employee.getAddress());
            preparedStatement.setString(4, employee.getPhoneNumber());
            preparedStatement.setDouble(5, employee.getSalary());
            preparedStatement.setInt(6, employee.getDepartment().getId());
            preparedStatement.setInt(7, employee.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Employee> searchByName(String name) {
        List<Employee> employeeList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_NAME)) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String names = resultSet.getString(2);
                String email = resultSet.getString(3);
                String address = resultSet.getString(4);
                String phoneNumber = resultSet.getString(5);
                double salary = resultSet.getDouble(6);
                int department_id = resultSet.getInt(7);
                Department department = departmentService.getById(department_id);
                Employee employee = new Employee(id, names, email, address, phoneNumber,salary,department);
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

}
