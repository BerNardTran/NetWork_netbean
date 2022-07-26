/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Handler;


import Entity.Food;
import Server.ConnectionDatabase;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author ASUS
 */
public class FoodHandler {
//     public static void getAllFood(){
//        Connection connection = null;
//        connection = ConnectionDatabase.getConnection();
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            String sqlQuery = "select * from Food";
//            preparedStatement = connection.prepareStatement(sqlQuery);
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                System.out.println("ID" + resultSet.getInt(1) + " | Name: " + resultSet.getString(2) + " | " + "cost: " + resultSet.getFloat(3));
//            }
//            String sql = "select * from LichSuDangNhap";
//                    pre = conn.prepareStatement(sql);
//                    rs = pre.executeQuery();
//                    tableModel.setRowCount(0);
//                    while (rs.next()) {
//                        Vector arr = new Vector();
//                        arr.add(rs.getString("maNV"));
//                        arr.add(rs.getString("suKien"));
//                        arr.add(rs.getString("thoiGian"));
//                        arr.add(rs.getString("quyenTruyCap"));
//                        tableModel.addRow(arr);
//                    }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }


    public static ArrayList getAllMenu()  {
        ArrayList<Food> listFood = new ArrayList<>();
        Connection connection = ConnectionDatabase.getConnection();
//        Statement statement = null;
//        ResultSet resultSet = null;

        try{
            String sqlQuery = "select * from Food";
            System.out.println(sqlQuery);
            PreparedStatement  statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();
             
            while (resultSet.next()){
                Food food = new Food();
                food.setFoodId(resultSet.getInt(1));
                food.setFoodName(resultSet.getString(2));
                food.setFoodDescription(resultSet.getString(3));
                food.setFoodCost(resultSet.getFloat(4));
                food.setFoodUrlIMG(resultSet.getString(5));
                listFood.add(food);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);

        }
        return listFood;
    }
    
//    public static void main(String[]) args){
//        FoodHandler.getAllMenu();
//    }
    
    public static void InsertFoodItem(String name, String des, float price, String url){
        Connection connection = null;
        connection = ConnectionDatabase.getConnection();
        String sql = "insert into Food values (?, ?, ?, ?)";
        
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, name);
            pre.setString(2, des);
            pre.setFloat(3, price);
            pre.setString(4, url);
            pre.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public static void UpdateFoodItem(int foodId, String name, String des, float price, String url){
        Connection connection = null;
        connection = ConnectionDatabase.getConnection();
        String sql = "update Food set foodName = ?, foodDescription = ?, foodCost = ?, foodUrlIMG = ? where foodId = ?" ; 
        
        try{
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, name);
            pre.setString(2, des);
            pre.setFloat(3, price);
            pre.setString(4, url);
            pre.setInt(5,  foodId);
            pre.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    public static void DeleteFoodItem(int foodId){
        Connection connection = null;
        connection = ConnectionDatabase.getConnection();
        String sql = "delete from Food where foodId = ?";
        try{
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, foodId);
            pre.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
