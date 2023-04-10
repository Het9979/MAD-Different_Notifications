package com.example.different_notification;

import static android.app.Notification.VISIBILITY_PRIVATE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button nrml,largeicon,headsup,hiddenContent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nrml = findViewById(R.id.nrmlnoti);
        largeicon = findViewById(R.id.largeicon);
        headsup = findViewById(R.id.headsup);
        hiddenContent = findViewById(R.id.hidden);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel
                    ("Normal Notification" , "Normal Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

            NotificationChannel channel2 = new NotificationChannel("Large Icon",
                    "Large Icon" , NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager2 = getSystemService(NotificationManager.class);
            manager2.createNotificationChannel(channel2);
            channel2.setShowBadge(true);


            NotificationChannel channel3 = new NotificationChannel("HeadsUp Notification","HeadsUp Notification", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager3 = getSystemService(NotificationManager.class);
            manager3.createNotificationChannel(channel3);

            NotificationChannel channel4 = new NotificationChannel("Hide On LockScreen","Hide On LockScreen",NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager4 = getSystemService(NotificationManager.class);
            manager4.createNotificationChannel(channel4);

        }

        nrml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder
                        (MainActivity.this,"Normal Notification");
                builder.setContentTitle("Normal Notification");
                builder.setContentText("This is a simple Notification");
                builder.setSmallIcon(R.drawable.text);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from
                        (MainActivity.this);

                managerCompat.notify(1, builder.build());
            }
        });


        largeicon.setOnClickListener(new View.OnClickListener() {

            Bitmap pic = BitmapFactory.decodeResource(getResources(), R.drawable.largreicon);

            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder
                        (MainActivity.this,"Large Icon");
                builder.setContentTitle("Notfication 2");
                builder.setContentText("This notification contains large icon , date/time and badge");
                builder.setSmallIcon(R.drawable.text);
                builder.setLargeIcon(pic);
                builder.setAutoCancel(true);
                builder.setWhen(System.currentTimeMillis()); //for getting the current system time
                builder.setShowWhen(true); // for showing the current system time


                NotificationManagerCompat managercompat = NotificationManagerCompat.from(MainActivity.this);
                managercompat.notify(2, builder.build());

            }
        });

        headsup.setOnClickListener(new View.OnClickListener() {

            Bitmap pic = BitmapFactory.decodeResource(getResources(), R.drawable.largreicon);
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.google.com/"));
                PendingIntent pendingIntent = PendingIntent.getActivity
                        (getApplicationContext(), 0,intent,PendingIntent.FLAG_MUTABLE);
                NotificationCompat.Builder builder = new NotificationCompat.Builder
                        (MainActivity.this,"HeadsUp Notification");
                builder.setContentTitle("HeadsUp Notification");
                builder.setContentText("This is headsup notification which will open google when clicked.");
                builder.setSmallIcon(R.drawable.text);
                builder.setLargeIcon(pic);
                builder.addAction(R.mipmap.ic_launcher,"Google",pendingIntent);
                builder.setAutoCancel(true);

                NotificationManagerCompat managercompat = NotificationManagerCompat.from(MainActivity.this);
                managercompat.notify(3, builder.build());

            }
        });

        hiddenContent.setOnClickListener(new View.OnClickListener() {
            Bitmap pic = BitmapFactory.decodeResource(getResources(), R.drawable.largreicon);
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder
                        (MainActivity.this,"Hide On LockScreen");
                builder.setSmallIcon(R.drawable.text);
                builder.setLargeIcon(pic);
                builder.setContentTitle("Not visible on lockscreen");
                builder.setContentText("This message will not be visible on the lockscreen");
                builder.setVisibility(NotificationCompat.VISIBILITY_PRIVATE);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
                managerCompat.notify(4, builder.build());
            }
        });
    }
}