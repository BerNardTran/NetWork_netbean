/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author tienm
 */
public class OrderHandle {

    public static void CreateOrder(int userId) {
        Connection connection = null;
        connection = ConnectionDatabase.getConnection();
        String sqlQuery = "insert into [order] values(0,'" + userId + "')";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(sqlQuery);
        } catch (Exception e) {
            System.out.println("Can't insert to database");
        }

    }

    public static int GetOrder(int userId) {
        Connection connection = null;
        connection = ConnectionDatabase.getConnection();
        String sqlQuery = "  select * from [Order] where userId = '" + userId + "' and orderId = (select max(orderId) from [order] where userId = '" + userId +"')" ;
        int orderId = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                orderId = resultSet.getInt(1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return orderId;
    }
}
