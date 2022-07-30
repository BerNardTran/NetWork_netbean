/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ASUS
 */
public class UserHandle {
    public static int checkedUserExist(String userName) {
            Connection connection = null;
            connection = ConnectionDatabase.getConnection();
            int userId = 0;
            try {
                String sqlQuery = "select * from [user] where userName = '" + userName + "';";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);

                while (resultSet.next()) {
                    if (userName.equals(resultSet.getString(2))) {
                        userId = resultSet.getInt(1);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return userId;
        }
}
