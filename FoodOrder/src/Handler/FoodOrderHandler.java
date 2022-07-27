/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Handler;

import Entity.FoodOrder;
import Server.ConnectionDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author ASUS
 */
public class FoodOrderHandler {
    public static void insertOrderFood(FoodOrder foodOrder){
        Connection connection = null;
        connection = ConnectionDatabase.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sqlQuery = "insert into FoodOrder values(" + foodOrder.getFoodId() + ", " + foodOrder.getOrderId() + ", " + foodOrder.getQuantity() + ")";
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
//
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
