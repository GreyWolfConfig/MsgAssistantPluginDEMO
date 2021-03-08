package com.ma.pluginframework.domain.richmsg.section;

import android.content.Context;
import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class PluginVoiceSection extends PluginMsgSection {
    //slk文件
    private String path;


    public PluginVoiceSection() {
    }

    public PluginVoiceSection(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    @Override
    public boolean available() {
        return path != null && new File(path).exists();
    }

    @Override
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("videoPath", path);
        jsonObject.put("type", TYPE_VOICE);
        return jsonObject;
    }

    @Override
    public PluginMsgSection readFromJSONObject(JSONObject jsonObject) {
        return new PluginVoiceSection(jsonObject.optString("videoPath", null));
    }

    @Override
    public String toString() {
        return "[语音]";
    }

    @Override
    public void processFileToUri(Intent intent, Context context) {
        super.processFileToUri(intent, context);
        this.setPath(file2Uri(intent, context, this.getPath()));
    }
}
