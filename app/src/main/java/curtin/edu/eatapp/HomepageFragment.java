package curtin.edu.eatapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomepageFragment extends Fragment {

    ImageView profileImage, tab, cartImg;
    Button backToHomepage1, backToHomepage2, logoutButton, loginButton;

    public List<SpecialMenu> popularFoodList = new ArrayList<>();
    public List<Restaurants> allFoodList = new ArrayList<>();

    MainActivity main = new MainActivity();

    RecyclerView popularRecycler;
    RecyclerView restaurantsRecycler;
    SpecialsAdapter popularFoodAdapter;
    RestaurantAdapter restaurantAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);

        profileImage = (ImageView) view.findViewById(R.id.profilePhoto);
        tab = (ImageView) view.findViewById(R.id.tabHompage);

        backToHomepage2 = (Button) view.findViewById(R.id.backButton);
        backToHomepage1 = (Button) view.findViewById(R.id.assistBackButton);
        logoutButton = (Button) view.findViewById(R.id.assistLogOutButton);
        cartImg = (ImageView) view.findViewById(R.id.cartLogo);
        loginButton = (Button) view.findViewById(R.id.assistloginButton);

            profileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    My_Profile profile_frag = (My_Profile) MainActivity.fm.findFragmentById(R.id.profileFrameLayout);
                    if (profile_frag == null) {
                        profile_frag = new My_Profile();
                        MainActivity.fm.beginTransaction().replace(R.id.profileFrameLayout, profile_frag, null).addToBackStack(null).commit();
                    }
                }
            });

            backToHomepage1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (MainActivity.fm.findFragmentById(R.id.profileFrameLayout) != null) {
                        MainActivity.fm.beginTransaction().remove(MainActivity.fm.findFragmentById(R.id.profileFrameLayout)).commit();
                    }
                    // fm.beginTransaction().remove(profile_frag).commit();
                    if (MainActivity.fm.findFragmentById(R.id.logoutFrameLayout) != null) {
                        MainActivity.fm.beginTransaction().remove(MainActivity.fm.findFragmentById(R.id.logoutFrameLayout)).commit();
                    }

                    if (MainActivity.fm.findFragmentById(R.id.loginFrameLayout) != null) {
                        MainActivity.fm.beginTransaction().remove(MainActivity.fm.findFragmentById(R.id.loginFrameLayout)).commit();
                    }

                    Toast.makeText(getActivity(), "Return to Homepage", Toast.LENGTH_SHORT).show();
                }
            });

            tab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LogoutFragment logout_frag = (LogoutFragment) MainActivity.fm.findFragmentById(R.id.logoutFrameLayout);
                    if (logout_frag == null) {
                        logout_frag = new LogoutFragment();
                        MainActivity.fm.beginTransaction().add(R.id.logoutFrameLayout, logout_frag).commit();
                    }

                    LogInTabFragment logintab_frag = (LogInTabFragment) MainActivity.fm.findFragmentById(R.id.loginFrameLayout);
                    if (logintab_frag == null) {
                        logintab_frag = new LogInTabFragment();
                        MainActivity.fm.beginTransaction().add(R.id.loginFrameLayout, logintab_frag).commit();
                    }
                }
            });
            logoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (MainActivity.fm.findFragmentById(R.id.logoutFrameLayout) != null) {
                        MainActivity.fm.beginTransaction().remove(MainActivity.fm.findFragmentById(R.id.logoutFrameLayout)).commit();
                    }
                    Toast.makeText(getActivity(), "Logged Out", Toast.LENGTH_SHORT).show();

                    LoggedOutProfileFragment noprofile_frag = (LoggedOutProfileFragment) MainActivity.fm.findFragmentById(R.id.loggedOutProfileFrameLayout);
                    if (noprofile_frag == null) {
                        noprofile_frag = new LoggedOutProfileFragment();
                        MainActivity.fm.beginTransaction().replace(R.id.loggedOutProfileFrameLayout, new LoggedOutProfileFragment(), null).addToBackStack(null).commit();
                    }
                }
            });

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LogInFragment login_frag = (LogInFragment) MainActivity.fm.findFragmentById(R.id.profileFrameLayout);
                    if (login_frag == null) {
                        login_frag = new LogInFragment();
                        MainActivity.fm.beginTransaction().replace(R.id.profileFrameLayout, new LogInFragment(), null).addToBackStack(null).commit();
                    }
                }
            });

            cartImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getActivity(), CartActivity.class);
                    startActivity(i);
                }
            });

            popularFoodList.add(new SpecialMenu("Japanese Sushi", "7.05", R.drawable.asiana_sushi));
            popularFoodList.add(new SpecialMenu("Tempura Prawn", "17.05", R.drawable.asiana_tempura));
            popularFoodList.add(new SpecialMenu("Moonbucks Holiday Coffee", "25.05", R.drawable.starbucks_holiday_special));
            popularFoodList.add(new SpecialMenu("Screamo Donuts", "7.35", R.drawable.donut_screamo));
            popularFoodList.add(new SpecialMenu("Signature Fried Rice", "15.35", R.drawable.rice_signature));
            popularFoodList.add(new SpecialMenu("Chill Frappe", "10.90", R.drawable.starbucks_frappe));
            popularFoodList.add(new SpecialMenu("Mango Rice Set", "39.99", R.drawable.rice_setmango));

         setPopularRecycler(popularFoodList);

            allFoodList.add(new Restaurants("Asiana Cuisine", "20", R.drawable.asiana_logo, "4.5", "Briand Restaurant"));
            allFoodList.add(new Restaurants("Pluckin' Donuts", "25", R.drawable.donut_logo, "4.2", "Friends Restaurant"));
            allFoodList.add(new Restaurants("The Rice Shop", "20", R.drawable.rice_logo, "4.5", "Briand Restaurant"));
            allFoodList.add(new Restaurants("Starbucks", "25", R.drawable.starbucks_logo, "4.2", "Friends Restaurant"));
            allFoodList.add(new Restaurants("Fruit Donut", "20", R.drawable.donut_fruit, "4.5", "Briand Restaurant"));
            allFoodList.add(new Restaurants("Choco Donut", "25", R.drawable.donut_choco, "4.2", "Friends Restaurant"));

            setRestaurantRecycler(allFoodList);
        return view;
        }
    private void setPopularRecycler(List<SpecialMenu> popularFoodList) {
        View view = null;
        popularRecycler = view.findViewById(R.id.popular_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        popularRecycler.setLayoutManager(layoutManager);
        popularFoodAdapter = new SpecialsAdapter(getActivity(), popularFoodList);
        popularRecycler.setAdapter(popularFoodAdapter);

    }

    private void setRestaurantRecycler(List<Restaurants> allFoodList) {

        View view = null;
        restaurantsRecycler = view.findViewById(R.id.restaurant_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        restaurantsRecycler.setLayoutManager(layoutManager);
        restaurantAdapter = new RestaurantAdapter(getActivity(), allFoodList);
        restaurantsRecycler.setAdapter(restaurantAdapter);
    }


}