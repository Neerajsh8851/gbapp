package com.nibodev.mobileads;

import static com.nibodev.androidutil.AndroidUtility.console;


public class AdTimer {
    private int minTime;
    private Long previousTime;

    /**
     * @param minTime only show ad after [minTime] otherwise not
     */
    public AdTimer(int minTime) {
        this.minTime = minTime;
        previousTime = System.currentTimeMillis();
    }

    /**
     * Verify that the required amount of waiting time  has elapsed for the ad to be appear
     */
    public boolean isWaitingTimePassed()  {
        long currentTime = System.currentTimeMillis();
        long diff = currentTime - previousTime;
        if (diff > minTime) {
            console("AdTimer", "difference: $diff");
            previousTime = currentTime;
            return true;
        } else return false;
    }
}