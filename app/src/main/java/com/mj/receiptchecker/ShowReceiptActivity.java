package com.mj.receiptchecker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.mj.receiptchecker.adapters.ReceiptAdapter;

import java.util.List;

public class ShowReceiptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_receipt);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ReceiptHelper receiptHelper = new ReceiptHelper(this);
        List<Receipt> receipts = receiptHelper.getAllReceipts();
        ListView listView = (ListView) findViewById(R.id.receipt_list_view);
        ReceiptAdapter receiptAdapter = new ReceiptAdapter(this, receipts);
        listView.setAdapter(receiptAdapter);
    }

    public void deleteReceipts(View view) {
        ReceiptHelper receiptHelper = new ReceiptHelper(this);
        receiptHelper.deleteAllReceipts();
        finish();
        startActivity(getIntent());
    }

}
