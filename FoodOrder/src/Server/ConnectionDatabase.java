/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;
//import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author ASUS
 */


public class ConnectionDatabase {
//    private static String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=FoodOrder;encrypt=true;trustServerCertificate=true;";
//    private static String Username = "sa";
//    private static String Password = "abcd1234";

//    public static Connection getConnection(){
//        try{
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
////            System.out.println("Connected success");
//            return DriverManager.getConnection(DB_URL, Username, Password);
//        } catch (SQLException e) {
//            System.out.println("Không kết nối được");
//        } catch (ClassNotFoundException e) {
//            System.out.println("Không load được driver");
//        }
//        return null;
//    }
        public static Connection getConnection(){
            String url = "jdbc:sqlserver://localhost:1433;databaseName=FoodOrder;encrypt=true;trustServerCertificate=true;";
            String username = "sa";
            String password = "abcd1234";
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                System.out.println("dcmmm");
                return DriverManager.getConnection(url, username, password);
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Không load được driver");
        } catch (SQLException ex) {
            System.out.println("Không kết nối được");
        }
        return null;
    }
}
