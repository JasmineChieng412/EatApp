package curtin.edu.eatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView profileImage, tab, cartImg;
    Button backToHomepage1, backToHomepage2, logoutButton, loginButton;

      RecyclerView popularRecycler ;
      RecyclerView restaurantsRecycler;
      SpecialsAdapter popularFoodAdapter;
      RestaurantAdapter restaurantAdapter;

    public static FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();

        profileImage = (ImageView) findViewById(R.id.profilePhoto);
        tab = (ImageView) findViewById(R.id.tabHompage);

        backToHomepage2 = (Button) findViewById(R.id.backButton);
        backToHomepage1 =  (Button) findViewById(R.id.assistBackButton);
        logoutButton = (Button) findViewById(R.id.assistLogOutButton);
        cartImg = (ImageView) findViewById(R.id.cartLogo);
        loginButton = (Button) findViewById(R.id.assistloginButton);

        //adding homepage fragment
       /* HomepageFragment home_frag = (HomepageFragment) fm.findFragmentById(R.id.HomepageFrameLayout);
        if (home_frag == null) {
            home_frag = new HomepageFragment();
            fm.beginTransaction().add(R.id.HomepageFrameLayout, home_frag).commit();

        }
    }

    public void setPopularRecycler(List<SpecialMenu> popularFoodList) {
       // popularRecycler = findViewById(R.id.popular_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        popularRecycler.setLayoutManager(layoutManager);
        popularFoodAdapter = new SpecialsAdapter(this, popularFoodList);
        popularRecycler.setAdapter(popularFoodAdapter);

    }

    public void setRestaurantRecycler (List < Restaurants > asiaFoodList) {
        //restaurantsRecycler = findViewById(R.id.restaurant_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        restaurantsRecycler.setLayoutManager(layoutManager);
        restaurantAdapter = new RestaurantAdapter(this, asiaFoodList);
        restaurantsRecycler.setAdapter(restaurantAdapter);

    }
}*/

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                My_Profile profile_frag = (My_Profile) fm.findFragmentById(R.id.profileFrameLayout);
                if(profile_frag==null){
                    profile_frag = new My_Profile();
                    fm.beginTransaction().add(R.id.profileFrameLayout, profile_frag).commit();
                }
            }
        });

        backToHomepage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {;

                if(fm.findFragmentById(R.id.profileFrameLayout) != null) {
                    fm.beginTransaction().remove(fm.findFragmentById(R.id.profileFrameLayout)).commit();
                }
                // fm.beginTransaction().remove(profile_frag).commit();
                if (fm.findFragmentById(R.id.logoutFrameLayout) != null) {
                    fm.beginTransaction().remove(fm.findFragmentById(R.id.logoutFrameLayout)).commit();
                }

                if (fm.findFragmentById(R.id.loginFrameLayout) != null) {
                    fm.beginTransaction().remove(fm.findFragmentById(R.id.loginFrameLayout)).commit();
                }

                Toast.makeText(MainActivity.this, "Return to Homepage", Toast.LENGTH_SHORT).show();
            }
        });

        tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogoutFragment logout_frag = (LogoutFragment) fm.findFragmentById(R.id.logoutFrameLayout);
                if(logout_frag==null){
                    logout_frag = new LogoutFragment();
                    fm.beginTransaction().add(R.id.logoutFrameLayout, logout_frag).commit();
                }

                LogInTabFragment logintab_frag = (LogInTabFragment) fm.findFragmentById(R.id.loginFrameLayout);
                if(logintab_frag==null){
                    logintab_frag = new LogInTabFragment();
                    fm.beginTransaction().add(R.id.loginFrameLayout, logintab_frag).commit();
                }
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (fm.findFragmentById(R.id.logoutFrameLayout) != null) {
                    fm.beginTransaction().remove(fm.findFragmentById(R.id.logoutFrameLayout)).commit();
                }
                Toast.makeText(MainActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();

                LoggedOutProfileFragment noprofile_frag = (LoggedOutProfileFragment) fm.findFragmentById(R.id.loggedOutProfileFrameLayout);
                if (noprofile_frag == null) {
                    noprofile_frag = new LoggedOutProfileFragment();
                    fm.beginTransaction().add(R.id.loggedOutProfileFrameLayout, noprofile_frag).commit();
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogInFragment login_frag = (LogInFragment) fm.findFragmentById(R.id.profileFrameLayout);
                if (login_frag == null) {
                    login_frag = new LogInFragment();
                    fm.beginTransaction().add(R.id.profileFrameLayout, login_frag).commit();
                }
            }
        });

        cartImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CartActivity.class);
                startActivity(i);
            }
        });

        List<SpecialMenu> popularFoodList = new ArrayList<>();

        popularFoodList.add(new SpecialMenu("Japanese Sushi", "7.05", R.drawable.asiana_sushi));
        popularFoodList.add(new SpecialMenu("Tempura Prawn", "17.05", R.drawable.asiana_tempura));
        popularFoodList.add(new SpecialMenu("Moonbucks Holiday Coffee", "25.05", R.drawable.starbucks_holiday_special));
        popularFoodList.add(new SpecialMenu("Screamo Donuts", "7.35", R.drawable.donut_screamo));
        popularFoodList.add(new SpecialMenu("Signature Fried Rice", "15.35", R.drawable.rice_signature));
        popularFoodList.add(new SpecialMenu("Chill Frappe", "10.90", R.drawable.starbucks_frappe));
        popularFoodList.add(new SpecialMenu("Mango Rice Set", "39.99", R.drawable.rice_setmango));

        setPopularRecycler(popularFoodList);

        List<Restaurants> allFoodList = new ArrayList<>();
        allFoodList.add(new Restaurants("Asiana Cuisine", "20", R.drawable.asiana_logo, "4.5", "Briand Restaurant"));
        allFoodList.add(new Restaurants("Pluckin' Donuts", "25", R.drawable.donut_logo, "4.2", "Friends Restaurant"));
        allFoodList.add(new Restaurants("The Rice Shop", "20", R.drawable.rice_logo, "4.5", "Briand Restaurant"));
        allFoodList.add(new Restaurants("Starbucks", "25", R.drawable.starbucks_logo, "4.2", "Friends Restaurant"));
        allFoodList.add(new Restaurants("Fruit Donut", "20", R.drawable.donut_fruit, "4.5", "Briand Restaurant"));
        allFoodList.add(new Restaurants("Choco Donut", "25", R.drawable.donut_choco, "4.2", "Friends Restaurant"));

        setRestaurantRecycler(allFoodList);


    }

    private void setPopularRecycler(List<SpecialMenu> popularFoodList) {

        popularRecycler = findViewById(R.id.popular_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        popularRecycler.setLayoutManager(layoutManager);
        popularFoodAdapter = new SpecialsAdapter(this, popularFoodList);
        popularRecycler.setAdapter(popularFoodAdapter);

    }

    private void setRestaurantRecycler(List<Restaurants> allFoodList) {

        restaurantsRecycler = findViewById(R.id.restaurant_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        restaurantsRecycler.setLayoutManager(layoutManager);
        restaurantAdapter = new RestaurantAdapter(this, allFoodList);
        restaurantsRecycler.setAdapter(restaurantAdapter);
    }





}