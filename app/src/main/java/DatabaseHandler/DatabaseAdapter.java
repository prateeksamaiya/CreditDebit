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
    private static final String TABLE_NAME="ACCOUNT";
    private static final String Column_amount="_amount";
    public Context context;
    public DatabaseAdapter(Context context)
    {
        this.context=context;
    }

    public void insert(int amount)
    {
        DatabaseManagement db= new DatabaseManagement(context);
        SQLiteDatabase pointer = db.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(Column_amount, amount);
        long flag=pointer.insert(TABLE_NAME,null,contentValues);
        if(flag==-1)
        {
            Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(context,"Data Inserted",Toast.LENGTH_SHORT).show();

        }

    }
}
