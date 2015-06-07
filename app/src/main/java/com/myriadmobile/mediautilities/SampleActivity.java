package com.myriadmobile.mediautilities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.myriadmobile.mediaextractor.file.FileResolver;
import com.myriadmobile.mediaextractor.mime.ContentMimeTypeResolver;
import com.myriadmobile.mediaextractor.mime.MimeTypeResolver;
import com.myriadmobile.mediaextractor.scheme.UriUtils;

import java.io.File;

public class SampleActivity extends AppCompatActivity {

    private IntentFactory intentFactory;
    private Uri galleryResult;

    private ImageView target;

    private MimeTypeResolver contentTypeResolver = new ContentMimeTypeResolver(this);
    private final FileResolver fileResolver = new FileResolver(this);

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

            galleryResult = Uri.parse("yahoo.com");
            try {
                File f = fileResolver.resolve(galleryResult);
                Log.wtf("path", galleryResult.getPath());
                Log.wtf("size", f.length() + "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            intentFactory.onCameraResult(resultCode, data);
        }
    }
}
