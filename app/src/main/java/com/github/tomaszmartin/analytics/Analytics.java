package com.github.tomaszmartin.analytics;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Class for managing data sent to Google Analytics and Firebase Analytics.
 */

public class Analytics {

    private static Analytics instance;
    private FirebaseAnalytics firebaseAnalytics;
    private Tracker googleAnalytics;

    private Analytics(Context context) {
        this.firebaseAnalytics = FirebaseAnalytics.getInstance(context);
        this.googleAnalytics = GoogleAnalytics.getInstance(context).newTracker(R.xml.tracker);
    }

    /**
     * Creates and instance of @Analytcs object
     *
     * @param context
     * @return instance of @Analytics object
     */
    public static Analytics getInstance(Context context) {
        if (instance == null) {
            instance = new Analytics(context);
        }

        return instance;
    }

    public void sendEvent(String category, String action, String label, long value) {
        sendEventToGoogleAnalytics(category, action, label, value);
        sendEventToFirebaseAnalytics(category, action, label, value);
        sendCustomEventToFirebaseAnalytics(category, action, label, value);
    }

    public void sendScreen(String screenName) {
        sendScreenToGoogleAnalytics(screenName);
        sendScreenToFirebaseAnalytics(screenName);
    }

    private void sendScreenToFirebaseAnalytics(String screenName) {
        if (firebaseAnalytics != null) {
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, screenName);
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        }
    }

    private void sendScreenToGoogleAnalytics(String screenName) {
        if (googleAnalytics != null) {
            googleAnalytics.setScreenName(screenName);
            googleAnalytics.send(new HitBuilders.ScreenViewBuilder().build());
        }
    }

    private void sendEventToGoogleAnalytics(String category, String action, String label, long value) {
        if (googleAnalytics != null) {
            googleAnalytics.send(new HitBuilders.EventBuilder()
                    .setCategory(category)
                    .setAction(action)
                    .setLabel(label)
                    .setValue(value)
                    .build());
        }
    }

    private void sendEventToFirebaseAnalytics(String category, String action, String label, long value) {
        if (firebaseAnalytics != null) {
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, label);
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, action);
            bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, category);
            bundle.putDouble(FirebaseAnalytics.Param.VALUE, value);
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
        }
    }

    private void sendCustomEventToFirebaseAnalytics(String category, String action, String label, long value) {
        if (firebaseAnalytics != null) {
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, label);
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, action);
            bundle.putDouble(FirebaseAnalytics.Param.VALUE, value);
            firebaseAnalytics.logEvent(category, bundle);
        }
    }

    private void log(String message) {
        Log.d(Analytics.class.getSimpleName(), message);
    }

    public void setUserID(String userId) {
        firebaseAnalytics.setUserProperty("userID", userId);
    }

}
