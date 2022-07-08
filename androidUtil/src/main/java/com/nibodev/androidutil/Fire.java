package com.nibodev.androidutil;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class Fire {
    private static class FetchResult {
        public boolean updated = false;
        public boolean isComplete = false;
    }

    public static FirebaseRemoteConfig getRemoteConfig() {
        return FirebaseRemoteConfig.getInstance();
    }

    public static String getString(String key) {
        return FirebaseRemoteConfig.getInstance().getString(key);
    }

    public static long getLong(String key) {
        return getRemoteConfig().getLong(key);
    }

    public static boolean getBoolean(String key) {
       return getRemoteConfig().getBoolean(key);
    }

    /**
     * This function does not return immediately
     * it waits for the result to complete and may take too much time (500ms to 1000ms or more).
     * @return true if the new values are fetched else not.
     */
    public static boolean fetchAndActivate(boolean waitForResult) {
        FetchResult result = new FetchResult();
        getRemoteConfig()
                .fetchAndActivate()
                .addOnCompleteListener(
                        new OnCompleteListener<Boolean>() {
                            @Override
                            public void onComplete(@NonNull Task<Boolean> task) {
                                result.isComplete = true;
                                if (task.isSuccessful()) {
                                    result.updated = task.getResult();
                                    System.out.println("");
                                }
                                if (waitForResult)
                                synchronized (result) {
                                    result.notifyAll();
                                }
                            }
                        }
                );
        // wait for completion
        if (waitForResult)
        while (!result.isComplete) {
            try {
                synchronized (result) {
                    System.out.println("Firebase fetch request: waiting for the result");
                    result.wait();
                    System.out.println("Firebase fetch complete: updated = " + result.updated);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        return result.updated;
    }
}
