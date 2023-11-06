package curtin.edu.eatapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public class LogDBHelper extends SQLiteOpenHelper {

    public static final String DatabaseName = "Login.db";

    public LogDBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Users(userName TEXT primary key, password TEXT , email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists Users");
    }

    public Boolean insertUserData(String userName, String password, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userName", userName);
        contentValues.put("password", password);
        contentValues.put("email", email);
        long result = db.insert("Users", null, contentValues);
        if(result==-1) {
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean checkusername(String userName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Users where userName = ?", new String[] {userName});
        if(cursor.getCount() > 0) { //if the cursor has data (data already exists)
            return true;
        }
        else
        {
            return false;
        }
    }


    public Boolean checkemail(String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Users where email = ?", new String[] {email});
        if(cursor.getCount() > 0) { //if the cursor has data (data already exists)
                return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean checkemailpassword(String email, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Users where email = ? and password = ?", new String[] {email, password});
        if(cursor.getCount() > 0) { //if the cursor has data (data already exists)
            return true;
        }
        else
        {
            return false;
        }
    }
}
