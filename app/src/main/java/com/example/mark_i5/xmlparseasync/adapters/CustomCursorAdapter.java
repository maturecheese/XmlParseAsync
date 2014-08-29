package com.example.mark_i5.xmlparseasync.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mark_i5.xmlparseasync.R;
import com.example.mark_i5.xmlparseasync.ResultsCallback;
import com.example.mark_i5.xmlparseasync.data.ArticleDatabase;
import com.example.mark_i5.xmlparseasync.tasks.ArticleIconTask;
import com.example.mark_i5.xmlparseasync.tasks.SaveImageTask;

import java.util.zip.Inflater;

import javax.xml.transform.Result;

/**
 * Created by mark-i5 on 28/08/2014.
 */
public class CustomCursorAdapter extends CursorAdapter{
    private LayoutInflater inflater;
    private SaveImageTask saveImageTask;
    private final int titleIndex;
    private final int descIndex;
    private final int pubDateIndex;
    private final int imageUrlIndex;
   // private ResultsCallback callback;


    public CustomCursorAdapter(ArticleDatabase database, ResultsCallback callback,
                               Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.saveImageTask = new SaveImageTask(context,callback);

        this.titleIndex = cursor.getColumnIndex(ArticleDatabase.ArticleDatabaseHelper.TITLE);
        this.descIndex = cursor.getColumnIndex(ArticleDatabase.ArticleDatabaseHelper.DESCRIPTION);
        this.pubDateIndex = cursor.getColumnIndex(ArticleDatabase.ArticleDatabaseHelper.PUBLISHED_AT);
        this.imageUrlIndex = cursor.getColumnIndex(ArticleDatabase.ArticleDatabaseHelper.IMAGE_URL);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return inflater.inflate(R.layout.custom_row, parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder holder = (ViewHolder) view.getTag();
        if(holder == null){
            holder = new ViewHolder();
            holder.textViewTitle = (TextView) view.findViewById(R.id.item_title);
            holder.textViewDesc = (TextView) view.findViewById(R.id.item_description);
            holder.textViewPubDate = (TextView) view.findViewById(R.id.item_pubdate);
            holder.imageViewCaption = (ImageView) view.findViewById(R.id.item_image);
            view.setTag(holder);
        }
        holder.textViewTitle.setText(cursor.getString(titleIndex));
        holder.textViewDesc.setText(cursor.getString(descIndex));
        holder.textViewPubDate.setText(cursor.getString(pubDateIndex));
        if (cursor.isNull(imageUrlIndex)){

        }

        holder

    }

    static class ViewHolder{

        TextView textViewTitle;
        TextView textViewDesc;
        TextView textViewPubDate;
        ImageView imageViewCaption;
    }
}
