package com.example.appver2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    String TAG = "===";
    String msg, title, click_action;
    NotificationManagerCompat notificationManager;


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        //This will give you the topic string from curl request (/topics/news)
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        title = remoteMessage.getNotification().getTitle();
        msg = remoteMessage.getNotification().getBody();
        // ------------- ??
        click_action = remoteMessage.getData().get("clcikAction");
        // ------------- ??
        Log.d(TAG, "title : " + title + " msg : " + msg);

        //
        //This is where you get your click_action
        // click_action추가?
        sendNotification(title,msg,click_action);
        Log.d(TAG, "sendNotification:"+title);
        Log.d(TAG, "Notification Click Action: " + remoteMessage.getNotification().getClickAction());
    }


    //-----------------------------------------------------------------------

    private void sendNotification (String title, String msg, String click_action) {
        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
        // intent.putExtra("title", title);
        // intent.putExtra("message", msg);

        Bundle bundle = new Bundle();
        bundle.putString("title",title); //?
        bundle.putString("message",msg);

        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Log.d(TAG, "intent확인:");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Bitmap bmp = BitmapFactory.decodeResource(this.getResources(),R.drawable.ic_launcher_background);


        String channelId = "channel_id";
        String channelName = "Channel_name";
        Log.d(TAG, "Channel_name:"+channelName);

        int importance = NotificationManager.IMPORTANCE_LOW;
        notificationManager = NotificationManagerCompat.from(this);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel mnotify = new NotificationChannel(title,msg, importance);
//            notificationManager.createNotificationChannel(mnotify);
//        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle(title)
                .setContentText(msg)
                .setAutoCancel(true)
                .setVibrate(new long[]{1, 1000});
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, mBuilder.build());
        Log.d(TAG, "notify확인");
    }


    @Override
   public void onNewToken(@NonNull String s) {
        super.onNewToken(s);

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isSuccessful()) {
                    Log.d("===", "getting new token fail!");
                    return;
                }

                String token = task.getResult().getToken();

                String msg = getString(R.string.msg_token_fmt, token);
                Log.d(TAG, msg);
                Toast.makeText(MyFirebaseMessagingService.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}