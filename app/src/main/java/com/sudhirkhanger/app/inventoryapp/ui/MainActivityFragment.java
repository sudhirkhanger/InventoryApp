package com.sudhirkhanger.app.inventoryapp.ui;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sudhirkhanger.app.inventoryapp.R;
import com.sudhirkhanger.app.inventoryapp.adapter.ProductCursorAdapter;
import com.sudhirkhanger.app.inventoryapp.model.ProductContract;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String LOG_TAG = MainActivityFragment.class.getSimpleName();
    private ContentResolver mContentResolver;
    private String[] mProjection;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mContentResolver = getActivity().getContentResolver();
        mProjection = new String[]{
                ProductContract.ProductEntry._ID,
                ProductContract.ProductEntry.COLUMN_NAME,
                ProductContract.ProductEntry.COLUMN_PRICE,
                ProductContract.ProductEntry.COLUMN_IMAGE,
                ProductContract.ProductEntry.COLUMN_QUANTITY,
                ProductContract.ProductEntry.COLUMN_SOLD,
                ProductContract.ProductEntry.COLUMN_SUPPLIER,
        };

        ListView listView = (ListView) rootView.findViewById(R.id.product_listview);
        ProductCursorAdapter productCursorAdapter = new ProductCursorAdapter(
                getContext(), queryAll());
        listView.setAdapter(productCursorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Cursor cursor = (Cursor) adapterView.getItemAtPosition(pos);
                if (cursor != null) {
                    ((Callback) getActivity()).onItemSelected(
                            ProductContract.ProductEntry.buildProductUri(
                                    cursor.getLong(cursor.getColumnIndex(ProductContract.ProductEntry._ID))));
                }
            }
        });

//        ContentResolver resolver = getActivity().getContentResolver();
//
//        ContentValues values = new ContentValues();
//
//        values.put(ProductContract.ProductEntry.COLUMN_NAME, "name 4");
//        values.put(ProductContract.ProductEntry.COLUMN_PRICE, "100");
//        values.put(ProductContract.ProductEntry.COLUMN_IMAGE, "cpimage");
//        values.put(ProductContract.ProductEntry.COLUMN_QUANTITY, "10");
//        values.put(ProductContract.ProductEntry.COLUMN_SOLD, "0");
//        values.put(ProductContract.ProductEntry.COLUMN_SUPPLIER, "cpsupplier");
//
//        resolver.insert(ProductContract.ProductEntry.CONTENT_URI, values);

        logQueryResults(queryAll());

        return rootView;
    }

    public interface Callback {
        void onItemSelected(Uri productUri);
    }

    private Cursor queryAll() {
        return mContentResolver.query(
                ProductContract.ProductEntry.CONTENT_URI,
                mProjection,
                null,
                null,
                null);
    }

    private void logQueryResults(Cursor cursor) {
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_NAME));
                    double price = cursor.getDouble(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRICE));
                    Log.d(LOG_TAG, "Name: " + name + " Price: " + price);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
    }
}
