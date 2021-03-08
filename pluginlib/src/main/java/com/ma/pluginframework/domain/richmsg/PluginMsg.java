package com.ma.pluginframework.domain.richmsg;


import android.content.Context;
import android.content.Intent;

import com.ma.pluginframework.domain.richmsg.section.PluginATSection;
import com.ma.pluginframework.domain.richmsg.section.PluginImageSection;
import com.ma.pluginframework.domain.richmsg.section.PluginMsgSection;
import com.ma.pluginframework.domain.richmsg.section.PluginTextSection;
import com.ma.pluginframework.domain.richmsg.section.PluginVoiceSection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class PluginMsg {
    private List<PluginMsgSection> pluginMsgSections;


    public PluginMsg() {
        this.pluginMsgSections = new ArrayList<>();
    }

    public List<PluginMsgSection> getPluginMsgSections() {
        return pluginMsgSections;
    }

    public void setPluginMsgSections(List<PluginMsgSection> pluginMsgSections) {
        this.pluginMsgSections = pluginMsgSections;
    }

    /**
     * 是否可用
     *
     * @return
     */
    public boolean available() {
        int ava = 0;
        if (pluginMsgSections != null) {
            for (PluginMsgSection pluginMsgSection : this.pluginMsgSections) {
                if (pluginMsgSection.available()) {
                    ava++;
                }
            }
        }
        return ava > 0;
    }


    /**
     * 读取
     *
     * @param json
     * @return
     * @throws JSONException
     */
    public static PluginMsg readFrom(JSONObject json) throws JSONException {
        PluginMsg pluginMsg = new PluginMsg();
        JSONArray arr = json.getJSONArray("localMsgSections");
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            int type = obj.getInt("type");
            if (type == PluginMsgSection.TYPE_IMAGE) {
                pluginMsg.getPluginMsgSections().add(new PluginImageSection().readFromJSONObject(obj));
            } else if (type == PluginMsgSection.TYPE_TEXT) {
                pluginMsg.getPluginMsgSections().add(new PluginTextSection().readFromJSONObject(obj));
            } else if (type == PluginMsgSection.TYPE_VOICE) {
                pluginMsg.getPluginMsgSections().add(new PluginVoiceSection().readFromJSONObject(obj));
            } else if (type == PluginMsgSection.TYPE_AT) {
                pluginMsg.getPluginMsgSections().add(new PluginATSection().readFromJSONObject(obj));

            }
        }
        return pluginMsg;
    }

    /**
     * 写入
     *
     * @return
     * @throws JSONException
     */
    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        JSONArray ss = new JSONArray();
        for (PluginMsgSection section : getPluginMsgSections()) {
            ss.put(section.toJSONObject());
        }
        json.put("localMsgSections", ss);
        return json;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (PluginMsgSection section : getPluginMsgSections()) {
            sb.append(section.toString());
        }
        return sb.toString();
    }


    /**
     * 进行文件转换
     *
     * @param intent
     * @param context
     */
    public void processFileToUri(Intent intent, Context context) {
        for (PluginMsgSection section : getPluginMsgSections()) {
            section.processFileToUri(intent, context);
        }
    }


}
