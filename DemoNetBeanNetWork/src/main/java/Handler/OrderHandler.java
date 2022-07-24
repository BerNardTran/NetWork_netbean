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
}
