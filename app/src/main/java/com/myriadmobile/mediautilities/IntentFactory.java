package com.myriadmobile.mediautilities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;

import java.util.List;


public class IntentFactory {

    private static final String LINKEDIN_PACKAGE_NAME = "com.linkedin.android";

    private final Context context;

    public IntentFactory(Application context) {
        this.context = context;
    }

    @Nullable
    public Intent openWebsite(Uri webUri) {
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webUri);
        if(canHandleIntent(context, webIntent)) {
            return webIntent;
        } else {
            return null;
        }
    }

    @Nullable
    public Intent openVideo(Uri videoUri) {
        Intent videoStreamIntent = new Intent(Intent.ACTION_VIEW, videoUri);

        if(canHandleIntent(context, videoStreamIntent)) {
            return videoStreamIntent;
        } else {
            return null;
        }
    }

    @Nullable
    public Intent openYoutubeVideo(Uri youtubeVideoUri) {
        Intent openYoutubeIntent = new Intent(Intent.ACTION_VIEW);
        openYoutubeIntent.setData(youtubeVideoUri);

        if(canHandleIntent(context, openYoutubeIntent)) {
            return openYoutubeIntent;
        } else {
            return null;
        }
    }

    @Nullable
    public Intent shareToLinkedIn(String text) {
        if(doesPackageExist(context, LINKEDIN_PACKAGE_NAME)) {
            Intent linkedIn = new Intent(Intent.ACTION_SEND);
            linkedIn.setPackage(LINKEDIN_PACKAGE_NAME);
            linkedIn.setType("text/*");
            linkedIn.putExtra(Intent.EXTRA_TEXT, text);
            return linkedIn;
        } else {
            return null;
        }
    }

    @Nullable
    public static Intent shareToLinkedIn(Context context, String text) {
        if(doesPackageExist(context, LINKEDIN_PACKAGE_NAME)) {
            Intent linkedIn = new Intent(Intent.ACTION_SEND);
            linkedIn.setPackage(LINKEDIN_PACKAGE_NAME);
            linkedIn.setType("text/*");
            linkedIn.putExtra(Intent.EXTRA_TEXT, text);
            return linkedIn;
        } else {
            return null;
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public Intent getGalleryImage(Uri galleryUri) {
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");

        if (Build.VERSION.SDK_INT >= 19) {
            galleryIntent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);
        } else {
            galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        }

        galleryIntent.putExtra(MediaStore.EXTRA_OUTPUT, galleryUri);
        return galleryIntent;
    }

    public Intent getGalleryVideo() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("video/*");
        return galleryIntent;
    }

    @Nullable
    public Uri onGalleryResult(int resultCode, Intent data) {
        Uri payload = null;
        switch (resultCode) {
            case Activity.RESULT_OK:
                if (data != null) {
                    payload = data.getData();
                }
            case Activity.RESULT_CANCELED:
                break;
            case Activity.RESULT_FIRST_USER:
                break;

        }
        return payload;
    }

    public Intent captureImage() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        return cameraIntent;
    }

    @Nullable
    public Uri onCameraResult(int resultCode, Intent data) {
        return null;
    }

    /**
     * Am I looking to execute this Intent with a particular application, does it exist?
     */
    public static boolean doesPackageExist(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> applicationInfoList = pm.getInstalledApplications(0);
        for(ApplicationInfo appInfo : applicationInfoList) {
            if(appInfo.packageName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Ensures that Intent will resolve to at least one Activity to handle it.
     */
    public static boolean canHandleIntent(Context context, Intent intent) {
        PackageManager pm = context.getPackageManager();
        return intent.resolveActivity(pm) != null;
    }

    /**
     * Does an Intent actually resolve to an Activity?
     */
    public static boolean isIntentSafe(Context context, Intent intent) {
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> responsibleActivities = pm.queryIntentActivities(intent, 0);
        return responsibleActivities.size() > 0;
    }
}
