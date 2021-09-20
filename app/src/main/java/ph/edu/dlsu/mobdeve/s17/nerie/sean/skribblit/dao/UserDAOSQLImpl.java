package ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.nerie.sean.skribblit.model.User;

public class UserDAOSQLImpl implements UserDAO {

    private SQLiteDatabase database;
    private UserDatabase userdatabase;

    public UserDAOSQLImpl(Context context){
        userdatabase = new UserDatabase(context);
    }

    @Override
    public long addUser(User user) {
        ContentValues values = new ContentValues();

        values.put(UserDatabase.USERS_ID, user.getId());
        values.put(UserDatabase.USERS_NAME, user.getName());
        values.put(UserDatabase.USERS_DP, user.getUserImageId());
        values.put(UserDatabase.USERS_SCORE, user.getHighscore());

        database = userdatabase.getWritableDatabase();

        long id = database.insert(UserDatabase.TABLE_USERS,
                null, values);

        if(database != null){
            userdatabase.close();
        }

        return id;
    }

    @Override
    public ArrayList<User> getUsers() {
        ArrayList<User> result = new ArrayList<>();
        String[] columns = {UserDatabase.USERS_ID,
                UserDatabase.USERS_NAME,
                UserDatabase.USERS_DP,
                UserDatabase.USERS_SCORE};

        database = userdatabase.getReadableDatabase();

        Cursor cursor = database.query(UserDatabase.TABLE_USERS,
                columns,
                null,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            User temp = new User();
            temp.setId(cursor.getInt(0));
            temp.setName(cursor.getString(1));
            temp.setUserImageId(cursor.getInt(2));
            temp.setHighscore(cursor.getInt(3));
            result.add(temp);
            cursor.moveToNext();
        }

        if(cursor != null){
            cursor.close();
        }

        if(database != null){
            userdatabase.close();
        }

        return result;
    }

    @Override
    public User getUser(int userid) {
        User user = null;

        String query = "SELECT * from " + UserDatabase.TABLE_USERS
                + " where " + UserDatabase.USERS_ID + " = " + userid;

        Cursor cursor = null;

        database = userdatabase.getReadableDatabase();

        try{
            cursor = database.rawQuery(query, null);

            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(UserDatabase.USERS_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(UserDatabase.USERS_NAME)));
                user.setUserImageId(cursor.getInt(cursor.getColumnIndex(UserDatabase.USERS_DP)));
                user.setHighscore(cursor.getInt(cursor.getColumnIndex(UserDatabase.USERS_SCORE)));
                cursor.moveToNext();
            }

        }catch (SQLException e){
            return null;
        }

        if(cursor != null){
            cursor.close();
        }

        if(database != null){
            userdatabase.close();
        }
        return user;

    }

    @Override
    public int updateUser(User user) {
        ContentValues values = new ContentValues();

        values.put(UserDatabase.USERS_ID, user.getId());
        values.put(UserDatabase.USERS_NAME, user.getName());
        values.put(UserDatabase.USERS_DP, user.getUserImageId());
        values.put(UserDatabase.USERS_SCORE, user.getHighscore());

        database = userdatabase.getWritableDatabase();

        int records = database.update(UserDatabase.TABLE_USERS,
                values,
                UserDatabase.USERS_ID + " = " + user.getId(),
                null);

        if (database != null){
            userdatabase.close();
        }

        return records;
    }

    @Override
    public int deleteUser(int userid) {

        database = userdatabase.getWritableDatabase();

        int records = database.delete(UserDatabase.TABLE_USERS,
                UserDatabase.USERS_ID + " = " + userid,
                null);

        if (database != null){
            userdatabase.close();
        }

        return records;
    }
}
