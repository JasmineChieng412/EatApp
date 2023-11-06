package curtin.edu.eatapp;

import static curtin.edu.eatapp.MainActivity.fm;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RestaurantMenuAdapter extends RecyclerView.Adapter<RestaurantMenuAdapter.MenuFoodViewHolder> {

    Context context;
    List<RestaurantMenu> restaurantlist;

    public RestaurantMenuAdapter(Context context, List<RestaurantMenu> restaurantlist) {
        this.context = context;
        this.restaurantlist = restaurantlist;
    }

    @NonNull
    @Override
    public MenuFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.restaurant_menu_layout, parent, false);
        return new MenuFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuFoodViewHolder holder, int position) {

        holder.foodImage.setImageResource(restaurantlist.get(position).getImageUrl());
        holder.name.setText(restaurantlist.get(position).getName());
        holder.price.setText(restaurantlist.get(position).getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent i = new Intent(context, DetailsActivity.class);
                RestaurantMenu special = restaurantlist.get(position);

                i.putExtra("Food Selected", special);*/
                /*
                i.putExtra("Food Image", popularFoodList.get(position).getImageUrl());
                i.putExtra("Food Name", popularFoodList.get(position).getName());
                i.putExtra("Food Price", popularFoodList.get(position).getPrice());

                 */

                /*context.startActivity(i);*/

                restaurantPageFragment menu_frag = (restaurantPageFragment) fm.findFragmentById(R.id.profileFrameLayout);
                if (menu_frag == null) {
                    menu_frag = new restaurantPageFragment();
                    fm.beginTransaction().add(R.id.profileFrameLayout, menu_frag).commit();
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return restaurantlist.size();
    }


    public static final class MenuFoodViewHolder extends RecyclerView.ViewHolder{


        ImageView foodImage;
        TextView price, name;

        public MenuFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.food_image);
            price = itemView.findViewById(R.id.price);
            name = itemView.findViewById(R.id.name);

        }
    }

}


