package com.example.mark_i5.xmlparseasync;

import com.example.mark_i5.xmlparseasync.data.Article;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mark-i5 on 25/08/2014.
 */
public interface ResultsCallback {
    public void onPreExecute();
    public void onPostExecute(ArrayList<Article> items);
}
