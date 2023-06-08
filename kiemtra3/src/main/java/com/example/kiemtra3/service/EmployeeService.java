package com.example.kiemtra3.service;

import com.example.kiemtra3.DAO.EmployeeDAO;
import com.example.kiemtra3.model.Department;
import com.example.kiemtra3.model.Employee;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class EmployeeService {
    private static EmployeeService employeeService;
    private final  DepartmentService departmentService = DepartmentService.getInstance();
    private final EmployeeDAO employeeDAO;

    public EmployeeService() {
        employeeDAO = new EmployeeDAO();
    }

    public static EmployeeService getEmployeeService() {
        if (employeeService == null) {
            employeeService = new EmployeeService();
        }
        return employeeService;
    }

    public List<Employee> findAll(){
        return employeeDAO.findAll();
    }

    public void save(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        double salary = Double.parseDouble(request.getParameter("salary"));
        int departmentID =Integer.parseInt(request.getParameter("department"));
        Department department = departmentService.getById(departmentID);
        if (id != null) {
            int idUpdate = Integer.parseInt(id);
            employeeDAO.updateEmployee(new Employee(idUpdate, name, email, address,phoneNumber,salary,department));
        } else {
            employeeDAO.addEmployee(new Employee(name, email, address,phoneNumber,salary,department));
        }
    }

    public Employee getById(int id) {
        return employeeDAO.findById(id);
    }

    public void deleteById(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        employeeDAO.deleteById(id);
    }

    public List<Employee> searchByName(HttpServletRequest request) {
        String search = request.getParameter("search");
        return employeeDAO.searchByName(search);
    }

    public boolean checkById(int id) {
        Employee employee = employeeDAO.findById(id);
        return employee != null;
    }


}
