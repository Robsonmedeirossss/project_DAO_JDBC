import database.DB;
import model.dao.DAOFactory;
import model.dao.DepartmentDAO;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        DepartmentDAO departmentDAO = DAOFactory.createDepartmentDAO();
//
//       List<Department> departments = departmentDAO.findAll();
//
//        for(Department department: departments) {
//            System.out.println(department);
//        }
//
//       departmentDAO.insert(new Department("Operações"));
//
//        departmentDAO.updateById(2, new Department("Tecnologia da Informação"));


        SellerDAO sellerDAO = DAOFactory.createSellerDAO();

        List<Seller> sellers = sellerDAO.findAll();

        for (Seller seller: sellers) {
            System.out.println(seller);
        }



    }
}