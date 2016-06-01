package DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.Date;

import DatabaseHandler.DatabaseManagement;

/**
 * Created by aticus on 31-05-2016.
 */
public class DatabaseAdapter {
    private static final String TABLE_NAME = "ACCOUNT";
    private static final String Column_amount = "_amount";
    private String Column_type = "_type";
    private short Credit = 0;
    private short Debit = 1;
    ContentValues contentValues;
    SQLiteDatabase pointer;
    String temporary = "";


    public Context context;

    public DatabaseAdapter(Context context) {
        this.context = context;
    }

    public void insert(String amount) {
        DatabaseManagement db = new DatabaseManagement(context);
        pointer = db.getWritableDatabase();
        contentValues = new ContentValues();
        char[] transaction = amount.toCharArray();

        for (char x : transaction)
        {
            contentValues.put(Column_type, Credit);
            if (x != '+' && x != '-')
                temporary = temporary + x;
            if (x == '-' && !temporary.matches("")) {
                int f = decisionInsertion(Debit, temporary);
                if (f == 1)
                    break;

            }


            if (x == '+' && !temporary.matches("")) {
                int f = decisionInsertion(Credit, temporary);
                if (f == 1)
                    break;
            }

        }


    }

    int decisionInsertion(short type, String temp) {
        contentValues.put(Column_amount, temp);
        long flag = pointer.insert(TABLE_NAME, null, contentValues);
        if (flag == -1) {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            return 1;

        } else {
            Toast.makeText(context, "Data Inserted", Toast.LENGTH_SHORT).show();
            this.temporary = "";
            contentValues.clear();
            if (type == 1)
                type = 0;
            else
                type = 1;
            contentValues.put(Column_type, type);
            return 0;


        }
    }

}
