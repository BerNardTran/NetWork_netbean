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
//import com.google.gson.Gson;
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

        @Override
        public void run() {

//            Gson gson = new Gson();
            while (true) {
                PrintWriter out = null;
                BufferedReader in = null;
                StringBuilder stringBuilder = new StringBuilder();
                try {
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    while (true) {
                        String data = in.readLine();
                        System.out.println("data: " + data);
                        int splitIndex = data.indexOf("|");
                        int dataLength = data.length();
                        String command = data.substring(0, splitIndex);
                        String commandData = data.substring(splitIndex + 1, dataLength);

                        if ("checkedUserExist".equals(command)) {
                            String response = "";
                            String userName = commandData;
                            int userid = UserHandle.checkedUserExist(userName);
                            int orderId = 0;
                            if (userid != 0) {
                                OrderHandle.CreateOrder(userid);
                                orderId = OrderHandle.GetOrder(userid);
                            }
                            response = userid + "|" + orderId;
                            out.println(response);
                        }
                        System.out.println("command: " + commandData);
                        if ("sendOneFood".equals(command)) {
                            OrderHandle.insertOrder(commandData);
                            out.println("true");
                        }
                        if("getAllFood".equals(command)){
                            String result = OrderHandler.getAllFoodOrder(Integer.valueOf(commandData));
                            out.println(result);
                        }
                        if("getTotalMoney".equals(command)){
                            float result = OrderHandler.getAllMoney(Integer.valueOf(commandData));
                            System.out.println(">>>> total: " + result);
                            out.println(result);
                        }
                        if("checkedStatus".equals(command)){
                            boolean check = OrderHandler.checkedStatusPaymentCard(commandData);
                            System.out.println(">>>>> check: " + check);
                            out.println(check);
                        }
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

}
