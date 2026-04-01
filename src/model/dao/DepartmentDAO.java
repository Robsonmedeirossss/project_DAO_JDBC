package model.dao;

import model.entities.Department;

import java.util.List;

public interface DepartmentDAO {
    void insert(Department department);
    void updateById(Integer departmentId, Department newDepartment);
    void deleteById(Integer id);
    Department findById(Integer id);
    List<Department> findAll();
}
