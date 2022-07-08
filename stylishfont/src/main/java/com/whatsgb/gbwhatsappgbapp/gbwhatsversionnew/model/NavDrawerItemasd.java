package com.whatsgb.gbwhatsappgbapp.gbwhatsversionnew.model;

public class NavDrawerItemasd {
    private boolean showNotify;
    private String title;


    public NavDrawerItemasd() {

    }

    public NavDrawerItemasd(boolean showNotify, String title) {
        this.showNotify = showNotify;
        this.title = title;
    }

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
