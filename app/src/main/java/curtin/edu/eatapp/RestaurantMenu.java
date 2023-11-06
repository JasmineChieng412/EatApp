package curtin.edu.eatapp;

import android.os.Parcel;
import android.os.Parcelable;

public class RestaurantMenu implements Parcelable {

    String name;
    String price;
    Integer imageUrl;


    public RestaurantMenu(String name, String price, Integer imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    protected RestaurantMenu(Parcel in) {
        name = in.readString();
        price = in.readString();
        if (in.readByte() == 0) {
            imageUrl = null;
        } else {
            imageUrl = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(price);
        if (imageUrl == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(imageUrl);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RestaurantMenu> CREATOR = new Creator<RestaurantMenu>() {
        @Override
        public RestaurantMenu createFromParcel(Parcel in) {
            return new RestaurantMenu(in);
        }

        @Override
        public RestaurantMenu[] newArray(int size) {
            return new RestaurantMenu[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }
}

