package com.myriadmobile.mediaextractor.retrofit;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.myriadmobile.mediaextractor.file.FileResolver;
import com.myriadmobile.mediaextractor.mime.FusedMimeResolver;
import com.myriadmobile.mediaextractor.scheme.UnsupportedSchemeException;

import java.io.IOException;

import retrofit.mime.TypedFile;

public class TypedFileFactory {

    private final FusedMimeResolver mimeResolver;
    private final FileResolver fileResolver;

    public TypedFileFactory(@NonNull Context context) {
        mimeResolver = new FusedMimeResolver(context);
        fileResolver = new FileResolver(context);
    }

    public TypedFileFactory(@NonNull FusedMimeResolver mimeResolver, @NonNull FileResolver fileResolver) {
        this.mimeResolver = mimeResolver;
        this.fileResolver = fileResolver;
    }

    @Nullable
    public TypedFile create(Uri uri) {
        TypedFile result = null;

        try {
            result = new TypedFile(mimeResolver.resolveMimeType(uri),
                    fileResolver.resolve(uri));
        } catch (UnsupportedSchemeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return result;
    }
}
