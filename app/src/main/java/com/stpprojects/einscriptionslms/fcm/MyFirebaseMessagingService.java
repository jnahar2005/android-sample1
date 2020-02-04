package com.stpprojects.einscriptionslms.fcm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.stpprojects.einscriptionslms.HomeActivity;
import com.stpprojects.einscriptionslms.R;
import com.stpprojects.einscriptionslms.utils.AppLog;
import com.stpprojects.einscriptionslms.utils.SessionManager;

import java.util.Map;
import java.util.Random;


/* Created by Smita Pathak on 29/1/2020 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    SessionManager session;
    public static final String CHANNEL_ONE_ID = "com.stpprojects.lms";
    public static final String CHANNEL_ONE_NAME = "Channel LMS";
    int color = 0x00000000;
    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "From: " + remoteMessage.getData());
        session = new SessionManager(this);
        Map<String, String> map = remoteMessage.getData();
        String notifications_type = map.get("notifications_type");
        Log.v("notifications_type",notifications_type+" *** ");
        sendNotification(map);
    }

    private void sendNotificationCount(String msg) {
        Intent intent = new Intent("NotificationPopup");
        intent.putExtra("msg",msg);
        sendLocationBroadcast(intent);
    }

    private void sendLocationBroadcast(Intent intent) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private int getNotificationIcon(NotificationCompat.Builder notificationBuilder) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int color = 0x0080000;
            notificationBuilder.setColor(color);
            return R.mipmap.appicon;

        } else {
            return R.mipmap.appicon;
        }
    }


    private void sendNotificationStatus() {
        Intent intent = new Intent("notificationCountStatus");
        sendLocationBroadcast(intent);
    }

    private void sendNotification(Map<String, String> map) {
        Random r = new Random();
        int randomNumber = r.nextInt(10000);

        try {
            String from_name1 = map.get("from_name1");
            String title = map.get("title");
            String group_id1 = map.get("group_id1");
            String froms1 = map.get("froms1");
            String message_type1 = map.get("message_type1");
            String to1 = map.get("to1");
            String msg1 = map.get("msg");
            String chat_id1 = map.get("chat_id1");
            String chatType1 = map.get("chatType1");
            String file_name1 = map.get("file_name1");
            String file_path1 = map.get("file_path1");
            String notifications_type = map.get("notifications_type");
            sendNotificationCount(map.get("body"));
            Intent intent = null;
            intent = new Intent(this, HomeActivity.class);
            if (notifications_type.equalsIgnoreCase("0")) {

            } else if (notifications_type.equalsIgnoreCase("1")){

            } else {
                intent = new Intent(this, HomeActivity.class);
                intent.putExtra("FromNotification", "Yes");
                intent.putExtra("class", "MyFirebaseMessagingService");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            }
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, randomNumber /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT);
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ONE_ID, CHANNEL_ONE_NAME, notificationManager.IMPORTANCE_HIGH);
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.setShowBadge(true);
                notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
                notificationManager.createNotificationChannel(notificationChannel);

                notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ONE_ID)
                        .setContentTitle(map.get("title"))
                        .setContentText(map.get("msg"))
                        .setAutoCancel(true)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(map.get("msg")))
                        .setSound(defaultSoundUri)
                        .setColor(color)
                        .setNumber(2)
                        .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                        .setContentIntent(pendingIntent);

                notificationBuilder.setSmallIcon(getNotificationIcon(notificationBuilder));
            } else {
                notificationBuilder = new NotificationCompat.Builder(this)
                        .setContentTitle(map.get("title"))
                        .setContentText(map.get("msg"))
                        .setAutoCancel(true)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(map.get("msg")))
                        .setSound(defaultSoundUri)
                        .setColor(color)
                        .setNumber(2)
                        .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                        .setContentIntent(pendingIntent);

                notificationBuilder.setSmallIcon(getNotificationIcon(notificationBuilder));
            }
            AppLog.v("randomNumber",randomNumber+" LL");
            notificationManager.notify(randomNumber /* ID of notification */, notificationBuilder.build());
        } catch (Exception e) {
        }
    }
}