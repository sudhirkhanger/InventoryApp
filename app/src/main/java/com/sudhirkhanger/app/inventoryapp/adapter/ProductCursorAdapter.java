package com.sudhirkhanger.app.inventoryapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.sudhirkhanger.app.inventoryapp.R;
import com.sudhirkhanger.app.inventoryapp.model.ProductContract;

public class ProductCursorAdapter extends CursorAdapter {

    public ProductCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    public static class ProductViewHolder {
        public final TextView mProductNameTextView;
        public final TextView mProductPriceTextView;

        public ProductViewHolder(View view) {
            mProductNameTextView = (TextView) view.findViewById(R.id.product_name_textview);
            mProductPriceTextView = (TextView) view.findViewById(R.id.product_price_textview);
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
    public void bindView(View view, Context context, Cursor cursor) {

        ProductViewHolder productViewHolder = (ProductViewHolder) view.getTag();

        String name = cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_NAME));
        double priceVal = cursor.getDouble(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRICE));
        String priceStr = "$" + priceVal;

        productViewHolder.mProductNameTextView.setText(name);
        productViewHolder.mProductPriceTextView.setText(priceStr);
    }
}
