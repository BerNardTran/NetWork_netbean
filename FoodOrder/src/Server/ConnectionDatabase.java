/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;
import java.sql.*;
/**
 *
 * @author ASUS
 */
public class ConnectionDatabase {
    private static String DB_URL = "jdbc:sqlserver://localhost:1433;" +
            "databaseName=FoodOrder;" +
            "integratedSecurity=true;" +
            "encrypt=false";
    private static String Username = "sa";
    private static String Password = "abcd1234";

    public static Connection getConnection(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            System.out.println("Connected success");
            return DriverManager.getConnection(DB_URL, Username, Password);
        } catch (SQLException e) {
            System.out.println("Không kết nối được");
        } catch (ClassNotFoundException e) {
            System.out.println("Không load được driver");
        }
        return null;
    }
}
