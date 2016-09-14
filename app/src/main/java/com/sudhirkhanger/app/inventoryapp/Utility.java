package com.sudhirkhanger.app.inventoryapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.sudhirkhanger.app.inventoryapp.model.Product;
import com.sudhirkhanger.app.inventoryapp.model.ProductContract;

public class Utility {

    public static void addProduct(SQLiteDatabase db, Product product) {

        ContentValues values = new ContentValues();
        values.put(ProductContract.ProductEntry.COLUMN_NAME, product.getProductName());
        values.put(ProductContract.ProductEntry.COLUMN_PRICE, product.getProductPrice());
        values.put(ProductContract.ProductEntry.COLUMN_IMAGE, product.getProductImage());
        values.put(ProductContract.ProductEntry.COLUMN_QUANTITY, product.getProductQuantity());
        values.put(ProductContract.ProductEntry.COLUMN_SOLD, product.getProductSold());
        values.put(ProductContract.ProductEntry.COLUMN_SUPPLIER, product.getProductSupplier());

        // Inserting Row
        db.insert(ProductContract.ProductEntry.TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }
}
