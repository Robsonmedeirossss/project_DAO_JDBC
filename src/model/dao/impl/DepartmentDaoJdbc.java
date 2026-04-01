package model.dao.impl;

import database.DBException;
import model.dao.DepartmentDAO;
import model.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJdbc implements DepartmentDAO {

    private Connection conn;

    public DepartmentDaoJdbc(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department department) {
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO departments(name) VALUES(?);");
            statement.setString(1, department.getName());
            int rowsAffected = statement.executeUpdate();

            if(rowsAffected > 0){
                System.out.println("Department added with sucess");
            }

        }catch (SQLException error) {
            throw new DBException(error.getMessage());
        }

    }

    @Override
    public void updateById(Integer departmentId, Department newDepartment) {
        try {
            PreparedStatement statement = conn.prepareStatement("UPDATE departments SET name = ? WHERE id = ?;");
            statement.setString(1, newDepartment.getName());
            statement.setInt(2, departmentId);
            int rowsAffected = statement.executeUpdate();
            if(rowsAffected > 0) {
                System.out.println("Department updated sucess!");
            }

        }catch (SQLException error) {
            throw new DBException(error.getMessage());
        }

    }

    @Override
    public void deleteById(Integer id) {
        try {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM departments WHERE id = ?;");
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0){
                System.out.println("Department deleted sucess");
            }
        }catch (SQLException error) {
            throw  new DBException(error.getMessage());
        }
    }

    @Override
    public Department findById(Integer id) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM departments WHERE id = ?;");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Department department = new Department();

            if(resultSet.next()) {
                department.setId(resultSet.getInt("id"));
                department.setName(resultSet.getString("name"));
            }

            return department;
        }catch (SQLException error) {
            throw new DBException(error.getMessage());
        }
    }

    @Override
    public List<Department> findAll() {

        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM departments;");
            ResultSet resultSet = statement.executeQuery();
            List<Department> departments =  new ArrayList<>();

            while (resultSet.next()) {
                Department department = instantiateDepartment(resultSet, new Department());
                departments.add(department);
            }

            return departments;
        } catch (SQLException error) {
            throw new DBException(error.getMessage());
        }
    }
    private Department instantiateDepartment(ResultSet resultSet, Department department) throws SQLException {
        department.setId(resultSet.getInt("id"));
        department.setName(resultSet.getString("name"));

        return department;
    }
}
