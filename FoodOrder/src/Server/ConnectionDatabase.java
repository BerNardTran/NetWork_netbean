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

    public static Connection getConnection() {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=FoodOrder;encrypt=true;trustServerCertificate=true;";
        String username = "sa";
        String password = "abcd1234";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException ex) {
            System.out.println("Không load được driver");
        } catch (SQLException ex) {
            System.out.println("Không kết nối được");
        }
        return null;
    }

    public Connection getConnection2() throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=FoodOrder;encrypt=true;trustServerCertificate=true;";
        String Username = "sa";
        String Password = "abcd1234";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Connected success");
            return DriverManager.getConnection(url, Username, Password);
        } catch (ClassNotFoundException e) {
            System.out.println("Không load được driver");
        }
        return null;
    }

}
