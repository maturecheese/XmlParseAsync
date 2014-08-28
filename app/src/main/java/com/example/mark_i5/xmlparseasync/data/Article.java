package com.example.mark_i5.xmlparseasync.data;

import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.HashMap;

/**
 * Created by marklloyd on 27/08/2014.
 */
public class Article {
    private static final String LOGTAG = "Article";

    public DateTime getCreatedAt_DateTimeObj() {
        return createdAt_DateTimeObj;
    }

    private String title;
    private String description;
    private String imageUrl;
    private String published;
    private Long createdAt;
    private DateTime createdAt_DateTimeObj;
    private static HashMap<String, Integer> monthNamesToNumber;

    private static String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
                                 "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    public Article(String title, String description, String imageUrl, String published){
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.published = published;
        if (monthNamesToNumber == null){
            this.createHashMap();
        }
        try {
            this.createdAt_DateTimeObj = this.convertPublished(published);
            this.createdAt = this.createdAt_DateTimeObj.getMillis();
        } catch (Exception e) {
            Log.e(LOGTAG, "processing date_time failed", e);
            e.printStackTrace();
        }
    }

    private void createHashMap(){
        monthNamesToNumber = new HashMap<String, Integer>();
        for (int i = 1; i <=12 ; i++){
            monthNamesToNumber.put(months[i-1],i);
        }
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPublished() {
        return published;
    }

    public DateTime convertPublished(String published) throws Exception{


        //String d = "Wed, 27 Aug 2014 12:24:20 GMT";
        String[] splits = published.split(" ");
        int dayNumber = Integer.parseInt(splits[1]);
        int monthNumber =  monthNamesToNumber.get(splits[2]);
        int year = Integer.parseInt(splits[3]);
        String[] hhmmss = splits[4].split(":");
        int hour = Integer.parseInt(hhmmss[0]);
        int minutes = Integer.parseInt(hhmmss[1]);
        int seconds = Integer.parseInt(hhmmss[2]);


        DateTime dateTime = new DateTime(year,monthNumber,dayNumber,hour,
                minutes,seconds,0, DateTimeZone.forID("Europe/London"));

        return dateTime;
    }


    @Override
    public String toString() {
        String toPrint = "title: " + this.title + "\n description: " + this.description +"\n imageUrl: " +
                this.imageUrl + "\n pubDate: " + this.published +"\n createdMillis: " +this.createdAt;
        return toPrint;
    }
}
