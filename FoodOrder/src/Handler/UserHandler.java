/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Handler;

import Entity.User;
import Server.ConnectionDatabase;

import java.sql.*;
/**
 *
 * @author ASUS
 */
public class UserHandler {
        public static void insertUserName(User user){
        Connection connection = null;
        connection = ConnectionDatabase.getConnection();

        try{
            String sqlQuery = "insert into [User] values ('" + user.getUserName() + "')";
//            System.out.println(sqlQuery);
//            preparedStatement = connection.prepareStatement(sqlQuery);
//            resultSet = preparedStatement.executeQuery();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            System.out.println("Success add your name");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
