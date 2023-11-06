package curtin.edu.eatapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "UserData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table UserOrder(userName TEXT, foodName TEXT primary key, amount INT, imageUrl INT, price TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists UserOrder");
    }

    public Boolean insertUserData(/*int orderFoodNo, */String userName, String foodName, int amount, int imageUrl, String foodPrice)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put("orderFoodNo", orderFoodNo);
        contentValues.put("userName", userName);
        contentValues.put("foodName", foodName);
        contentValues.put("amount", amount);
        contentValues.put("imageUrl", imageUrl);
        contentValues.put("price", foodPrice);

        Cursor cursor = db.rawQuery("Select * from UserOrder where foodName = ?", new String[] {foodName});
        if(cursor.getCount() > 0) { //data already exists
            return updateUserData(userName, foodName, amount, imageUrl, foodPrice);

        }
        else { //new data
            long result = db.insert("UserOrder", null, contentValues);
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }

    }

    public Boolean updateUserData(/*int orderFoodNo, */String userName, String foodName, int amount, int imageUrl, String foodPrice)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put("orderFoodNo", orderFoodNo);
        contentValues.put("userName", userName);
        contentValues.put("amount", amount);
        contentValues.put("imageUrl", imageUrl);
        contentValues.put("price", foodPrice);
        Cursor cursor = db.rawQuery("Select * from UserOrder where foodName = ?", new String[] {foodName});
        if(cursor.getCount() > 0) { //if the cursor has data (data already exists)
            long result = db.update("UserOrder", contentValues, "foodName=?", new String[]{foodName});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else
        {
            return false;
        }

    }

    public Boolean deleteUserData(String foodName, int position)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = db.rawQuery("Select * from UserOrder where foodName = ?", new String[] {foodName});
        if(cursor.getCount() > 0) { //if the cursor has data (data already exists)
            long result = db.delete("UserOrder",  "foodName=?", new String[]{foodName});

            if (result == -1) {
                return false;
            } else {
                //notifyItemRemoved(position);
                return true;
            }
        }
        else
        {
            return false;
        }

    }

    public Cursor getUserData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from UserOrder", null);
        return cursor;
    }

    public Cursor getData(String sql)
    {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql,null);
    }
}
