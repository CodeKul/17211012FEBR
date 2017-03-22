package com.codekul.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getCanonicalName();
    private DbHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new DbHelper(this, Db.DB_NAME, null, 1);
    }

    public void onDelete(View view) {
        final SQLiteDatabase sqDb = helper.getWritableDatabase();
        sqDb.delete(Db.TAB_PRODS, Db.COL_PROD_NAME + " = ?", new String[]{getProduct()});
        sqDb.close();
    }

    public void onDisplay(View view) {
        displayOne(getProduct());
    }

    private void displayAll() {
        final SQLiteDatabase sqDb = helper.getReadableDatabase();

        String table = Db.TAB_PRODS;
        String[] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;

        Cursor cursor = sqDb.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
        while (cursor.moveToNext()) {
            String prodName = cursor.getString(0);
            Double cost = cursor.getDouble(cursor.getColumnIndex(Db.COL_PROD_COST));
            Log.i(TAG, "Product - " + prodName + " Cost - " + cost);
        }
        cursor.close();
        sqDb.close();
    }

    private void displayOne(String name) {
        final SQLiteDatabase sqDb = helper.getReadableDatabase();

        String table = Db.TAB_PRODS;
        String[] columns = {Db.COL_PROD_COST};
        String selection = Db.COL_PROD_NAME + " = ?";
        String[] selectionArgs = {name};
        String groupBy = null;
        String having = null;
        String orderBy = null;

        Cursor cursor = sqDb.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
        if (cursor.moveToNext()) {
            Double cost = cursor.getDouble(cursor.getColumnIndex(Db.COL_PROD_COST));
            setProduct(name);
            setCost(cost);
        }
        cursor.close();
        sqDb.close();
    }

    public void onUpdate(View view) {

        final SQLiteDatabase sqDb = helper.getWritableDatabase();

        String table = Db.TAB_PRODS;
        ContentValues values = new ContentValues();
        values.put(Db.COL_PROD_COST, getCost());

        String whereClause = Db.COL_PROD_NAME + " = ?";
        String[] whereArgs = {getProduct()};

        sqDb.update(table, values, whereClause, whereArgs);
        sqDb.close();
    }

    public void onInsert(View view) {
        /*final SQLiteDatabase sqDb = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Db.COL_PROD_NAME, getProduct());
        values.put(Db.COL_PROD_COST, getCost());
        sqDb.insert(Db.TAB_PRODS, null, values);
        sqDb.close();*/

        rawInsert();
    }

    public String getProduct() {
        return ((EditText) findViewById(R.id.edtProduct)).getText().toString();
    }

    public Double getCost() {
        return Double.parseDouble(((EditText) findViewById(R.id.edtCost)).getText().toString());
    }

    public void setProduct(String name) {
        ((EditText) findViewById(R.id.edtProduct)).setText(name);
    }

    public void setCost(Double cost) {
        ((EditText) findViewById(R.id.edtCost)).setText("" + cost);
    }

    public void rawInsert() {
        final  SQLiteDatabase sqDb = helper.getWritableDatabase();
        sqDb.execSQL("insert into "+Db.TAB_PRODS +" values ('"+getProduct() +"', "+ getCost() +")");
        sqDb.close();
    }
}
