/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

import Const.PORT;
import java.io.IOException;

/**
 *
 * @author ASUS
 */

public class testSocket {
   
    
    public static void main(String[] args) throws IOException{
        SocketFunction socketFuntion = new SocketFunction();
        try{
            socketFuntion.startConnection("localhost", PORT.PORT);
            System.out.println("abcd");
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
//        socketFuntion.startConnection("localhost", PORT.PORT);
    }
}
