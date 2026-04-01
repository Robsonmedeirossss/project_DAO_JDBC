package model.dao.impl;

import model.dao.DepartmentDAO;
import model.entities.Department;

import java.sql.Connection;
import java.util.List;

public class DepartmentDaoJdbc implements DepartmentDAO {

    private Connection conn;

    public DepartmentDaoJdbc(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department department) {

    }

    @Override
    public void updateById(Integer departmentId, Department newDepartment) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        return null;
    }

    @Override
    public List<Department> findAll() {
        return List.of();
    }
}
