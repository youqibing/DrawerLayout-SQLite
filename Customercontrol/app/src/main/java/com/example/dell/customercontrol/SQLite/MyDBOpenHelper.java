package com.example.dell.customercontrol.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dell on 2016/7/4.
 */

public class MyDBOpenHelper extends SQLiteOpenHelper{

    public static final String ACCOUNT="account";
    public static final String ACCOUNT_ID="account_id";
    public static final String ACCOUNT_TIME="account_time";
    public static final String ACCOUNT_MONEY = "money";
    public static final String ACCOUNT_FLOORNUMBER="floor_number";
    public static final String ACCOUNT_ROOMNUMBER="room_number";
    public static final String ACCOUNT_ISRESERVE="isreserve";

    public MyDBOpenHelper(Context context) {
        super(context, "data", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS"+" "+ACCOUNT+
                        "("+
                        ACCOUNT_ID+" "+"INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        ACCOUNT_TIME+" "+"varchar(10),"+
                        ACCOUNT_MONEY+" "+"float,"+
                        ACCOUNT_FLOORNUMBER+" "+"float,"+
                        ACCOUNT_ROOMNUMBER+" "+"float,"+
                        ACCOUNT_ISRESERVE+" "+"BOOLEAN"+
                        ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
