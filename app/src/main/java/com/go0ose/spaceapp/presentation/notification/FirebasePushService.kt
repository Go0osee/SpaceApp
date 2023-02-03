package com.go0ose.spaceapp.presentation.notification

import com.google.firebase.messaging.FirebaseMessagingService

class FirebasePushService: FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)

    }
}