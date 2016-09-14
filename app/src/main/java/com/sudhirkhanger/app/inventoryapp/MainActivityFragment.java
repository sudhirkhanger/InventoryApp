package com.sudhirkhanger.app.inventoryapp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sudhirkhanger.app.inventoryapp.model.ProductContract;

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

        TextView textView = (TextView) rootView.findViewById(R.id.textview);

        ContentResolver resolver = getActivity().getContentResolver();
        Cursor cursor =
                resolver.query(ProductContract.ProductEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_NAME));
                    Log.d(LOG_TAG, "An item found with name: " + name);
                } while (cursor.moveToNext());
            }
        }

        if (cursor != null)
            cursor.close();

        return rootView;
    }
}
