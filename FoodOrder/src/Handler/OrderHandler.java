/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Handler;

import Server.ConnectionDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
/**
 *
 * @author ASUS
 */
public class OrderHandler {
    public static void ShowOrder(){
        Connection connection = null;
        connection = ConnectionDatabase.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            String sqlQuery = "select * from [order]";
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
            while (resultSet != null){
                System.out.println("OrderId: " + resultSet.getInt(1) + " | " + "totalMoney" + resultSet.getFloat(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void CreateOrder(int userId){
        Connection connection = null;
        connection = ConnectionDatabase.getConnection();
        String sqlQuery = "insert into [order] values(0,'"+userId+"')";
        try{
            Statement statement = connection.createStatement();
            statement.executeQuery(sqlQuery);
        }catch(Exception  e){
            System.out.println("Can't insert to database");
        }
        
    }
    
    public static void GetOrder(int userId) {
        Connection connection = null;
        connection = ConnectionDatabase.getConnection();
        String sqlQuery = "  select top(1) * from [Order] where userId = '" +userId+"'";
    }
}
