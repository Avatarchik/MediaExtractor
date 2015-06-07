package com.myriadmobile.mediaextractor.file;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import android.util.Log;

import com.myriadmobile.mediaextractor.extension.ExtensionTypeResolver;
import com.myriadmobile.mediaextractor.extension.FusedExtensionResolver;
import com.myriadmobile.mediaextractor.scheme.UnsupportedSchemeException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileResolver {

    private static final String TAG = FileResolver.class.getName();
    private static final String READ_FLAG = "r";
    private final Context context;
    private final FusedExtensionResolver extensionResolver;

    public FileResolver(Context context) {
        this.context = context;
        this.extensionResolver = new FusedExtensionResolver(context);
    }

    /**
     * Open a raw file descriptor to access data under a URI.
     * @param uri The desired URI to open.
     * @return a new ParcelFileDescriptor pointing to the file. You own this descriptor and are responsible for closing it when done.
     * @throws FileNotFoundException Throws if no file exists under the URI or the mode is invalid.
     */
    protected ParcelFileDescriptor resolveFileDescriptor(Uri uri) throws FileNotFoundException, NullPointerException {
        return context.getContentResolver().openFileDescriptor(uri, READ_FLAG);
    }

    /**
     * Attempt to write contents of provided Uri to File.
     * @param uri The desired URI to open.
     * @return File object representing provided {@param uri} if existent
     * @throws IOException
     */
    @Nullable
    public File resolve(Uri uri) throws IOException {

        ParcelFileDescriptor pfd = null;
        try {
            pfd = resolveFileDescriptor(uri);
        } catch (FileNotFoundException e) {
            Log.d(TAG, "no file exists under the URI or the mode is invalid.", e);
        } catch (NullPointerException e) {
            Log.d(TAG, "provided Uri was null", e);
        }

        File temp = null;
        if(pfd != null) {

            try {
                temp = new File(context.getCacheDir(), "temp." + extensionResolver.resolveExtension(uri));
            } catch (UnsupportedSchemeException e) {
                e.printStackTrace();
            }

            // TODO: move this into it's own Thread w/ callbacks when ready.
            // Allocate streams and channels to facilitate streaming of bytes.
            FileChannel from = new FileInputStream(pfd.getFileDescriptor()).getChannel();
            FileChannel to = new FileOutputStream(temp.getPath()).getChannel();
            to.transferFrom(from, 0, from.size());

            // Close all allocated streams and channels.
            from.close();
            to.close();
            pfd.close();
        }

        return temp;
    }


}
