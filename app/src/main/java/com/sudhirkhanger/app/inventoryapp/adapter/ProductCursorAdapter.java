package com.sudhirkhanger.app.inventoryapp.adapter;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.sudhirkhanger.app.inventoryapp.R;
import com.sudhirkhanger.app.inventoryapp.model.ProductContract;

public class ProductCursorAdapter extends CursorAdapter {

    public static final String LOG_TAG = ProductCursorAdapter.class.getSimpleName();

    public ProductCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    public static class ProductViewHolder {
        public final TextView mProductNameTextView;
        public final TextView mProductPriceTextView;
        public final TextView mProductQuantityTextView;
        public final Button mSaleButtons;

        public ProductViewHolder(View view) {
            mProductNameTextView = (TextView) view.findViewById(R.id.product_name_textview);
            mProductPriceTextView = (TextView) view.findViewById(R.id.product_price_textview);
            mProductQuantityTextView = (TextView) view.findViewById(R.id.product_quantity_textview);
            mSaleButtons = (Button) view.findViewById(R.id.sale_list_button);
        }
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_list_item, viewGroup, false);
        ProductViewHolder productViewHolder = new ProductViewHolder(view);
        view.setTag(productViewHolder);
        return view;
    }

    @Override
    public void bindView(final View view, Context context, Cursor cursor) {

        ProductViewHolder productViewHolder = (ProductViewHolder) view.getTag();

        final String name = cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_NAME));
        final double priceVal = cursor.getDouble(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRICE));
        final int quantity = cursor.getInt(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_QUANTITY));
        final int sold = cursor.getInt(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_SOLD));
        final int id = cursor.getInt(cursor.getColumnIndex(ProductContract.ProductEntry._ID));

        String priceStatement = "Price $" + priceVal;
        String quantityStatement = " Quantity " + quantity;
        final Uri uri = ProductContract.ProductEntry.buildProductUri(id);

        productViewHolder.mProductNameTextView.setText(name);
        productViewHolder.mProductPriceTextView.setText(priceStatement);
        productViewHolder.mProductQuantityTextView.setText(quantityStatement);

        productViewHolder.mSaleButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "Quantity " + quantity);
                ContentResolver resolver = view.getContext().getContentResolver();
                ContentValues values = new ContentValues();
                if (quantity > 0) {
                    int quantityValue = quantity;
                    int soldValue = sold;
                    values.put(ProductContract.ProductEntry.COLUMN_SOLD, ++soldValue);
                    values.put(ProductContract.ProductEntry.COLUMN_QUANTITY, --quantityValue);
                    resolver.update(
                            uri,
                            values,
                            null,
                            null);

                }
            }
        });
    }
}
