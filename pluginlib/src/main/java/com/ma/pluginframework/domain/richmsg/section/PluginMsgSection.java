package com.ma.pluginframework.domain.richmsg.section;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.ma.pluginframework.PluginHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.UUID;


public abstract class PluginMsgSection {
    public static final int TYPE_IMAGE = 1;
    public static final int TYPE_TEXT = 2;
    public static final int TYPE_VOICE = 3;
    public static final int TYPE_AT = 4;

    public abstract boolean available();

    public abstract JSONObject toJSONObject() throws JSONException;

    public abstract PluginMsgSection readFromJSONObject(JSONObject jsonObject);

    public void processFileToUri(Intent intent, Context context) {

    }

    protected String file2Uri(Intent intent, Context context, String path) {
        File file = new File(path);
        Uri fileUri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            fileUri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);
            context.grantUriPermission(PluginHelper.SERVER_PN, fileUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            fileUri = Uri.fromFile(file);
        }
//        String guid = UUID.randomUUID().toString();
//        intent.putExtra(guid, fileUri);
//        return guid;
        return fileUri.toString();
    }
}
