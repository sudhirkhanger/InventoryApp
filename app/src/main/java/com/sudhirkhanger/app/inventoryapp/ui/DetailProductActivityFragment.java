package com.sudhirkhanger.app.inventoryapp.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sudhirkhanger.app.inventoryapp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailProductActivityFragment extends Fragment {

    public static final String LOG_TAG = DetailProductActivityFragment.class.getSimpleName();

    public DetailProductActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail_product, container, false);

        Intent intent = getActivity().getIntent();
        String uriString = intent.getStringExtra(MainActivity.PRODUCT_KEY);
        Uri uri = Uri.parse(uriString);
        Log.d(LOG_TAG, "product uri " + uriString);
        
        return rootView;
    }
}
