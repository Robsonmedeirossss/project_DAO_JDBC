package model.dao.impl;

import database.DBException;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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

    }

    @Override
    public void updateById(Integer id, Seller updatedSeller) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        return null;
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
