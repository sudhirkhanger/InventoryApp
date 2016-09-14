package com.sudhirkhanger.app.inventoryapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sudhirkhanger.app.inventoryapp.model.Product;
import com.sudhirkhanger.app.inventoryapp.model.ProductContract;
import com.sudhirkhanger.app.inventoryapp.model.ProductDbHelper;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String LOG_TAG = MainActivityFragment.class.getSimpleName();

    SQLiteOpenHelper mSQLiteOpenHelper;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mSQLiteOpenHelper = new ProductDbHelper(rootView.getContext());

        Utility.addProduct(mSQLiteOpenHelper.getReadableDatabase(),
                new Product(
                        "name",
                        8.99,
                        "image",
                        5,
                        4,
                        "supplier"));

        Utility.addProduct(mSQLiteOpenHelper.getReadableDatabase(),
                new Product(
                        "name1",
                        8.99,
                        "image",
                        5,
                        4,
                        "supplier"));

        String countQuery = "SELECT  * FROM " + ProductContract.ProductEntry.TABLE_NAME;
        SQLiteDatabase db = mSQLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null) {
            Log.d(LOG_TAG, "Items in db: " + cursor.getCount());
        }
        cursor.close();

        return rootView;
    }
}
