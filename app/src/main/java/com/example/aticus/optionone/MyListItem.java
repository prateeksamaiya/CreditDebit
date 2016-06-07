package com.example.aticus.optionone;

import android.database.Cursor;

import java.sql.Date;

/**
 * Created by aticus on 03-06-2016.
 */
public class MyListItem{
    private String amount;
    private String dateTime;
    private int _id;
    private short type;

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public void set_id(int _id) {

        this._id = _id;
    }

    public String getAmount() {
        return amount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public int get_id() {
        return _id;
    }

    public static MyListItem fromCursor(Cursor cursor) {
        //TODO return your MyListItem from cursor.
        MyListItem myListItem=new MyListItem();
        myListItem._id=cursor.getInt(0);
        myListItem.amount=cursor.getString(1);
        myListItem.dateTime=cursor.getString(2);
        myListItem.type=cursor.getShort(3);
        return myListItem;
    }
}
