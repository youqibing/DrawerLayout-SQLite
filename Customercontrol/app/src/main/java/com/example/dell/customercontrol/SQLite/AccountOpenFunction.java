package com.example.dell.customercontrol.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2016/7/4.
 */

public class AccountOpenFunction {
    private Context context;
    private ContentValues contentValues;

    public MyDBOpenHelper dbOpenHelper;
    public SQLiteDatabase  sqLiteDatabase;

    public AccountOpenFunction(Context context){
        this.context=context;
        dbOpenHelper=new MyDBOpenHelper(context);
    }

    /**
     * 添加一条记录
     */
    public void add(String time,float money, float foolNumber,float roomNumber,Boolean isreserve){
        sqLiteDatabase=dbOpenHelper.getWritableDatabase();//写入数据权限
        if(sqLiteDatabase.isOpen()){
            contentValues = new ContentValues();

            contentValues.put("account_time",time);
            contentValues.put("money",money);
            contentValues.put("floor_number",foolNumber);
            contentValues.put("room_number",roomNumber);
            contentValues.put("isreserve",isreserve);

            sqLiteDatabase.insert("account",null,contentValues);
            sqLiteDatabase.close();
        }
    }

    /**
     * 删除一条记录
     */
    public void delete(String account_id){
        sqLiteDatabase =dbOpenHelper.getWritableDatabase();
        if(sqLiteDatabase.isOpen()){
            sqLiteDatabase.delete("account","account_id=?",new String[]{account_id});
            sqLiteDatabase.close();
        }
    }

    /**
     * 数据库的更新，每次删除之后需要更新一下
     */
    public void updata(String account_id,String time,float money, float foolNumber,float roomNumber,Boolean isreserve){
        sqLiteDatabase =dbOpenHelper.getWritableDatabase();
        if(sqLiteDatabase.isOpen()){
            contentValues.put("account_time",time);
            contentValues.put("money",money);
            contentValues.put("floor_number",foolNumber);
            contentValues.put("room_number",roomNumber);
            contentValues.put("isreserve",isreserve);

            sqLiteDatabase.update("account",contentValues,"account_id",new String[]{account_id});
            sqLiteDatabase.close();
        }
    }

    /**
     * 查询特定时间段的信息
     */
    public List<Account> findBySomeTime(String Sometime){
        List<Account> accounts = null;
        sqLiteDatabase = dbOpenHelper.getReadableDatabase();
        if(sqLiteDatabase.isOpen()) {
            Cursor cursor = sqLiteDatabase.rawQuery("select * from account where account_time like ?", new String[]{Sometime});
            accounts = new ArrayList<Account>();
            while (cursor.moveToNext()) {
                Account account = new Account();
                int id = cursor.getInt(cursor.getColumnIndex("account_id"));
                account.setId(id);
                String time = cursor.getString(cursor.getColumnIndex("account_time"));
                account.setTime(time);
                float money = Float.parseFloat(cursor.getString(cursor.getColumnIndex("money")));
                account.setMoney(money);
                float foolrNumber = Float.parseFloat(cursor.getString(cursor.getColumnIndex("floor_number")));
                account.setFoolNumber(foolrNumber);
                float roomNumber = Float.parseFloat(cursor.getString(cursor.getColumnIndex("room_number")));
                account.setRoomNumber(roomNumber);
                long isReserve = cursor.getLong(cursor.getColumnIndex("isReserve"));
                if (isReserve == 0) {
                    account.setIsReserve(false);
                } else {
                    account.setIsReserve(true);
                }
                accounts.add(account);
            }

            cursor.close();
            sqLiteDatabase.close();
        }
        return accounts;
    }

    /**
     * 查询特定房间的信息
     */
    public List<Account> findBySomeRoom(String Somefool,String Someroom){
        List<Account> accounts = null;
        sqLiteDatabase = dbOpenHelper.getReadableDatabase();
        if(sqLiteDatabase.isOpen()) {
            Cursor cursor = sqLiteDatabase.rawQuery("select * from account where floor_number =? and room_number like ?", new String[]{Somefool,Someroom});
            accounts = new ArrayList<Account>();
            while (cursor.moveToNext()) {
                Account account = new Account();
                int id = cursor.getInt(cursor.getColumnIndex("account_id"));
                account.setId(id);
                String time = cursor.getString(cursor.getColumnIndex("account_time"));
                account.setTime(time);
                float money = Float.parseFloat(cursor.getString(cursor.getColumnIndex("money")));
                account.setMoney(money);
                float foolrNumber = Float.parseFloat(cursor.getString(cursor.getColumnIndex("floor_number")));
                account.setFoolNumber(foolrNumber);
                float roomNumber = Float.parseFloat(cursor.getString(cursor.getColumnIndex("room_number")));
                account.setRoomNumber(roomNumber);
                long isReserve = cursor.getLong(cursor.getColumnIndex("isReserve"));
                if (isReserve == 0) {
                    account.setIsReserve(false);
                } else {
                    account.setIsReserve(true);
                }
                accounts.add(account);
            }

            cursor.close();
            sqLiteDatabase.close();
        }
        return accounts;
    }

    /**
     * 查询所有已预订的房间
     */
    public List<Account> fillAllReserve(){
        List<Account> accounts = null;
        sqLiteDatabase = dbOpenHelper.getReadableDatabase();
        if(sqLiteDatabase.isOpen()){
            Cursor cursor = sqLiteDatabase.rawQuery("select * from account where isreserve =1",null);
            accounts = new ArrayList<Account>();
            while (cursor.moveToNext()) {
                Account account = new Account();
                int id = cursor.getInt(cursor.getColumnIndex("account_id"));
                account.setId(id);
                String time = cursor.getString(cursor.getColumnIndex("account_time"));
                account.setTime(time);
                float money = Float.parseFloat(cursor.getString(cursor.getColumnIndex("money")));
                account.setMoney(money);
                float foolrNumber = Float.parseFloat(cursor.getString(cursor.getColumnIndex("floor_number")));
                account.setFoolNumber(foolrNumber);
                float roomNumber = Float.parseFloat(cursor.getString(cursor.getColumnIndex("room_number")));
                account.setRoomNumber(roomNumber);
                long isReserve = cursor.getLong(cursor.getColumnIndex("isreserve"));
                if (isReserve == 0) {
                    account.setIsReserve(false);
                } else {
                    account.setIsReserve(true);
                }
                accounts.add(account);
            }

            cursor.close();
            sqLiteDatabase.close();
        }
        return accounts;
    }


}
