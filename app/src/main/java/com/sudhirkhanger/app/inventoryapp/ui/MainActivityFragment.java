package com.sudhirkhanger.app.inventoryapp.ui;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.sudhirkhanger.app.inventoryapp.R;
import com.sudhirkhanger.app.inventoryapp.adapter.ProductCursorAdapter;
import com.sudhirkhanger.app.inventoryapp.model.ProductContract;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String LOG_TAG = MainActivityFragment.class.getSimpleName();
    private static final String[] PRODUCT_COLUMNS = {
            ProductContract.ProductEntry._ID,
            ProductContract.ProductEntry.COLUMN_NAME,
            ProductContract.ProductEntry.COLUMN_PRICE,
            ProductContract.ProductEntry.COLUMN_IMAGE,
            ProductContract.ProductEntry.COLUMN_QUANTITY,
            ProductContract.ProductEntry.COLUMN_SOLD,
            ProductContract.ProductEntry.COLUMN_SUPPLIER,
    };

    private static final int PRODUCT_LOADER = 0;
    private ProductCursorAdapter mProductCursorAdapter;
    private ListView mListView;
    private View mEmptyView;

    /*
     * To pass the product uri from
     * fragment to activity and
     * to details activity to its
     * fragment
     */
    public interface Callback {
        void onItemSelected(Uri productUri);
    }

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mListView = (ListView) rootView.findViewById(R.id.product_listview);
        mEmptyView = rootView.findViewById(R.id.emptyView);

        mProductCursorAdapter = new ProductCursorAdapter(getContext(), null);

        mListView.setAdapter(mProductCursorAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

        Button button = (Button) rootView.findViewById(R.id.add_product_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddProductActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(PRODUCT_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(LOG_TAG, "onCreateLoader()");
        return new CursorLoader(
                getActivity(),
                ProductContract.ProductEntry.CONTENT_URI,
                PRODUCT_COLUMNS,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d(LOG_TAG, "onLoadFinished()");
        if (data.getCount() == 0) {
            mListView.setEmptyView(mEmptyView);
        } else {
            mProductCursorAdapter.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.d(LOG_TAG, "onLoaderReset()");
        mProductCursorAdapter.swapCursor(null);
    }
}
