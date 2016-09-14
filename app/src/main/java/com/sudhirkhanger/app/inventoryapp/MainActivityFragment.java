package com.sudhirkhanger.app.inventoryapp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String LOG_TAG = MainActivityFragment.class.getSimpleName();

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ContentResolver resolver = getActivity().getContentResolver();

        ContentValues values = new ContentValues();
//
//        String pro_name = "name " + 3;
//
//        values.put(ProductContract.ProductEntry.COLUMN_NAME, pro_name);
//        values.put(ProductContract.ProductEntry.COLUMN_PRICE, "100");
//        values.put(ProductContract.ProductEntry.COLUMN_IMAGE, "cpimage");
//        values.put(ProductContract.ProductEntry.COLUMN_QUANTITY, "10");
//        values.put(ProductContract.ProductEntry.COLUMN_SOLD, "0");
//        values.put(ProductContract.ProductEntry.COLUMN_SUPPLIER, "cpsupplier");
//
//        resolver.insert(ProductContract.ProductEntry.CONTENT_URI, values);

//        resolver.delete(ProductContract.ProductEntry.CONTENT_URI,
//                ProductContract.ProductEntry.COLUMN_NAME + " = ?",
//                new String[]{"name 0"});

//        values.clear();
//        values.put(ProductContract.ProductEntry.COLUMN_NAME, "name modified 1");
////
//        Uri uri = ContentUris.withAppendedId(ProductContract.ProductEntry.CONTENT_URI, 4);
//
//        resolver.update(uri,
//        resolver.update(ProductContract.ProductEntry.CONTENT_URI,
//                values,
//                ProductContract.ProductEntry.COLUMN_NAME + " = ?",
//                new String[]{"name 2"});
//
//        Cursor cursor =
//                resolver.query(ProductContract.ProductEntry.CONTENT_URI,
//                        null,
//                        null,
//                        null,
//                        null);
//
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                do {
//                    String name = cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_NAME));
//                    long id = cursor.getLong(cursor.getColumnIndex(ProductContract.ProductEntry._ID));
//                    Log.d(LOG_TAG, "An item found with name: " + String.valueOf(id) + " " + name);
//                } while (cursor.moveToNext());
//            }
//        } else {
//            Log.d(LOG_TAG, "cursor is null");
//        }
//
//        if (cursor != null)
//            cursor.close();

        return rootView;
    }
}
