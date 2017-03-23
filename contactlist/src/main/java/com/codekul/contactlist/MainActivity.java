package com.codekul.contactlist;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadMyData();
    }

    private void loadContacts() {

        ContentResolver resolver = getContentResolver();

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;

        ArrayList<String> dataSet = new ArrayList<>();

        Cursor cursor = resolver.query(uri, projection, selection, selectionArgs, sortOrder);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String num = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            dataSet.add(name + "\n" + num);
        }

        ((ListView) findViewById(R.id.listContacts))
                .setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataSet));
    }

    private void loadMyData() {
        ContentResolver resolver = getContentResolver();

        Uri uri = Uri.parse("content://com.codekul.provider.AUTH");
        String[] projection = null;
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;

        ArrayList<String> dataSet = new ArrayList<>();

        Cursor cursor = resolver.query(uri, projection, selection, selectionArgs, sortOrder);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("product_name"));
            String cost = cursor.getString(cursor.getColumnIndex("product_cost"));
            dataSet.add(name + "\n" + cost);
        }

        ((ListView) findViewById(R.id.listContacts))
                .setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataSet));
    }
}
