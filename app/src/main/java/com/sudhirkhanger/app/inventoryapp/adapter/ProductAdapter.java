package com.sudhirkhanger.app.inventoryapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sudhirkhanger.app.inventoryapp.R;
import com.sudhirkhanger.app.inventoryapp.model.Product;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {

    public ProductAdapter(Context context, ArrayList<Product> productArrayList) {
        super(context, 0, productArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Product product = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product_list_item, parent, false);
        }

        TextView productName = (TextView) convertView.findViewById(R.id.product_name_textview);
        TextView productPrice = (TextView) convertView.findViewById(R.id.product_price_textview);

        productName.setText(product.getProductName());
        productPrice.setText(String.valueOf(product.getProductPrice()));

        return convertView;
    }
}
