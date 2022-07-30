/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import Entity.User;
import Const.PORT;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ASUS
 */
public class Server {

    public static void main(String args[]) {
//        FoodHandler.getAllFood();
//        InsertFoodOrder.InputFoodOrderDataFromKeyBoard();
//        InsertUserName.insertNameOfClient();

        ServerSocket serverSocket = null;
        User user = new User();
        try {
            serverSocket = new ServerSocket(PORT.PORT);
            serverSocket.setReuseAddress(true);
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("New client connected" + " "
                        + client.getInetAddress()
                                .getHostAddress());
                ClientHandler clientHandler = new ClientHandler(client);
                new Thread(clientHandler).start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class ClientHandler implements Runnable {

        private final Socket clientSocket;

        // Constructor
        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

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
                System.out.println("username: " + userName + " | id: " + userId);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return userId;
        }

        @Override
        public void run() {
            PrintWriter out = null;
            BufferedReader in = null;
            StringBuilder stringBuilder = new StringBuilder();
            Gson gson = new Gson();
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                String firstline = in.readLine(); //get name
////                String line;
////                while ((line = in.readLine()) != null){
////                    stringBuilder.append(line);
////                }
////                String result = stringBuilder.toString();
//                FormatData formatData = gson.fromJson(firstline, FormatData.class);
////                System.out.println("CLIENT: " + formatData.name + ", " + formatData.foodId + ", " + formatData.orderId + ", " + formatData.quantity);
//                System.out.println("CLIENT: " + formatData.name + " joined!");
                String data = in.readLine();
                String[] splitedData = data.split("|");
                
                String command = splitedData[0];
                String commandData = splitedData[1];
                System.out.println(splitedData);
                if("checkedUserExist".equals(command)){
                    String userName = commandData;
                    int userid = checkedUserExist(userName);
                    System.out.println("username: " + userName + " joined");
                    out.println(userid);
                }
                

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
