package com.ma.pluginframework.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.ma.pluginframework.PluginHelper;

/**
 * 主程序信息
 */
public class HostInfo implements Parcelable {
    private String pn;
    private int vc;
    private String vn;
    private String svcName;
    private int pluginSdkVersion;
    private String extra;

    public HostInfo(String pn, int vc, String vn, String svcName, int pluginSdkVersion, String extra) {
        this.pn = pn;
        this.vc = vc;
        this.vn = vn;
        this.svcName = svcName;
        this.pluginSdkVersion = pluginSdkVersion;
        this.extra = extra;
    }

    protected HostInfo(Parcel in) {
        pn = in.readString();
        vc = in.readInt();
        vn = in.readString();
        svcName = in.readString();
        pluginSdkVersion = in.readInt();
        extra = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pn);
        dest.writeInt(vc);
        dest.writeString(vn);
        dest.writeString(svcName);
        dest.writeInt(pluginSdkVersion);
        dest.writeString(extra);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HostInfo> CREATOR = new Creator<HostInfo>() {
        @Override
        public HostInfo createFromParcel(Parcel in) {
            return new HostInfo(in);
        }

        @Override
        public HostInfo[] newArray(int size) {
            return new HostInfo[size];
        }
    };

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public int getVc() {
        return vc;
    }

    public void setVc(int vc) {
        this.vc = vc;
    }

    public String getVn() {
        return vn;
    }

    public void setVn(String vn) {
        this.vn = vn;
    }

    public String getSvcName() {
        return svcName;
    }

    public void setSvcName(String svcName) {
        this.svcName = svcName;
    }

    public int getPluginSdkVersion() {
        return pluginSdkVersion;
    }

    public void setPluginSdkVersion(int pluginSdkVersion) {
        this.pluginSdkVersion = pluginSdkVersion;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "HostInfo{" +
                "pn='" + pn + '\'' +
                ", vc=" + vc +
                ", vn='" + vn + '\'' +
                ", svcName='" + svcName + '\'' +
                ", pluginSdkVersion=" + pluginSdkVersion +
                ", extra='" + extra + '\'' +
                '}';
    }
}
