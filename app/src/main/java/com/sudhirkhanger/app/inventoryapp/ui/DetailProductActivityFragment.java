package com.sudhirkhanger.app.inventoryapp.ui;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sudhirkhanger.app.inventoryapp.R;
import com.sudhirkhanger.app.inventoryapp.model.ProductContract;
import com.sudhirkhanger.app.inventoryapp.utility.Utility;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailProductActivityFragment extends Fragment {

    public static final String LOG_TAG = DetailProductActivityFragment.class.getSimpleName();
    private ContentResolver mContentResolver;

    private String name;
    private double price;
    private String imageUri;
    private int quantity;
    private int sold;
    private String supplier;

    private Uri mUris;
    private ImageView imageView;
    private TextView nameTextView;
    private TextView priceTextView;
    private TextView quantityTextView;
    private TextView soldTextView;
    private TextView supplierTextView;
    private Button addButton;
    private Button removeButton;
    private Button deleteButton;
    private ViewTreeObserver viewTree;
    private ImageButton mImageButtons;

    public DetailProductActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail_product, container, false);
        initViews(rootView);
        mContentResolver = getActivity().getContentResolver();
        final ContentValues contentValues = new ContentValues();

        final Intent intent = getActivity().getIntent();
        String uriString = intent.getStringExtra(MainActivity.PRODUCT_KEY);
        mUris = Uri.parse(uriString);
        Log.d(LOG_TAG, "product uri " + uriString);

        Cursor cursor = queryProduct(mUris);
        extractDataFromCursor(cursor);
        cursor.close();

        viewTree.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                Utility.setPic(getContext(), imageView, Uri.parse(imageUri));
                nameTextView.setText(name);
                priceTextView.setText("$" + price);
                quantityTextView.setText(String.valueOf(quantity));
                soldTextView.setText(String.valueOf(sold));
                supplierTextView.setText(supplier);
                return true;
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "Add Button");
                contentValues.clear();
                if (quantity > 0) {
                    contentValues.put(ProductContract.ProductEntry.COLUMN_SOLD, ++sold);
                    contentValues.put(ProductContract.ProductEntry.COLUMN_QUANTITY, --quantity);
                    mContentResolver.update(
                            mUris,
                            contentValues,
                            null,
                            null);
                }
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "Remove");
                contentValues.put(ProductContract.ProductEntry.COLUMN_QUANTITY, ++quantity);
                mContentResolver.update(
                        mUris,
                        contentValues,
                        null,
                        null);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "delete");

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Confirm");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mContentResolver.delete(
                                mUris,
                                null,
                                null);
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        mImageButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        return rootView;
    }

    /*
     * Adopted from http://www.tutorialspoint.com/android/android_sending_email.htm
     */
    private void sendEmail() {
        String[] TO = {supplier};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Order " + name);
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Please ship " + name +
                " in quantities " + quantity);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private Cursor queryProduct(Uri uri) {
        return mContentResolver.query(
                uri,
                MainActivityFragment.PRODUCT_COLUMNS,
                null,
                null,
                null);
    }

    private void extractDataFromCursor(Cursor cursor) {
        if (cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_NAME));
            price = cursor.getDouble(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRICE));
            imageUri = cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_IMAGE));
            quantity = cursor.getInt(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_QUANTITY));
            sold = cursor.getInt(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_SOLD));
            supplier = cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_SUPPLIER));
            Log.d(LOG_TAG,
                    "Name: " + name + " " +
                            "Price: " + price + " " +
                            "Image: " + imageUri + " " +
                            "Quantity: " + quantity + " " +
                            "Sold: " + sold + " " +
                            "Supplier: " + supplier);
        }
    }

    private void initViews(View view) {
        imageView = (ImageView) view.findViewById(R.id.imageview_detail);
        nameTextView = (TextView) view.findViewById(R.id.name_detail);
        priceTextView = (TextView) view.findViewById(R.id.price_detail);
        quantityTextView = (TextView) view.findViewById(R.id.quantity_detail);
        soldTextView = (TextView) view.findViewById(R.id.sold_detail);
        supplierTextView = (TextView) view.findViewById(R.id.supplier_detail);
        addButton = (Button) view.findViewById(R.id.add_detail_btn);
        removeButton = (Button) view.findViewById(R.id.remove_detail_btn);
        deleteButton = (Button) view.findViewById(R.id.delete_detail_btn);
        viewTree = imageView.getViewTreeObserver();
        mImageButtons = (ImageButton) view.findViewById(R.id.email_detail);
    }
}
