package com.example.shopper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    //initialize constants for DB version and name
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "shopper.db";

    //intialize constants for shopping list table
    private static final String TABLE_SHOPPING_LIST = "shoppinglist";
    private static final String COLUMN_LIST_ID = "_id";
    private static final String COLUMN_LIST_NAME = "name";
    private static final String COLUMN_LIST_STORE = "store";
    private static final String COLUMN_LIST_DATE = "date";

    public DBHandler (Context context, SQLiteDatabase.CursorFactory factory){
        // call superclass constructor
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Define string for create statement for shopping list table
        String query = "CREATE TABLE " + TABLE_SHOPPING_LIST + "("  +
                COLUMN_LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LIST_NAME + " TEXT, " +
                COLUMN_LIST_STORE + " TEXT, " +
                COLUMN_LIST_DATE + " TEXT " +
                ");";

        // execute the create statement
        sqLiteDatabase.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        // execute a drop statment that drops the shopping list table from the database
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPPING_LIST);

        // call method that creates the table
        onCreate(sqLiteDatabase);
    }

    public void addShoppingList (String name, String store, String date){

        //get writable reference to shopper database
        SQLiteDatabase db = getWritableDatabase();

        //initalize an empty ContentValues object
        ContentValues values = new ContentValues();

        // put key value pairs in the ContentValues object
        // the key must be the name of a column and the value
        // is the value to be inserted in the column
        values.put(COLUMN_LIST_NAME, name);
        values.put(COLUMN_LIST_STORE, store);
        values.put(COLUMN_LIST_DATE, date);

        //insert values into the shopping list table
        db.insert(TABLE_SHOPPING_LIST, null, values);

        //close the reference to the shopper database
        db.close();
    }

    public Cursor getShoppingLists(){

        SQLiteDatabase db = getWritableDatabase();


        return db.rawQuery("SELECT * FROM " + TABLE_SHOPPING_LIST, null);
    }

    public String getShoppingListName(int id) {

        //get writable reference to shopper database
        SQLiteDatabase db = getWritableDatabase();

        String dbString = "";

        //create sql string that will get the shopping list name
        String query = "SELECT * FROM " + TABLE_SHOPPING_LIST +
                " WHERE " + COLUMN_LIST_ID + " = " + id;

        //execute select statement and store result in cursor
        Cursor cursor = db.rawQuery(query, null);

        //move to the first row in the cursor
        cursor.moveToFirst();

        //check to make sure there's a shopping list name value
        if (cursor.getString(cursor.getColumnIndex("name")) != null) {
            //store it in the String that will be returned by the method
            dbString = cursor.getString(cursor.getColumnIndex("name"));
        }

        db.close();

        return dbString;
    }
}
