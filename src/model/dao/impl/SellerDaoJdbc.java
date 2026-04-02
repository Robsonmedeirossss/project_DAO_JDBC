package model.dao.impl;

import database.DBException;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SellerDaoJdbc implements SellerDAO {

    private Connection conn;
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public SellerDaoJdbc(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller seller) {
        try {
            PreparedStatement statement = conn.prepareStatement("INSERTO INTO sellers(name, email, base_salary, department_id) "
                + "VALUES(?, ?, ?, ?);"
            );
            statement.setString(1, seller.getName());
            statement.setString(2, seller.getEmail());
            statement.setDouble(3, seller.getBaseSalary());
            statement.setInt(4, seller.getDepartment().getId());

            int rowsAffected = statement.executeUpdate();

            if(rowsAffected > 0) {
                System.out.println("Seller created! Rows affected: " + rowsAffected);
            } else {
                System.out.println("Unexpected error! Rows affected: " + rowsAffected);
            }

        }catch (SQLException error) {
            throw new DBException(error.getMessage());
        }
    }

    @Override
    public void updateById(Integer id, Seller newSeller) {
        try {
            PreparedStatement statement = conn.prepareStatement("UPDATE sellers SET "
            + "name = ?, email = ?, base_salary = ?, department_id = ?;"
            );
            statement.setString(1, newSeller.getName());
            statement.setString(2, newSeller.getEmail());
            statement.setDouble(3, newSeller.getBaseSalary());
            statement.setInt(4, newSeller.getDepartment().getId());

            int rowsAffected = statement.executeUpdate();

            if(rowsAffected > 0) {
                System.out.println("Seller updated! Rows affected: " + rowsAffected);
            } else {
                System.out.println("Unexpected error! Rows affected: " + rowsAffected);
            }
        }catch (SQLException error) {
            throw new DBException(error.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM sellers WHERE id = ?;");
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();

            if(rowsAffected > 0) {
                System.out.println("Seller deleted! rows affected: " + rowsAffected);
            } else {
                System.out.println("Unexpected error! Rows affected: " + rowsAffected);
            }

        }catch (SQLException error) {
            throw new DBException(error.getMessage());
        }
    }

    @Override
    public Seller findById(Integer id) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT sellers.*, departments.name AS department_name "
                    + "FROM sellers INNER JOIN departments "
                    + "ON sellers.department_id = departments.id "
                    + "WHERE sellers.department_id = ?;"
            );

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Seller seller = null;

            if(resultSet.next()) {
                seller = instantiateSeller(resultSet);
                return seller;
            }
            return seller;

        }catch (SQLException error) {
            throw new DBException(error.getMessage());
        }
    }

    @Override
    public List<Seller> findAll() {

        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT sellers.*, departments.name AS department_name "
                    + "FROM sellers INNER JOIN departments "
                    + "ON sellers.department_id = departments.id;"
            );
            List<Seller> sellers = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                    Seller seller = instantiateSeller(resultSet);
                    sellers.add(seller);
            }

            return sellers;

        }catch (SQLException error) {
            throw new DBException(error.getMessage());
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT sellers.*, departments.name AS department_name "
                            + "FROM sellers INNER JOIN departments "
                            + "ON sellers.department_id = ?;"
            );
            statement.setInt(1, department.getId());
            List<Seller> sellers = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Seller seller = instantiateSeller(resultSet);
                sellers.add(seller);
            }

            return sellers;

        }catch (SQLException error) {
            throw new DBException(error.getMessage());
        }
    }

    private static Seller instantiateSeller(ResultSet resultSet) throws SQLException {
        Seller seller = new Seller();
        seller.setId(resultSet.getInt("id"));
        seller.setName(resultSet.getString("name"));
        seller.setEmail(resultSet.getString("email"));
        seller.setBaseSalary(resultSet.getDouble("base_salary"));
        seller.setDepartment(new Department(
                resultSet.getInt("department_id"),
                resultSet.getString("department_name"))
        );

        return seller;

    }
}
