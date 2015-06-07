package com.myriadmobile.mediaextractor.extension;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.myriadmobile.mediaextractor.scheme.Type;
import com.myriadmobile.mediaextractor.scheme.UnsupportedSchemeException;
import com.myriadmobile.mediaextractor.scheme.UriUtils;

import java.util.EnumMap;


public class FusedExtensionResolver {

    private final EnumMap<Type, ExtensionTypeResolver> extensionResolvers;

    public FusedExtensionResolver(Context context) {
        extensionResolvers = new EnumMap<>(Type.class);
        extensionResolvers.put(Type.CONTENT, new ContentExtensionResolver(context));
        extensionResolvers.put(Type.FILE, new FileExtensionResolver());
    }

    @Nullable
    protected Type resolveType(Uri uri) {
        return UriUtils.getUriTypeChecked(uri);
    }

    @Nullable
    public String resolveExtension(Uri uri) throws UnsupportedSchemeException {
        return extensionResolvers.get(resolveType(uri)).resolveExtensionType(uri);
    }
}
