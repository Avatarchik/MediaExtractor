package com.myriadmobile.mediautilities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.myriadmobile.mediaextractor.mime.ContentMimeTypeResolver;
import com.myriadmobile.mediaextractor.scheme.UriUtils;

public class SampleActivity extends AppCompatActivity {

    private IntentFactory intentFactory;
    private Uri galleryResult;

    private ImageView target;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        intentFactory = new IntentFactory(this.getApplication());

        target = (ImageView) findViewById(R.id.image);

        findViewById(R.id.camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intentFactory.captureImage(), 1001);
            }
        });

        findViewById(R.id.gallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intentFactory.getGalleryImage(galleryResult), 1337);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1337) {
            galleryResult = intentFactory.onGalleryResult(resultCode, data);
            target.setImageURI(galleryResult);

            try {
                Log.wtf("result", UriUtils.getUriType(galleryResult).getScheme());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            intentFactory.onCameraResult(resultCode, data);
        }
    }
}
