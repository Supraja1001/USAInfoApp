package com.example.supraja.usainfoapp;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import static android.app.PendingIntent.FLAG_CANCEL_CURRENT;
import static java.lang.Thread.sleep;

/**
 * Created by Supraja on 6/16/2017.
 */

public class ImageDownloadService extends IntentService {

    public static final int NOTIF_ID = 1;

    public ImageDownloadService() {
        super("image-service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

      Log.d("####","Service Started");

        String imageUrl = intent.getStringExtra("url");

        Log.d("####",imageUrl);

        Bitmap bitmap = downloadImage(imageUrl);

        sleep(2000);

       // saveImage(bitmap);

        createNotification(bitmap);

    }

    protected Bitmap downloadImage(String address) {

        Log.d("####","in downloadImage");

        URL url = getUrlFromString(address);

        InputStream in = getInputStream(url);

        Bitmap bitmap = decodeBitmap(in);

        return bitmap;
    }

    private  void saveImage(Bitmap bitmap){

        String root = Environment.getExternalStorageDirectory().toString();
        Log.d("####",root);
        File myDir = new File(root + "/Downloads");

        if (!myDir.exists()) {
            myDir.mkdirs();
        }

        String name = new Date().toString() + ".jpg";

        myDir = new File(myDir, name);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(myDir);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);

        try {
            out.flush();
            out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }


    private void createNotification(Bitmap bmp) {
        Log.d("####","In Create Notification");

        Bitmap resizedBitmap = Bitmap
                .createScaledBitmap(bmp, bmp.getWidth() / 5, bmp.getHeight() / 5, false);

        Intent intent = new Intent(this, StateDetailsFragment.class);
        intent.putExtra("bitmap", resizedBitmap);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.user_default)
                .setContentTitle("Image Download Complete!")
                .setContentText(
                        "Image download from IntentService has completed! Click to view!")
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(bmp))
                .setSound(defaultSoundUri)
                .setContentIntent(pIntent);

        Notification noti = builder.build();
        noti.flags |= Notification.FLAG_AUTO_CANCEL;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(
                Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIF_ID, noti);
    }

    private URL getUrlFromString(String address) {

        Log.d("####","In get url from string");
        URL url;
        try {
            url = new URL(address);
        } catch (MalformedURLException e1) {
            url = null;
        }
        return url;
    }

    private InputStream getInputStream(URL url) {
        Log.d("####","In get input stream");
        InputStream in;
        URLConnection conn;
        try {
            conn = url.openConnection();
            conn.connect();
            in = conn.getInputStream();
        } catch (IOException e) {
            in = null;
        }
        return in;
    }

    private Bitmap decodeBitmap(InputStream in) {
        Log.d("####","in decode bitmap");
        Bitmap bitmap;
        try {
            // Turn response into Bitmap
            bitmap = BitmapFactory.decodeStream(in);
            // Close the input stream
            in.close();
        } catch (IOException e) {
            in = null;
            bitmap = null;
        }
        return bitmap;
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
