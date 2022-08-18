/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author ASUS
 */
public class SocketFunction {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public String sendUserData(String name) throws IOException {
        String a = "checkedUserExist|" + name;
        out.println(a);
        out.flush();
        return in.readLine();
    }

    public String sendOneFood(int foodId, int orderId, int quantity) throws IOException {
        String sql = "checking_to_update_or_insert " + foodId + ", " + orderId + ", " + quantity;
        String cmd = "sendOneFood|" + sql;
        out.println(cmd);
        out.flush();
        return in.readLine();
    }

    public String getAllFoodorder(int orderId) throws IOException {
        String cmd = "getAllFood|" + orderId;
        out.println(cmd);
        out.flush();
        return in.readLine();
    }

    public String getTotalMoney(int orderId) throws IOException {
        String cmd = "getTotalMoney|" + orderId;
        out.println(cmd);
        out.flush();
        return in.readLine();
    }

    public String checkedStatusPayment(String num) throws IOException {
        String cmd = "checkedStatus|" + num;
        out.println(cmd);
        out.flush();
        return in.readLine();
    }
}
