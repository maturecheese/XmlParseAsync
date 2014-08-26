package com.example.mark_i5.xmlparseasync;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by marklloyd on 26/08/2014.
 */
public class ArticleIconTask {

    private static final String LOGTAG = "ArticleIconTask";
    private HashMap<String, Drawable> imageCache;
    private BaseAdapter adapter;
    private static Drawable DEFAULT_ICON = null;

    public ArticleIconTask(){
        this.imageCache = new HashMap<String, Drawable>();
    }

    public Drawable loadImage(BaseAdapter adapter, ImageView view){

        this.adapter = adapter;
        String url = (String) view.getTag();
        if(imageCache.containsKey(url)){
            Log.d(LOGTAG, "found image in imageCache");
            return imageCache.get(url);
        }else{
            Log.d(LOGTAG, "did not find image in imageCache");
            new ImageDownloader().execute(url);
            return DEFAULT_ICON;
        }


    }

    private class ImageDownloader extends AsyncTask<String,Void, Drawable >{

        String image_url;

        @Override
        protected Drawable doInBackground(String... strings) {
            String image_url = strings[0];
            InputStream inputStream;
            try {
                Log.d(LOGTAG, "fetching: " + image_url);
                URL url = new URL(image_url);
                inputStream = url.openStream();
            }
            catch ( MalformedURLException e){

                Log.d(LOGTAG, "MalformedURLException: " +e.getMessage() );
                throw new RuntimeException();

            }catch (IOException e){

                Log.d(LOGTAG, "IOException: " +e.getMessage());
                throw new RuntimeException();
            }
            Drawable icon = Drawable.createFromStream(inputStream, "src");

            return icon;
        }


        @Override
        protected void onPostExecute(Drawable downloadedDrawable){
            super.onPostExecute(downloadedDrawable);
            if (downloadedDrawable ==null){
                Log.d(LOGTAG, "the result in onPostExecute is null");
            }
            Log.d(LOGTAG, downloadedDrawable.toString());

            synchronized (this){
                imageCache.put(image_url, downloadedDrawable);
            }
            adapter.notifyDataSetChanged();
        }


    }
}
