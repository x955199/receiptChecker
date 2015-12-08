package com.mj.receiptchecker;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by Michael on 11/17/2015.
 */
public class Receipt {

    public String id;
    public DateTime date;
    public int matchLevel;

    public Receipt(String id, String date) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy");
        this.id = id;
        this.date = formatter.parseDateTime(date);
        this.matchLevel = 0;
    }

    public Receipt(String id, int date, int matchLevel) {
        this.id = id;
        this.date = new DateTime(date * 1000l);
        this.matchLevel = matchLevel;
    }

//    @Override
//    public String toString(){
//        return this.id + "    " + this.date.toString("MM/dd/yyyy") + "    " + this.matchLevel;
//    }

}
