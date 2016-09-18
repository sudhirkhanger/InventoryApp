package com.sudhirkhanger.app.inventoryapp.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sudhirkhanger.app.inventoryapp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailProductActivityFragment extends Fragment {

    public DetailProductActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_product, container, false);
    }
}
