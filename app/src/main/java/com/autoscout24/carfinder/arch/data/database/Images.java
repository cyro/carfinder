package com.autoscout24.carfinder.arch.data.database;

public class Images {
    private String url;

    public Images(String url) {
        this.url = url;
    }


    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }
}
