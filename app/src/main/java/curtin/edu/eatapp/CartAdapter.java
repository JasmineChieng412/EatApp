package curtin.edu.eatapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>
{
    Context context;
    ArrayList name, foodName, foodQuantity, imageUrl, foodPrice;
    DatabaseHelper db;
    //AdapterView.OnItemClickListener cartListener;

    public CartAdapter(Context context, ArrayList name, ArrayList foodName, ArrayList foodQuantity, ArrayList imageUrl, ArrayList foodPrice, DatabaseHelper db) {
        this.context = context;
        this.name = name;
        this.foodName = foodName;
        this.foodQuantity = foodQuantity;
        this.imageUrl = imageUrl;
        this.foodPrice = foodPrice;
        this.db = db;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_line_cart, parent, false);
        return new CartViewHolder(v/*, cartListener*/);  //new change
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        //position = holder.getBindingAdapterPosition(); //gets rid of the warning sign idk if its important

        String tempUserName = String.valueOf(name.get(position));
        String tempFoodName = String.valueOf(foodName.get(position));
        int tempFoodNo = (int) foodQuantity.get(position);
        int tempFoodURL = (int)imageUrl.get(position);
        String tempFoodPrice = String.valueOf(foodPrice.get(position));

        holder.position = position;
        holder.foodURL = tempFoodURL;
        holder.userName = tempUserName;
        holder.foodName = tempFoodName;
        holder.foodPrice = tempFoodPrice;

        holder.textFoodName.setText(tempFoodName);
        holder.foodImage.setImageResource(tempFoodURL);
        holder.textFoodNo.setText(String.valueOf(tempFoodNo)); //rmb to parse to string when dealing with int

        /*
        holder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteUserData(tempFoodName);
            }
        });
*/
        //idk why it only works once
        /*
        holder.minBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean updateSuccess;
                int tempFoodNo = ((int) foodQuantity.get(position)) - 1;
                updateSuccess = db.updateUserData(tempUserName, tempFoodName, tempFoodNo, tempFoodURL, tempFoodPrice);
                if(updateSuccess)
                {
                    Toast.makeText(context, "update success", Toast.LENGTH_SHORT).show();
                    //holder.textFoodNo.setText(String.valueOf(foodNo));
                }
                else
                {
                    Toast.makeText(context, "update failed", Toast.LENGTH_SHORT).show();
                }
                holder.textFoodNo.setText(String.valueOf(tempFoodNo));

            }
        });
*/


    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView textFoodName, textFoodNo;
        ImageView foodImage, deleteImage;
        Button minBtn, plusBtn;
        int position, foodURL, foodNo;
        String userName, foodName, foodPrice;

        public CartViewHolder(@NonNull View itemView/*, AdapterView.OnItemClickListener */) {
            super(itemView);
            textFoodName = itemView.findViewById(R.id.txtFoodName);
            textFoodNo = itemView.findViewById(R.id.txtItemNo);
            foodImage = itemView.findViewById(R.id.RV_CartImg);
            minBtn = itemView.findViewById(R.id.cartMinBtn);
            plusBtn = itemView.findViewById(R.id.cartPlusBtn);
            deleteImage = itemView.findViewById(R.id.deleteIcon);

            deleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    position = getAbsoluteAdapterPosition();
                    Boolean deleteSuccess = db.deleteUserData(foodName, position);

                    if(deleteSuccess){
                        Toast.makeText(context, "Delete Success, please exit cart to refresh", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(context, "Delete failed", Toast.LENGTH_SHORT).show();
                    }
                    //notifyItemRemoved(position);
                    //listener.removeCartItem(position);
                }
            });

            minBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Boolean updateSuccess;
                    foodNo = Integer.parseInt(textFoodNo.getText().toString());

                    if(foodNo < 2)
                    {
                        Toast.makeText(context, "Minimum Quantity is 1", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        foodNo--;
                        updateSuccess = db.updateUserData(userName, foodName, foodNo, foodURL, foodPrice);
                        if (updateSuccess) {
                            Toast.makeText(context, "decreased amount", Toast.LENGTH_SHORT).show();
                            //notifyItemRemoved(position);
                            //holder.textFoodNo.setText(String.valueOf(foodNo));
                        } else {
                            Toast.makeText(context, "decrease failed", Toast.LENGTH_SHORT).show();
                        }
                        textFoodNo.setText(String.valueOf(foodNo));
                    }
                }
            });

            plusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Boolean updateSuccess;
                    foodNo = Integer.parseInt(textFoodNo.getText().toString());

                    foodNo++;
                    updateSuccess = db.updateUserData(userName, foodName, foodNo, foodURL, foodPrice);
                    if (updateSuccess) {
                        Toast.makeText(context, "increased amount", Toast.LENGTH_SHORT).show();
                        //holder.textFoodNo.setText(String.valueOf(foodNo));
                    } else {
                        Toast.makeText(context, "increase failed", Toast.LENGTH_SHORT).show();
                    }
                    textFoodNo.setText(String.valueOf(foodNo));

                }
            });
        }


    }
}
