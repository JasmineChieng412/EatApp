package curtin.edu.eatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cartRecyclerView;
    private RecyclerView.Adapter cartAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private DatabaseHelper db;


    private ArrayList<String> name, foodName, foodPrice;
    private ArrayList<Integer> foodQuantity, imageUrl;

    private TextView txtTotPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        txtTotPrice = findViewById(R.id.textTotPrice);

        db = new DatabaseHelper(this);
        name = new ArrayList<>();
        foodName = new ArrayList<>();
        foodPrice = new ArrayList<>();
        foodQuantity = new ArrayList<>();
        imageUrl = new ArrayList<>();

        //CartOrder cartOrder = new CartOrder();
        cartRecyclerView = findViewById(R.id.cartRecycler);
        cartRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        cartRecyclerView.setLayoutManager(layoutManager);

        cartAdapter = new CartAdapter(this, name, foodName, foodQuantity, imageUrl, foodPrice, db);
        cartRecyclerView.setAdapter(cartAdapter);

        displayData();

        txtTotPrice.setText("RM " +String.valueOf(calculateCartPrice()));


    }

    private void displayData() {
        Cursor cursor = db.getUserData();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(CartActivity.this, "No entry exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {
                name.add(cursor.getString(0));
                foodName.add(cursor.getString(1));
                foodQuantity.add(cursor.getInt(2));
                imageUrl.add(cursor.getInt(3));
                foodPrice.add(cursor.getString(4));
            }
        }

    }

    private double calculateCartPrice()
    {
        //int listSize = name.size();
        int tempFoodAmount;
        double tempFoodPrice;
        double totCart = 0;
        Cursor cursor = db.getUserData();

        if(cursor.moveToFirst())
        {
            do {
                tempFoodAmount = cursor.getInt(2);
                tempFoodPrice = Double.parseDouble(cursor.getString(4));
                totCart = totCart + ((double)tempFoodAmount*tempFoodPrice);
            }while(cursor.moveToNext());
        }
        return totCart;
    }

    //meant to refresh cart but idk how to implement
    public void removeCartItem(String foodName, int position)
    {
        db.deleteUserData(foodName, position);
        cartAdapter.notifyItemRemoved(position);
    }

}