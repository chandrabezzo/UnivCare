package com.docotel.coreandroid.services;

import com.docotel.core.util.AppLoggerUtil;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by bezzo on 21/02/18.
 */

public class MessagingInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        AppLoggerUtil.INSTANCE.d("Refresh Firebase Token : "+ refreshedToken);

        sendRegistrationToServer(refreshedToken);
    }

    public void sendRegistrationToServer(String refreshedToken){
        // request to api for refreshed token
    }
}
