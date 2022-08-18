/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Handler;

import Entity.BuyProduct;
import Server.ConnectionDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class OrderHandler {

    public static void ShowOrder() {
        Connection connection = null;
        connection = ConnectionDatabase.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sqlQuery = "select * from [order]";
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
            while (resultSet != null) {
                System.out.println("OrderId: " + resultSet.getInt(1) + " | " + "totalMoney" + resultSet.getFloat(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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

    public static void insertOneProduct(int foodId, int orderId, int quantity) {
        Connection connection = null;
        connection = ConnectionDatabase.getConnection();
        String sql = "checking_to_update_or_insert ?, ? ,?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, foodId);
            pre.setInt(2, orderId);
            pre.setInt(3, quantity);
            pre.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getFoodIdByName(String foodName) {
        Connection connection = null;
        connection = ConnectionDatabase.getConnection();
        String sql = "select foodId from Food where Food.foodName = '" + foodName + "'";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int foodId = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                foodId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return foodId;
    }
}
