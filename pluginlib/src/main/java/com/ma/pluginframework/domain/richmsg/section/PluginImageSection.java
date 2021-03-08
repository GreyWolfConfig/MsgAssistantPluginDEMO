package com.ma.pluginframework.domain.richmsg.section;


import android.content.Context;
import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class PluginImageSection extends PluginMsgSection {
    //region 需要存储的字段
    private String imagePath;
    //endregion


    public PluginImageSection(String imagePath) {
        this.imagePath = imagePath;
    }

    public PluginImageSection() {
    }

    @Override
    public boolean available() {
        return (imagePath != null && new File(imagePath).exists());
    }

    @Override
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("imagePath", imagePath);
        jsonObject.put("type", TYPE_IMAGE);
        return jsonObject;
    }

    @Override
    public PluginMsgSection readFromJSONObject(JSONObject jsonObject) {
        PluginImageSection pluginImageSection = new PluginImageSection();
        pluginImageSection.setImagePath(jsonObject.optString("imagePath", null));
        return pluginImageSection;
    }


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    @Override
    public String toString() {
        return "[图片]";
    }


    @Override
    public void processFileToUri(Intent intent, Context context) {
        super.processFileToUri(intent, context);

        this.setImagePath(file2Uri(intent, context, this.getImagePath()));

    }
}
