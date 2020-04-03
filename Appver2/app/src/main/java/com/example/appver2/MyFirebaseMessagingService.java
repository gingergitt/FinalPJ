package com.example.appver2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    String TAG = "===";
    String msg, title;
    NotificationManagerCompat notificationManager;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {


        title = remoteMessage.getNotification().getTitle();
        msg = remoteMessage.getNotification().getBody();

        Log.d(TAG, "title : " + title + " msg : " + msg);

        String channelId = "channel";
        String channelName = "Channel_name";
        int importance = NotificationManager.IMPORTANCE_LOW;


        notificationManager = NotificationManagerCompat.from(this);

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

        notificationManager.notify(0, mBuilder.build());

    }


//    private void sendPushNotification(JSONObject json) {
//        //optionally we can display the json into log
//        Log.e(TAG, "Notification JSON" + json.toString());
//
//        try {
//            JSONObject data = json.getJSONObject("data");
//            //parsing json data
//
//            String title = data.getString("data");
//            String message = data.getString("message");
//            // String imageUrl = data.getString("Image");
//
//            list.add(message);
//            Collections.reverse(list);
//            System.out.println(list);
//
//            MyNotificationManager mNotificationManager = new MyNotificationManager(getApplicationContext());
//
//            Intent intent = new Intent(getApplicationContext(),MapActivity.class);
////            //if there is no image
////            if(imageUrl.equals("null")){
////                //displaying small notification
////                mNotificationManager.showSmallNotification(title, message, intent);
////            }else{
////                //if there is an image
////                //displaying a big notification
////                mNotificationManager.showBigNotification(title, message, imageUrl, intent);
////            }
//        } catch (JSONException e) {
//            Log.e(TAG, "Json Exception: " + e.getMessage());
//        } catch (Exception e) {
//            Log.e(TAG, "Exception: " + e.getMessage());
//        }
//
//    }


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