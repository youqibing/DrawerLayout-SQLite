package com.example.dell.customercontrol.SQLite;

/**
 * Created by dell on 2016/7/4.
 */

public class Account {
    private int id;
    private String time;
    private float money;
    private float fool_number;
    private float room_number;
    private Boolean isReserve;

    public int getId(){
        return id;
    }

    public void setId( int id){
        this.id=id;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time=time;
    }

    public Float getMoney(){
        return money;
    }

    public void setMoney(float money){
        this.money=money;
    }

    public Float getFoolNumber(){
        return fool_number;
    }

    public void setFoolNumber(Float fool_number){
        this.fool_number=fool_number;
    }

    public Float getRoomNmber(){
        return  room_number;
    }

    public void setRoomNumber(Float room_number){
        this.room_number=room_number;
    }

    public Boolean getIsReserve(){
        return  getIsReserve();
    }

    public void setIsReserve(Boolean isReserve){
        this.isReserve = isReserve;
    }


}
