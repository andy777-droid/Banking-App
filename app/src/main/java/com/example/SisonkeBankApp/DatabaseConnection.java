package com.example.SisonkeBankApp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseConnection extends SQLiteOpenHelper {

    SQLiteDatabase sql = this.getWritableDatabase();

    public DatabaseConnection(@Nullable Context context) {
        super(context, "Auth.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table Users (Email text primary key, FirstName text, LastName text, Password text, Mobile text, Gender text, Current real, Savings real)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists Users");
    }

    public Boolean addUser(String email, String fname, String lname, String psw, String numb, String gender) {
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("Email", email);
        content.put("FirstName", fname);
        content.put("LastName", lname);
        content.put("Password", psw);
        content.put("Mobile", numb);
        content.put("Gender", gender);
        content.put("Current", 6000);
        content.put("Savings", 2500);
        long insert = mydb.insert("Users", null, content);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }

    }

    public String getUserDetails(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Users", null);
        String uname, psw;
        psw = "error";
        if (cursor.moveToFirst()) {
            do {
                uname = cursor.getString(0);
                if (uname.equals(user)) {
                    String f = cursor.getString(1);
                    String l = cursor.getString(2);
                    psw = cursor.getString(3);
                    String m = cursor.getString(4);
                    String g = cursor.getString(5);
                    Double c = cursor.getDouble(6);
                    Double s = cursor.getDouble(7);

                    User obj = new User(uname, f, l, psw, m, g, c, s);
                    break;
                }
            } while (cursor.moveToNext());
        }
        return psw;
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase check = this.getReadableDatabase();
        Cursor cursor = check.rawQuery("select * from Users where Email = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean updateBalance(double curr, double sav, double amt, String account, String mail) {
        double savings, current, amount;
        savings = sav;
        current = curr;
        amount = amt;

        if (account.equals("Current To Savings")) {
            if (amount <= current) {
                current = current - amount;
                savings = savings + amount;
            } else {
                return false;
            }
        } else {
            if (amount <= savings) {
                savings = savings - amount;
                current = current + amount;
            } else {
                return false;
            }
        }

        String query = "UPDATE Users SET Current = " + current + ", Savings = " + savings + " WHERE Email = '" + mail + "'";
        sql.execSQL(query);
        User.setCurrent(current);
        User.setSavings(savings);
        return true;
    }


}
