package DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.aticus.optionone.CreditFragment;
import com.example.aticus.optionone.MyListItem;

import java.util.Date;

import DatabaseHandler.DatabaseManagement;

/**
 * Created by aticus on 31-05-2016.
 */
public class DatabaseAdapter {
    private static final String TABLE_NAME = "ACCOUNT";
    private static final String Column_id = "_id";
    private static final String Column_amount = "_amount";
    private static final String Column_date = "_date";
    private static String Column_type = "_type";
    private short Credit = 0;
    private short Debit = 1;
    ContentValues contentValues;
    static SQLiteDatabase pointer;
    String temporary = "";
    long flag;
    char[] transaction;


    public Context context;
    private DatabaseManagement db;


    public DatabaseAdapter(Context context) {
        this.context = context;
        db = new DatabaseManagement(context);
    }



    public void insert(String amount) {

        pointer = db.getWritableDatabase();
        contentValues = new ContentValues();
        transaction = amount.toCharArray();


        for (int k = 0; k < transaction.length; k++) {
            switch (transaction[k]) {
                case '+':
                    contentValues.put(Column_type, Credit);
                    k = EntrySeperator(++k);
                    break;
                case '-':
                    contentValues.put(Column_type, Debit);
                    k = EntrySeperator(++k);
                    break;
                default:
                    contentValues.put(Column_type, Credit);
                    k = EntrySeperator(k);
                    break;
            }
        }
        if (flag == -1)
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "data inserted", Toast.LENGTH_SHORT).show();
            CreditFragment.updateAdapter();


    }

    int EntrySeperator(int i) {
        String temp = "";
        while (transaction[i] != '+' && transaction[i] != '-') {
            temp = temp + transaction[i];
            if (i == transaction.length - 1)
                break;
            i++;
        }
        contentValues.put(Column_amount, temp);
        flag = pointer.insert(TABLE_NAME, null, contentValues);
        contentValues.clear();
        if (flag == -1 || transaction.length - 1 == i)
            return transaction.length + 1;
        return i - 1;
    }

    public Cursor showCreditRecord() {

        pointer = db.getWritableDatabase();
        String[] columns = {Column_id, Column_amount, Column_date, Column_type};

        Cursor c = pointer.query(TABLE_NAME, columns,TABLE_NAME+"."+Column_type+"="+Credit, null, null, null, Column_id + " desc");
        String s = "";
        return c;
    }

    public Cursor showDebitRecord() {

        pointer = db.getWritableDatabase();
        String[] columns = {Column_id, Column_amount, Column_date, Column_type};

        Cursor c = pointer.query(TABLE_NAME, columns,TABLE_NAME+"."+Column_type+"="+Debit, null, null, null, Column_id + " desc");
        String s = "";
        return c;
    }


}

