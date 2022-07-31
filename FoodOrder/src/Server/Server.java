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
            PrintWriter out = null;
            BufferedReader in = null;
            StringBuilder stringBuilder = new StringBuilder();
//            Gson gson = new Gson();
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
                int splitIndex = data.indexOf("|");
                int dataLength = data.length();
                String command = data.substring(0,splitIndex);
                String commandData = data.substring(splitIndex+1,dataLength);
                if("checkedUserExist".equals(command)){
                    String userName = commandData;
                    int userid = UserHandle.checkedUserExist(userName);
                    if (userid != 0) {
//                        OrderHandle.CreateOrder(userid);
                        System.out.println(OrderHandle.GetOrder(userid));
                    }
                    out.println(userid);
                }
                

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
