package com.mj.receiptchecker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.mj.receiptchecker.adapters.GridViewAdapter;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set display date to today
        EditText tv = (EditText) findViewById(R.id.receipt_date);
        DateHelper.setDate(tv);

        // set up grid
        final String[] strs = new String[]{
                "My Receipts", "Feeling Lucky", "Lucky Numbers", "Quick Scan"
        };
        GridView gv = (GridView) findViewById(R.id.main_grid_view);
        gv.setAdapter(new GridViewAdapter(this, strs));

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if(strs[position] == "My Receipts") {
                    showReceipts(v);
                }
            }
        });
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

    public void showReceipts(View view) {
        Intent intent = new Intent(this, ShowReceiptActivity.class);
        startActivity(intent);
    }

    public void saveReceipt(View view) {
        String receiptId = ((EditText) findViewById(R.id.receipt_number)).getText().toString();
        String receiptDate = ((EditText) findViewById(R.id.receipt_date)).getText().toString();

        // todo: better validation
        if(!receiptId.isEmpty() && !receiptDate.isEmpty()) {
            Receipt receipt = new Receipt(receiptId, receiptDate);
            ReceiptHelper receiptHelper = new ReceiptHelper(this);
            receiptHelper.addReceipt(receipt);

            Toast toast = Toast.makeText(this, R.string.save_toast, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}





