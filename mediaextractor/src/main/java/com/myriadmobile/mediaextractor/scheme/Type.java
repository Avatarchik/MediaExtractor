package com.myriadmobile.mediaextractor.scheme;

import android.content.ContentResolver;
import android.support.annotation.Nullable;

public enum Type {
    CONTENT(ContentResolver.SCHEME_CONTENT),
    FILE(ContentResolver.SCHEME_FILE);

    private String name;

    Type(String name) {
        this.name = name;
    }

    public final String getScheme() {
        return this.name;
    }

    @Nullable
    public static Type valueFromScheme(String scheme) {
        for(Type element : values()) {
            if(element.getScheme().equals(scheme)) {
                return element;
            }
        }

        return null;
    }
}
