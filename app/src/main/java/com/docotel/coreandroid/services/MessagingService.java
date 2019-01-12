package com.docotel.coreandroid.services;

import android.content.Intent;

import com.docotel.coreandroid.util.NotificationUtils;
import com.docotel.coreandroid.R;
import com.docotel.coreandroid.features.main.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

/**
 * Created by bezzo on 21/02/18.
 */

public class MessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        NotificationUtils.createNotification(1, remoteMessage.getNotification().getTitle(),
                remoteMessage.getNotification().getBody(), this, MainActivity.class, R.mipmap.ic_launcher);

        if (remoteMessage.getData() != null){
            sendBroadcast(remoteMessage.getData().get("type"));
        }
    }

    private void sendBroadcast(String type) {
        Intent intent = new Intent();
        intent.setAction("NOTIFICATION");
        intent.putExtra("type", type);

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }
}
