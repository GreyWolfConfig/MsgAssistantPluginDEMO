package com.ma.pluginframework.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class AccountCookie implements Parcelable {
    private String domain;
    private String pskey;
    private String pt4Token;
    private String cookie;
    private String extra;

    public AccountCookie(String domain, String pskey, String pt4Token, String cookie, String extra) {
        this.domain = domain;
        this.pskey = pskey;
        this.pt4Token = pt4Token;
        this.cookie = cookie;
        this.extra = extra;
    }

    protected AccountCookie(Parcel in) {
        domain = in.readString();
        pskey = in.readString();
        pt4Token = in.readString();
        cookie = in.readString();
        extra = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(domain);
        dest.writeString(pskey);
        dest.writeString(pt4Token);
        dest.writeString(cookie);
        dest.writeString(extra);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AccountCookie> CREATOR = new Creator<AccountCookie>() {
        @Override
        public AccountCookie createFromParcel(Parcel in) {
            return new AccountCookie(in);
        }

        @Override
        public AccountCookie[] newArray(int size) {
            return new AccountCookie[size];
        }
    };

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPskey() {
        return pskey;
    }

    public void setPskey(String pskey) {
        this.pskey = pskey;
    }

    public String getPt4Token() {
        return pt4Token;
    }

    public void setPt4Token(String pt4Token) {
        this.pt4Token = pt4Token;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "AccountCookie{" +
                "domain='" + domain + '\'' +
                ", pskey='" + pskey + '\'' +
                ", pt4Token='" + pt4Token + '\'' +
                ", cookie='" + cookie + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}
