package curtin.edu.eatapp;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

public class CartOrder implements Parcelable {

    List<FoodOrder> cartList = new ArrayList<>();

    public CartOrder() {
    }

    protected CartOrder(Parcel in) {
    }

    public static final Creator<CartOrder> CREATOR = new Creator<CartOrder>() {
        @Override
        public CartOrder createFromParcel(Parcel in) {
            return new CartOrder(in);
        }

        @Override
        public CartOrder[] newArray(int size) {
            return new CartOrder[size];
        }
    };

    public List<FoodOrder> getCartList() {
        return cartList;
    }

    public void setCartList(List<FoodOrder> cartList) {
        this.cartList = cartList;
    }

    // adds a food item and quantity to cart
    public void addFoodOrder(SpecialMenu inFood, int inAmount)
    {
        cartList.add(new FoodOrder(inFood));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    private class FoodOrder{
        SpecialMenu food;
        int foodQuantity;

        public FoodOrder(SpecialMenu food) { //default no is 1 bc thats the default amount when u order until more is added
            this.food = food;
            this.foodQuantity = 1;
        }

        public SpecialMenu getFood() {
            return food;
        }

        public void setFood(SpecialMenu food) {
            this.food = food;
        }

        public Integer getFoodQuantity() {
            return foodQuantity;
        }

        public void setFoodQuantity(Integer foodQuantity) {
            this.foodQuantity = foodQuantity;
        }

        public void incFoodQ()
        {
            foodQuantity++;
        }

        public void decFoodQ()
        {
            if(foodQuantity <= 1)
            {
                foodQuantity = 0;
            }
            else
            {
                foodQuantity--;
            }
        }

        public double foodOrderTot()
        {
            double price = Double.parseDouble(food.getPrice());
            price = price*foodQuantity;

            return price;

        }


    }
}
