/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import Entity.BuyProduct;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

/**
 *
 * @author ASUS
 */
public class OrderHandler {

    public static String getAllFoodOrder(int orderId) {

        Connection connection = ConnectionDatabase.getConnection();
        FormatData formatData = new FormatData();
        Gson gson = new Gson();
        String str = "";
        try {
            String sql = "select foodName, foodCost, quantity from Food inner join FoodOrder on Food.foodId = FoodOrder.foodId where FoodOrder.orderId = " + orderId;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                formatData.name = resultSet.getString(1);

                formatData.cost = resultSet.getFloat(2);

                formatData.quantity = resultSet.getInt(3);
                String json = gson.toJson(formatData);
                str = str + json + " ";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        String result = str.substring(0, str.length() - 1);
        System.out.println(result);
        return result;
    }

    public static float getAllMoney(int orderId) {
        Connection connection = ConnectionDatabase.getConnection();
        float totalMoney = 0;
        String sqlQuery = "  select totalMoney from [Order] where orderId  = " + orderId;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                totalMoney = resultSet.getFloat(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return totalMoney;
    }

    public static boolean checkedStatusPaymentCard(String num) {
        if (num.length() != 10) {
            return false;
        }
        if(num.length() == 10){
            for (char c : num.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
        }
        return true;
    }
}
