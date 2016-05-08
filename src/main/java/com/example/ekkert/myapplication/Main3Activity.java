/*package com.example.ekkert.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class Main3Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ImageView bindImage = (ImageView) findViewById(R.id.imageView);
        String pathToFile = "http://www.bugaga.ru/uploads/posts/2014-06/1401723206_kotyatki-7.jpg";
        DownloadImageWithURLTask downloadTask = new DownloadImageWithURLTask(bindImage);
        downloadTask.execute(pathToFile);
    }

    private class DownloadImageWithURLTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;


        public DownloadImageWithURLTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        public Bitmap doInBackground(String... urls) {
            String pathToFile = urls[0];
            Bitmap bitmap = null;
            try {
                InputStream in = new java.net.URL(pathToFile).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}*/
