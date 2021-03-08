package com.ma.pluginframework.domain;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * 插件信息
 */
public class PluginInfo implements Parcelable {
    private String name;
    private String icon;
    private String pn;
    private String vn;
    private int vc;
    private int minPluginSdkVersion;
    private String author;
    private String desc;
    private String extra;


    public PluginInfo(String icon, String author, String desc, String extra) {
        this.icon = icon;
        this.author = author;
        this.desc = desc;
        this.extra = extra;
    }

    public PluginInfo(String name, String icon, String pn, String vn, int vc, int minPluginSdkVersion, String author, String desc, String extra) {
        this.name = name;
        this.icon = icon;
        this.pn = pn;
        this.vn = vn;
        this.vc = vc;
        this.minPluginSdkVersion = minPluginSdkVersion;
        this.author = author;
        this.desc = desc;
        this.extra = extra;
    }

    protected PluginInfo(Parcel in) {
        name = in.readString();
        icon = in.readString();
        pn = in.readString();
        vn = in.readString();
        vc = in.readInt();
        minPluginSdkVersion = in.readInt();
        author = in.readString();
        desc = in.readString();
        extra = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(icon);
        dest.writeString(pn);
        dest.writeString(vn);
        dest.writeInt(vc);
        dest.writeInt(minPluginSdkVersion);
        dest.writeString(author);
        dest.writeString(desc);
        dest.writeString(extra);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PluginInfo> CREATOR = new Creator<PluginInfo>() {
        @Override
        public PluginInfo createFromParcel(Parcel in) {
            return new PluginInfo(in);
        }

        @Override
        public PluginInfo[] newArray(int size) {
            return new PluginInfo[size];
        }
    };

    @Override
    public String toString() {
        return "PluginInfo{" +
                "name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", pn='" + pn + '\'' +
                ", vn='" + vn + '\'' +
                ", vc=" + vc +
                ", minPluginSdkVersion=" + minPluginSdkVersion +
                ", author='" + author + '\'' +
                ", desc='" + desc + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getVn() {
        return vn;
    }

    public void setVn(String vn) {
        this.vn = vn;
    }

    public int getVc() {
        return vc;
    }

    public void setVc(int vc) {
        this.vc = vc;
    }

    public int getMinPluginSdkVersion() {
        return minPluginSdkVersion;
    }

    public void setMinPluginSdkVersion(int minPluginSdkVersion) {
        this.minPluginSdkVersion = minPluginSdkVersion;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
