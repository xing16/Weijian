package com.xing.weijian.common;

import android.content.Context;
import android.graphics.Bitmap;

import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;

/**
 * Created by Administrator on 2018/7/8.
 */

public class OneKeyShareClient {

    private OnekeyShare oks;
    private String url;
    private String videoUrl;
    private String text;
    private OnekeyShareTheme theme;
    private String titleUrl;
    private String title;
    private String site;
    private String siteUrl;
    private float longitude = -1f;
    private float latitude = -1f;
    private String imageUrl;
    private String[] imageArray;
    private Bitmap imageData;
    private String imagePath;
    private String platform;
    private String comment;
    private String address;
    private String musicUrl;
    private String installUrl;
    private boolean silent = true;


    private OneKeyShareClient() {
        oks = new OnekeyShare();
    }

    public void show(Context context) {
        setShareParams();
        oks.show(context);
    }

    private void setShareParams() {
        if (url != null) {
            oks.setUrl(url);
        }
        if (videoUrl != null) {
            oks.setVideoUrl(videoUrl);
        }
        if (text != null) {
            oks.setText(text);

        }
        if (theme != null) {
            oks.setTheme(theme);
        }
        if (titleUrl != null) {
            oks.setTitleUrl(titleUrl);
        }
        if (title != null) {
            oks.setTitle(title);
        }
        if (site != null) {
            oks.setSite(site);
        }
        if (siteUrl != null) {
            oks.setSiteUrl(siteUrl);
        }
        if (longitude != -1) {
            oks.setLongitude(longitude);
        }
        if (latitude != -1) {
            oks.setLatitude(latitude);
        }
        if (imageUrl != null) {
            oks.setImageUrl(imageUrl);
        }
        if (imageArray != null) {
            oks.setImageArray(imageArray);
        }
        if (imageData != null) {
            oks.setImageData(imageData);
        }
        if (imagePath != null) {
            oks.setImagePath(imagePath);
        }
        if (platform != null) {
            oks.setPlatform(platform);
        }
        if (comment != null) {
            oks.setComment(comment);
        }
        if (address != null) {
            oks.setAddress(address);
        }
        if (musicUrl != null) {
            oks.setMusicUrl(musicUrl);
        }
        if (installUrl != null) {
            oks.setInstallUrl(installUrl);
        }
        oks.setSilent(silent);
    }

    public static class Builder {

        private OneKeyShareClient client;

        public Builder() {
            client = new OneKeyShareClient();
        }

        public Builder setUrl(String url) {
            client.url = url;
            return this;
        }

        public Builder setVideoUrl(String videoUrl) {
            client.videoUrl = videoUrl;
            return this;
        }

        public Builder setText(String text) {
            client.text = text;
            return this;
        }

        public Builder setTheme(OnekeyShareTheme theme) {
            client.theme = theme;
            return this;
        }

        public Builder setTitleUrl(String titleUrl) {
            client.titleUrl = titleUrl;
            return this;
        }

        public Builder setTitle(String title) {
            client.title = title;
            return this;
        }

        public Builder setSite(String site) {
            client.site = site;
            return this;
        }

        public Builder setSiteUrl(String siteUrl) {
            client.siteUrl = siteUrl;
            return this;
        }

        public Builder setLongitude(float longitude) {
            client.longitude = longitude;
            return this;
        }

        public Builder setLatitude(float latitude) {
            client.latitude = latitude;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            client.imageUrl = imageUrl;
            return this;
        }

        public Builder setImageArray(String[] imageArray) {
            client.imageArray = imageArray;
            return this;
        }

        public Builder setImageData(Bitmap imageData) {
            client.imageData = imageData;
            return this;
        }

        public Builder setImagePath(String imagePath) {
            client.imagePath = imagePath;
            return this;
        }

        public Builder setPlatform(String platform) {
            client.platform = platform;
            return this;
        }

        public Builder setComment(String comment) {
            client.comment = comment;
            return this;
        }

        public Builder setAddress(String address) {
            client.address = address;
            return this;
        }

        public Builder setMusicUrl(String musicUrl) {
            client.musicUrl = musicUrl;
            return this;
        }

        public Builder setInstallUrl(String installUrl) {
            client.installUrl = installUrl;
            return this;
        }

        public Builder setSilent(boolean silent) {
            client.silent = silent;
            return this;
        }

        public OneKeyShareClient build() {
            return client;
        }
    }

}

