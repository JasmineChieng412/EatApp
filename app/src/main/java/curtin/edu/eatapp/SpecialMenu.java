package curtin.edu.eatapp;

import android.os.Parcel;
import android.os.Parcelable;

public class SpecialMenu implements Parcelable {

    String name;
    String price;
    Integer imageUrl;

    public SpecialMenu(String name, String price, Integer imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    protected SpecialMenu(Parcel in) {
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

    public static final Creator<SpecialMenu> CREATOR = new Creator<SpecialMenu>() {
        @Override
        public SpecialMenu createFromParcel(Parcel in) {
            return new SpecialMenu(in);
        }

        @Override
        public SpecialMenu[] newArray(int size) {
            return new SpecialMenu[size];
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

