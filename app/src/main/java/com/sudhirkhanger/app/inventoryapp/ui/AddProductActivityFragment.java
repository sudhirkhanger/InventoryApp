package com.sudhirkhanger.app.inventoryapp.ui;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sudhirkhanger.app.inventoryapp.R;
import com.sudhirkhanger.app.inventoryapp.model.ProductContract;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddProductActivityFragment extends Fragment {

    private static final int SOLD = 0;

    EditText mNameEditText;
    EditText mPriceEditText;
    EditText mQuantityEditText;
    EditText mSupplierEditText;

    public AddProductActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_product, container, false);

        mNameEditText = (EditText) rootView.findViewById(R.id.name_add_product_edittext);
        mPriceEditText = (EditText) rootView.findViewById(R.id.price_add_product_edittext);
        mQuantityEditText = (EditText) rootView.findViewById(R.id.quantity_add_product_edittext);
        mSupplierEditText = (EditText) rootView.findViewById(R.id.supplier_add_product_edittext);

        // TODO update image code
        Button addImageButton = (Button) rootView.findViewById(R.id.add_product_image_button);

        final Button saveProductButton = (Button) rootView.findViewById(R.id.add_product_save_button);
        saveProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = mNameEditText.getText().toString();
                final double price = Double.parseDouble(mPriceEditText.getText().toString());
                final int quantity = Integer.parseInt(mQuantityEditText.getText().toString());
                final String supplier = mSupplierEditText.getText().toString();
                saveProduct(name, price, "", quantity, supplier);
            }
        });

        // TODO do something for unique name constraint

        return rootView;
    }

    private void saveProduct(String name, double price, String image, int quantity, String supplier) {
        ContentValues values = new ContentValues();

        values.put(ProductContract.ProductEntry.COLUMN_NAME, name);
        values.put(ProductContract.ProductEntry.COLUMN_PRICE, price);
        //TODO update image code
        values.put(ProductContract.ProductEntry.COLUMN_IMAGE, "image");
        values.put(ProductContract.ProductEntry.COLUMN_QUANTITY, quantity);
        values.put(ProductContract.ProductEntry.COLUMN_SOLD, SOLD);
        values.put(ProductContract.ProductEntry.COLUMN_SUPPLIER, supplier);

        getActivity().getContentResolver()
                .insert(ProductContract.ProductEntry.CONTENT_URI, values);
    }
}
