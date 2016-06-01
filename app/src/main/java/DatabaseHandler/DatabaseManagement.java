package DatabaseHandler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by aticus on 30-05-2016.
 */
public class DatabaseManagement extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="CREDITDEBIT";
    private static final int DATABASE_VERSION=2;
    private static final String TABLE_NAME="ACCOUNT";
    private static final String Column_id="_id";
    private static final String Column_amount="_amount";
    private static final String Column_date="_date";
    private String Column_type="_type";
    private Context context;

    public DatabaseManagement(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                Column_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                Column_amount + " INTEGER," +
                Column_date + " DATETIME DEFAULT CURRENT_TIMESTAMP," +
                Column_type + " TINYINT" +
                ");";
        //String query ="CREATE TABLE  "+TABLE_NAME+ "("+Column_id +" INTEGER PRIMARY KEY AUTOINCREMENT,"+Column_amount+"  INTEGER,"+Column_date+"  DATETIME DEFAULT CURRENT_TIMESTAMP);";
        db.execSQL(query);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
        Toast.makeText(context,"upgrade ran",Toast.LENGTH_SHORT).show();





    }
}
