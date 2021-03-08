package com.ma.pluginframework.domain.richmsg.section;


import org.json.JSONException;
import org.json.JSONObject;

public class PluginATSection extends PluginMsgSection {
    private String mark;
    private long uin;


    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public long getUin() {
        return uin;
    }

    public void setUin(long uin) {
        this.uin = uin;
    }

    @Override
    public boolean available() {
        return mark != null;
    }

    @Override
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mark", getMark());
        jsonObject.put("uin", getUin());
        jsonObject.put("type", TYPE_AT);
        return jsonObject;
    }

    @Override
    public PluginMsgSection readFromJSONObject(JSONObject jsonObject) {
        PluginATSection pluginAtSection = new PluginATSection();
        pluginAtSection.setMark(jsonObject.optString("mark", null));
        pluginAtSection.setUin(jsonObject.optLong("uin", 0L));
        return pluginAtSection;
    }

    @Override
    public String toString() {
        return "@" + getMark();
    }
}
