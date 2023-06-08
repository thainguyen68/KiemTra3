package com.example.kiemtra3.service;

import com.example.kiemtra3.DAO.DepartmentDAO;
import com.example.kiemtra3.model.Department;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DepartmentService {
    private final DepartmentDAO departmentDAO;

    private static DepartmentService departmentService;

    public DepartmentService() {
        departmentDAO = new DepartmentDAO();
    }

    public static DepartmentService getInstance() {
        if (departmentService == null) {
            departmentService = new DepartmentService();
        }
        return departmentService;
    }

    public List<Department> findAll() {
        return departmentDAO.findAll();
    }

    public Department getById(int id) {
        return departmentDAO.findById(id);
    }

    public void deleteById(int id) {
        departmentDAO.deleteById(id);
    }

    public boolean checkById(int id) {
        Department department = departmentService.getById(id);
        return department != null;
    }

    public void save(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        if (id != null) {
            int idUp = Integer.parseInt(id);
            departmentDAO.updateDepartment(new Department(idUp, name));
        } else {
            departmentDAO.addDepartment(new Department(name));
        }
    }

}
