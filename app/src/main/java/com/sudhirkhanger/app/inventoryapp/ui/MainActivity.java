package com.sudhirkhanger.app.inventoryapp.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.sudhirkhanger.app.inventoryapp.R;

public class MainActivity extends AppCompatActivity
        implements MainActivityFragment.Callback {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String PRODUCT_KEY = "product_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(Uri productUri) {
        String uriPackage = productUri.toString();
        Log.d(LOG_TAG, "product item " + uriPackage);
        Intent intent = new Intent(this, DetailProductActivity.class);
        intent.putExtra(PRODUCT_KEY, uriPackage);
        startActivity(intent);
    }
}
