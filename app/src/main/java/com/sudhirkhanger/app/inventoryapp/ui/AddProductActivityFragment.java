package com.sudhirkhanger.app.inventoryapp.ui;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sudhirkhanger.app.inventoryapp.R;
import com.sudhirkhanger.app.inventoryapp.model.ProductContract;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddProductActivityFragment extends Fragment {

    private static final int SOLD = 0;
    private static final String LOG_TAG = AddProductActivityFragment.class.getSimpleName();

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
        final String image = "image";
        Button addImageButton = (Button) rootView.findViewById(R.id.add_product_image_button);

        final Button saveProductButton = (Button) rootView.findViewById(R.id.add_product_save_button);
        saveProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = mNameEditText.getText().toString();
                double priceVal;
                try {
                    priceVal = Double.parseDouble(mPriceEditText.getText().toString());
                } catch (NumberFormatException e) {
                    priceVal = -1.0;
                }

                int quantityVal;
                try {
                    quantityVal = Integer.parseInt(mQuantityEditText.getText().toString());
                } catch (NumberFormatException e) {
                    quantityVal = -1;
                }
                final String supplier = mSupplierEditText.getText().toString();

                saveProduct(name, priceVal, image, quantityVal, supplier);
            }
        });

        // TODO do something for unique name constraint
        // TODO validate input prohibit empty one's

        return rootView;
    }

    private void saveProduct(String name, double price, String image, int quantity, String supplier) {
        ContentValues values = new ContentValues();

        values.put(ProductContract.ProductEntry.COLUMN_NAME, name);
        values.put(ProductContract.ProductEntry.COLUMN_PRICE, price);
        //TODO update image code
        values.put(ProductContract.ProductEntry.COLUMN_IMAGE, image);
        values.put(ProductContract.ProductEntry.COLUMN_QUANTITY, quantity);
        values.put(ProductContract.ProductEntry.COLUMN_SOLD, SOLD);
        values.put(ProductContract.ProductEntry.COLUMN_SUPPLIER, supplier);

        if (isProductExist(name)) {
            makeToast(getActivity()
                    .getResources()
                    .getString(R.string.product_exists));
            Log.d(LOG_TAG, "Product Exists");
        } else if (isValueInvalid(name) ||
                isValueInvalid(price) ||
                isValueInvalid(image) ||
                isValueInvalid(quantity) ||
                isValueInvalid(supplier)) {
            makeToast(getActivity()
                    .getResources()
                    .getString(R.string.invalid_entry));
            Log.d(LOG_TAG, "Invalid Entry");
        } else {
            getActivity().getContentResolver()
                    .insert(ProductContract.ProductEntry.CONTENT_URI, values);
            makeToast(getActivity()
                    .getResources()
                    .getString(R.string.entry_created));
            Log.d(LOG_TAG, "Entry created");
        }

    }

    private boolean isProductExist(String name) {
        Cursor cursor = getActivity().getContentResolver().query(
                ProductContract.ProductEntry.CONTENT_URI,
                new String[]{ProductContract.ProductEntry.COLUMN_NAME},
                ProductContract.ProductEntry.COLUMN_NAME + " = ?",
                new String[]{name},
                null);

        boolean recordExists;

        if (cursor != null) {
            recordExists = cursor.getCount() > 0;
            cursor.close();
        } else {
            recordExists = false;
        }
        return recordExists;
    }

    private boolean isValueInvalid(String string) {
        return string == null || string.equals("");
    }

    private boolean isValueInvalid(int num) {
        return num < 0;
    }

    private boolean isValueInvalid(double num) {
        return num < 0;
    }

    private void makeToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
