/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author BerNard
 */
public class Food {

    private int foodId;
    private String foodName;
    private float foodCost;
    private String foodDescription;
    private String foodUrlIMG;

    public Food() {
    }

    public Food(int foodId, String foodName, float foodCost, String foodDescription , String foodUrlIMG) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodCost = foodCost;
        this.foodDescription = foodDescription;
        this.foodUrlIMG = foodUrlIMG;
    }
    
    public String getFoodDescription(){
        return foodDescription;
    }
    
    public void setFoodDescription(String foodDescription){
        this.foodDescription = foodDescription;
    }
    
   public void setFoodUrlIMG(String foodUrlIMG){
       this.foodUrlIMG = foodUrlIMG;
   }
    public String getFoodUrlIMG(){
        return foodUrlIMG;
    }
    
    public int getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public float getFoodCost() {
        return foodCost;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setFoodCost(float foodCost) {
        this.foodCost = foodCost;
    }
}
