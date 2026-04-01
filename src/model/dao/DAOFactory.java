package model.dao;

import database.DB;
import model.dao.impl.DepartmentDaoJdbc;
import model.dao.impl.SellerDaoJdbc;

public class DAOFactory {
    public static DepartmentDAO createDepartmentDAO() {
        return new DepartmentDaoJdbc(DB.getConnection());
    }

    public static SellerDAO createSellerDAO() {
        return new SellerDaoJdbc(DB.getConnection());
    }
}
