package com.example.mark_i5.xmlparseasync;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mark-i5 on 25/08/2014.
 */
public class MyAdapter extends BaseAdapter {

    ArrayList<HashMap<String, String>> dataSource;
    Context context;
    LayoutInflater layoutInflater;
    public MyAdapter(Context context, ArrayList<HashMap<String, String>> dataSource){
        this.dataSource = dataSource;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position  ) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View row = convertView;
        MyHolder holder = null;
        if (row == null){
            row = layoutInflater.inflate(R.layout.custom_row, parent, false);
            holder = new MyHolder(row);
            row.setTag(holder);
        }else{
            holder = (MyHolder) row.getTag();
        }
        HashMap<String, String> currentItem = dataSource.get(i);
        holder.articleTitleText.setText(currentItem.get("title"));
        holder.articleDescriptionText.setText(currentItem.get("description"));
        holder.articlePubDate.setText(currentItem.get("pubDate"));

        /*holder.articleTitleText.setText("title");
        holder.articleDescriptionText.setText("description");
        holder.articlePubDate.setText("pubDate");*/
        //holder.articleImage.setImageURI(Uri.parse( currentItem.get("imageUrl")));


        return row;
    }

    class MyHolder{
        TextView articleTitleText, articleDescriptionText, articlePubDate;
        ImageView articleImage;

        public MyHolder (View view){
            this.articleTitleText = (TextView) view.findViewById(R.id.item_title);
            this.articleDescriptionText = (TextView) view.findViewById(R.id.item_description);
            this.articlePubDate = (TextView) view.findViewById(R.id.item_pubdate);
           // this.articleImage = (ImageView) view.findViewById(R.id.item_image);
        }

    }
}
