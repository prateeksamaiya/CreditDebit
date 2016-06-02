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
    long flag;
    char[] transaction;


    public Context context;

    public DatabaseAdapter(Context context) {
        this.context = context;
    }

    public void insert(String amount) {
        DatabaseManagement db = new DatabaseManagement(context);
        pointer = db.getWritableDatabase();
        contentValues = new ContentValues();
        transaction = amount.toCharArray();

        for (int k=0;k<transaction.length;k++)
        {
            switch (transaction[k])
            {
                case '+':contentValues.put(Column_type,Credit);
                         k=EntrySeperator(++k);break;
                case '-':contentValues.put(Column_type,Debit);
                         k=EntrySeperator(++k);break;
                default:contentValues.put(Column_type,Credit);
                        k=EntrySeperator(k);break;
            }
        }
        if(flag==-1)
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context,"data inserted",Toast.LENGTH_SHORT).show();


    }

    int EntrySeperator(int i)
    {
        String temp="";
        while(transaction[i]!='+' && transaction[i]!='-') {
            temp=temp+transaction[i];
            if(i==transaction.length-1)
                break;
             i++;
        }
        contentValues.put(Column_amount,temp);
        flag=pointer.insert(TABLE_NAME,null,contentValues);
        Toast.makeText(context,temp+contentValues.get(Column_type),Toast.LENGTH_SHORT).show();
        contentValues.clear();
        if(flag==-1||transaction.length-1==i)
            return transaction.length+1;
        return i-1;
    }

}
