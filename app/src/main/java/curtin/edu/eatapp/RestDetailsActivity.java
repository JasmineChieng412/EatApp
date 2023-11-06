package curtin.edu.eatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RestDetailsActivity extends AppCompatActivity {

    //private TextView restName, foodPrice, rating;
    private ImageView restImg;
    RecyclerView menuRecycler;
    RestaurantMenuAdapter menuAdapter;
   // private int foodNo = 0;
   // DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_details);

        //restImg = findViewById(R.id.restProfile) ;
        ///foodQuantity = findViewById(R.id.foodQuantityText);
        //foodPrice = findViewById(R.id.priceTxt);
        //3669restImg = findViewById(R.id.foodImgView);
        //rating = findViewById(R.id.foodImgView);

       // db = new DatabaseHelper(this);

       // Intent intent = getIntent();
        //Restaurants selectedItem = intent.getParcelableExtra("Restaurant Selected");

       // restImg.setImageResource(selectedItem.getImageUrl());
       // restImg.setImageResource(selectedItem.getImageUrl());

        List<RestaurantMenu> AsianaFoodList = new ArrayList<>();

        AsianaFoodList.add(new RestaurantMenu("Japanese Sushi", "7.05", R.drawable.asiana_sushi));
        AsianaFoodList.add(new RestaurantMenu("Tempura Prawn", "17.05", R.drawable.asiana_tempura));
        AsianaFoodList.add(new RestaurantMenu("Fried Mushrooms", "25.05", R.drawable.asiana_friedmushroom));
        AsianaFoodList.add(new RestaurantMenu("Salad", "7.35", R.drawable.asiana_salad));
        AsianaFoodList.add(new RestaurantMenu("Special Plate", "15.35", R.drawable.asiana_specialplate));
        AsianaFoodList.add(new RestaurantMenu("Tomato dish", "10.90", R.drawable.asiana_tomato));

        setMenuRecycler(AsianaFoodList);

    }

    private void setMenuRecycler(List<RestaurantMenu> AsianaFoodList) {

        menuRecycler = findViewById(R.id.restRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        menuRecycler.setLayoutManager(layoutManager);
        menuAdapter = new RestaurantMenuAdapter(this, AsianaFoodList);
        menuRecycler.setAdapter(menuAdapter);
    }
}