/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InsertData;

import Entity.FoodOrder;
//import Handler.FoodHandler;
import Handler.FoodOrderHandler;
import Server.FormatData;

import java.util.Scanner;
/**
 *
 * @author ASUS
 */
public class InsertFoodOrder {
        public static void InputFoodOrderDataFromKeyBoard () {
        FoodOrder foodOrder = new FoodOrder();
        Scanner input = new Scanner(System.in);
        FormatData formatData = new FormatData();
        System.out.println("Food ID - OrderID - Quantity: ");// fix is needed
        foodOrder.setFoodId(input.nextInt());
        foodOrder.setOrderId(input.nextInt());
        foodOrder.setQuantity(input.nextInt());
        formatData.foodId = foodOrder.getFoodId();
        formatData.orderId = foodOrder.getOrderId();
        formatData.quantity = foodOrder.getQuantity();
        input.close();
        FoodOrderHandler.insertOrderFood(foodOrder);
    }
}
