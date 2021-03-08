package com.ma.pluginframework.domain.richmsg.section;

import org.json.JSONException;
import org.json.JSONObject;

public class PluginTextSection extends PluginMsgSection {
    //region 需要存储的字段
    private String text;
    //endregion


    public PluginTextSection(String text) {
        this.text = text;
    }

    public PluginTextSection() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean available() {
        return text != null && text.length() > 0;
    }

    @Override
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("text", getText());
        jsonObject.put("type", TYPE_TEXT);
        return jsonObject;
    }

    @Override
    public PluginMsgSection readFromJSONObject(JSONObject jsonObject) {
        PluginTextSection textLocalSection = new PluginTextSection();
        textLocalSection.setText(jsonObject.optString("text", null));
        return textLocalSection;
    }


    @Override
    public String toString() {
        return text;
    }
}
