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

        public Food(){}
        public Food(int foodId, String foodName, float foodCost) {
                this.foodId = foodId;
                this.foodName = foodName;
                this.foodCost = foodCost;
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
