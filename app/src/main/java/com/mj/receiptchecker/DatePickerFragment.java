package com.mj.receiptchecker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Michael on 12/7/2015.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    // Set the date chosen by the user
    public void onDateSet(DatePicker view, int year, int month, int day) {
        EditText textView = (EditText) getActivity().findViewById(R.id.receipt_date);
        DateHelper.setDate(textView, day, month, year);
    }
}

class DateHelper {

    private static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

    public static void setDate(EditText et, int day, int month, int year) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy");
        month++;
        DateTime date = formatter.parseDateTime(month + "/" + day + "/" + year);
        DateTimeFormatter dtfOut = DateTimeFormat.forPattern("MM/dd/yyyy");
        et.setText(dtfOut.print(date));
    }

    public static void setDate(EditText et) {
        long currentMills = System.currentTimeMillis();
        et.setText(sdf.format(currentMills));
    }
}
