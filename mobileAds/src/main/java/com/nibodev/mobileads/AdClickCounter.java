package com.nibodev.mobileads;

public class AdClickCounter {
    private final int _trigAdAtClick;
    private int clickRegister;

    public AdClickCounter(int trigAdAtClick) {
        _trigAdAtClick = trigAdAtClick;
        clickRegister = 0;
    }

    /**
     * Can trigger ad
     * @return true if you can show ad.
     */
    public boolean canTrigger() {
        boolean result = false;
        clickRegister++;
        if (clickRegister >= _trigAdAtClick) {
            clickRegister = 0;
            result = true;
        }
        return result;
    }
}
