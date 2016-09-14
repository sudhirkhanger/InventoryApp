package com.sudhirkhanger.app.inventoryapp.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ProductDbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = ProductDbHelper.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "product.db";

    public ProductDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATION_PRODUCT_TABLE =
                "CREATE TABLE " + ProductContract.ProductEntry.TABLE_NAME + " (" +
                        ProductContract.ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        ProductContract.ProductEntry.COLUMN_NAME + " TEXT UNIQUE NOT NULL, " +
                        ProductContract.ProductEntry.COLUMN_PRICE + " REAL NOT NULL, " +
                        ProductContract.ProductEntry.COLUMN_IMAGE + " TEXT NOT NULL, " +
                        ProductContract.ProductEntry.COLUMN_QUANTITY + " INTEGER NOT NULL, " +
                        ProductContract.ProductEntry.COLUMN_SOLD + " INTEGER NOT NULL, " +
                        ProductContract.ProductEntry.COLUMN_SUPPLIER + " TEXT NOT NULL" +
                        " );";

        Log.d(LOG_TAG, "onCreate: " + SQL_CREATION_PRODUCT_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATION_PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ProductContract.ProductEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void deleteDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }
}
