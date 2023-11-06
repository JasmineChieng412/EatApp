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

import java.io.Serializable;
import java.util.List;


public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    Context context;
    List<Restaurants> allFoodList;
  //  ItemClickListener clickListener;

    public RestaurantAdapter(Context context, List<Restaurants> allFoodList) {
        this.context = context;
        this.allFoodList = allFoodList;
       // final ItemClickListener clickListener = this.clickListener;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.restaurant_list, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder( RestaurantViewHolder holder, int position) {

        holder.foodImage.setImageResource(allFoodList.get(position).getImageUrl());
        holder.name.setText(allFoodList.get(position).getName());
        holder.price.setText(allFoodList.get(position).getPrice());
        holder.rating.setText(allFoodList.get(position).getRating());
        holder.restorantName.setText(allFoodList.get(position).getRestorantname());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, RestDetailsActivity.class);
                //Restaurants special = allFoodList.get(position);

               // i.putExtra("Restaurant Selected", String.valueOf(special));
                /*
                i.putExtra("Food Image", popularFoodList.get(position).getImageUrl());
                i.putExtra("Food Name", popularFoodList.get(position).getName());
                i.putExtra("Food Price", popularFoodList.get(position).getPrice());*/

                context.startActivity(i);
               /* restaurantPageFragment menu_frag = (restaurantPageFragment) fm.findFragmentById(R.id.profileFrameLayout);
                if (menu_frag == null) {
                    menu_frag = new restaurantPageFragment();
                    fm.beginTransaction().add(R.id.profileFrameLayout, menu_frag).commit();

                }*/

                //clickListener.onItemClick(allFoodList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return allFoodList.size();
    }


    public static final class RestaurantViewHolder extends RecyclerView.ViewHolder{


        ImageView foodImage;
        TextView price, name, rating, restorantName;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.food_image);
            price = itemView.findViewById(R.id.price);
            name = itemView.findViewById(R.id.name);
            rating = itemView.findViewById(R.id.rating);
            restorantName = itemView.findViewById(R.id.restorant_name);
        }
    }


   /* public interface ItemClickListener{
        public void onItemClick(Restaurants restaurants);
    }*/
}
