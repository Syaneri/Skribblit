package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 1;

    //table names
    public static final String TABLE_USERS = "users";

    //column names
    public static final String USERS_ID = "id";
    public static final String USERS_NAME = "name";
    public static final String USERS_DP = "display_pic";
    public static final String USERS_SCORE = "high_score";
    public static final String USERS_LOBBY = "lobby";

    public static final String CREATE_USER_TABLE =
            "create table " + TABLE_USERS + " ( "
                    + USERS_ID + " integer primary key, "
                    + USERS_NAME + " text, "
                    + USERS_DP + " integer, "
                    + USERS_SCORE + " integer, "
                    + USERS_LOBBY + " text ); ";

    public UserDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
}
