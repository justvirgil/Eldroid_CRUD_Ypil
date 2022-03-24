package com.example.androidintents.crudypil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DPhelper extends SQLiteOpenHelper {
    public DPhelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("Create Table Userdetails(idnumber TEXT primary key,firstname TEXT, lastname TEXT, address TEXT, phonenumber TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop TABLE if exists Userdetails");
    }

    public Boolean insertuserdata(String idnumber, String firstname, String lastname, String address, String phonenumber, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idnumber",idnumber);
        contentValues.put("firstname",firstname);
        contentValues.put("lastname",lastname);
        contentValues.put("address",address);
        contentValues.put("phonenumber",phonenumber);
        contentValues.put("password",password);
        long result = DB.insert("Userdetails",null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updateuserdata(String idnumber,String firstname, String lastname, String address, String phonenumber, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstname", firstname);
        contentValues.put("lastname", lastname);
        contentValues.put("address", address);
        contentValues.put("phonenumber", phonenumber);
        contentValues.put("password", password);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where idnumber = ?", new String[]{idnumber});
        if (cursor.getCount() > 0) {

            long result = DB.update("Userdetails", contentValues, "idnumber=?", new String[]{idnumber});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

        public Boolean deletedata(String idnumber){
            SQLiteDatabase DB = this.getWritableDatabase();
            Cursor cursor = DB.rawQuery("Select * from Userdetails where idnumber = ?", new String[]{idnumber});
            if (cursor.getCount() > 0) {

                long result = DB.delete("Userdetails","idnumber=?", new String[]{idnumber});
                if (result == -1) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }

    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails",null);
        return cursor;
        }
    }

