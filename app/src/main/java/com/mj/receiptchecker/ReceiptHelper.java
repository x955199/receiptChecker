package com.mj.receiptchecker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Michael on 11/27/2015.
 */
public class ReceiptHelper extends MySQLiteHelper {

    private static final String TABLE_RECEIPT = "receipts";

    // receipt table columns names
    private static final String ID = "id";
    private static final String RECEIPT_ID = "receipt_id";
    private static final String DATE = "date";
    private static final String MATCH_LEVEL = "match_level";

    public ReceiptHelper(Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create receipts table
        String receiptsQuery = "CREATE TABLE " + TABLE_RECEIPT +  "( " +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RECEIPT_ID + " TEXT, " +
                DATE + " INTEGER, "+
                MATCH_LEVEL + " INTEGER )";

        // create receipts table
        db.execSQL(receiptsQuery);
    }

    public void addReceipt(Receipt receipt){
        // get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(RECEIPT_ID, receipt.id);
        values.put(DATE, receipt.date.getMillis() / 1000);
        values.put(MATCH_LEVEL,receipt.matchLevel);

        // insert
        db.insert(TABLE_RECEIPT, null, values);

        // close
        db.close();
    }

    // Get All Books
    public List<Receipt> getAllReceipts() {
        List<Receipt> receipts = new LinkedList<>();

        // build the query
        String query = "SELECT * FROM " + TABLE_RECEIPT + ";";

        // get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // go over each row, build receipt and add it to list
        if (cursor.moveToFirst()) {
            do {
                String receiptId = cursor.getString(1);
                int date = cursor.getInt(2);
                int matchLevel = cursor.getInt(3);
                Receipt receipt = new Receipt(receiptId, date, matchLevel);
                receipts.add(receipt);
            } while (cursor.moveToNext());
        }

        return receipts;
    }

    // todo: Deleting single receipt
    public void deleteReceipt(Receipt receipt) {

    }

    // todo: function to get receipt by year/month

    // Deleting all receipts
    public void deleteAllReceipts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RECEIPT, null, null);
        db.close();
    }

}
