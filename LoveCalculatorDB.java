package hr.horoskop.horoskop.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import hr.horoskop.horoskop.utils.AppConstants;

/**
 * Created by zoran on 31.08.2015..
 */
public class LoveCalculatorDB extends SQLiteOpenHelper {

    private static final String[] COLUMNS = {AppConstants.LOVE_ID, AppConstants.LOVE_CATEGORY, AppConstants.LOVE_TEXT};


    public LoveCalculatorDB(Context context) {
        super(context, AppConstants.DATABASE_NAME, null, AppConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL statement to create sign table
        String CREATE_LOVE_TABLE = "CREATE TABLE " + AppConstants.LOVE_TABLE + " (" +
                AppConstants.LOVE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AppConstants.LOVE_CATEGORY + " TEXT, " +
                AppConstants.LOVE_TEXT + " TEXT)";

        db.execSQL(CREATE_LOVE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS love");

        // create fresh books table
        this.onCreate(db);
    }

    public void addLove(Love love) {
        //1. Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        //2. Create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(AppConstants.LOVE_CATEGORY, love.getCategory());
        values.put(AppConstants.LOVE_TEXT, love.getText());
        //3. Insert
        db.insert(AppConstants.LOVE_TABLE, null, values);
        //4. Close
        db.close();
    }

    // Get All love
    public List<Love> getAllLove() {
        List<Love> loveList = new LinkedList<Love>();

        // 1. build the query
        String query = "SELECT  * FROM " + AppConstants.LOVE_TABLE;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Love love = null;
        if (cursor.moveToFirst()) {
            do {
                love = new Love();
                love.setId(Integer.parseInt(cursor.getString(0)));
                love.setCategory(cursor.getString(1));
                love.setText(cursor.getString(2));

                // Add book to books
                loveList.add(love);
            } while (cursor.moveToNext());
        }

        return loveList;
    }

    public void deleteLove(Love love) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(AppConstants.LOVE_TABLE, //table name
                AppConstants.LOVE_ID + " = ?",  // selections
                new String[]{String.valueOf(love.getId())}); //selections args

        // 3. close
        db.close();

        //log
        Log.d("delete Sign", love.toString());
    }

    public int updateLove(Love love) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();

        values.put(AppConstants.LOVE_CATEGORY, love.getCategory());
        values.put(AppConstants.LOVE_TEXT, love.getText());
        // 3. updating row
        int i = db.update(AppConstants.LOVE_TABLE, //table
                values, // column/value
                AppConstants.LOVE_ID + " = ?", // selections
                new String[]{String.valueOf(love.getId())}); //selection args

        // 4. close
        db.close();

        return i;
    }
}
