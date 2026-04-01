package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

     private static Properties loadProperties() {
         try(FileInputStream fs = new FileInputStream("db.properties")){
             Properties props = new Properties();
             props.load(fs);
             return props;
         }catch (IOException error) {
            throw  new DBException(error.getMessage());
         }
     }

     public static Connection getConnection() {
         if(conn == null) {
             try {
                Properties props = loadProperties();
                String dbUrl = props.getProperty("db.url");
                String dbUser = props.getProperty("db.user");
                String dbPassword = props.getProperty("db.password");
                conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             }catch (SQLException error){
                throw new DBException(error.getMessage());
             }
         }

         return conn;
     }

     public static void closeConnection() {
         if(conn != null) {
             try {
                 conn.close();
             }catch (SQLException error) {
                 throw new DBException(error.getMessage());
             }
         }
     }
}
