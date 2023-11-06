package curtin.edu.eatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    private TextView foodName, foodQuantity, foodPrice;
    private ImageView foodImg, minusImg, plusImg, addCartImg;
    private int foodNo = 0;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        foodName = findViewById(R.id.foodNameText) ;
        foodQuantity = findViewById(R.id.foodQuantityText);
        foodPrice = findViewById(R.id.priceTxt);
        foodImg = findViewById(R.id.foodImgView);
        minusImg = findViewById(R.id.minusImgView);
        plusImg =findViewById(R.id.plusImgView);
        addCartImg = findViewById(R.id.addCartImgView);

        db = new DatabaseHelper(this);

        Intent intent = getIntent();
        SpecialMenu selectedItem = intent.getParcelableExtra("Food Selected");

        foodImg.setImageResource(selectedItem.getImageUrl());
        foodName.setText(selectedItem.getName());
        foodPrice.setText(selectedItem.getPrice());
        foodQuantity.setText(String.valueOf(foodNo));


        minusImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (foodNo >= 1) {
                    foodNo--;
                    foodQuantity.setText(String.valueOf(foodNo));
                } else
                {
                    //show snackbar maybe
                    //do nothing if amount already 0
                }
            }
        });

        plusImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodNo++;
                foodQuantity.setText(String.valueOf(foodNo));
            }
        });

        addCartImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(foodNo < 1)
                {
                    Toast.makeText(DetailsActivity.this, "Please add more quantity", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkInsertData = db.insertUserData("jiayi", selectedItem.getName(), foodNo, selectedItem.getImageUrl(), selectedItem.getPrice());
                    if (checkInsertData) {
                        Toast.makeText(DetailsActivity.this,
                                "Added to Cart",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailsActivity.this,
                                "Failed to add",
                                Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}