package com.ma.pluginframework.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 好友
 */
public class Buddy extends HostContact implements Parcelable {

    //新增
    private int groupid = 0;//好友分组下标
    private int faceId;
    private byte sqqtype;
    private byte status;
    private byte memberLevel;
    private byte isMqqOnLine;
    private byte sqqOnLineState;
    private byte isIphoneOnline;
    private byte detalStatusFlag;
    private byte sqqOnLineStateV2;
    private String sShowName;
    private byte isRemark;
    private byte specialFlag;
    private int iTermType;
    private byte network;
    private long uAbiFlag;
    private long ulFaceAddonId;
    private int eNetworkType;
    private long uVipFont;
    private int eIconType;
    private String termDesc;
    private long uColorRing;
    private byte apolloFlag;
    private long uApolloTimestamp;
    private byte sex;
    private long uFounderFont;
    private String eimId;
    private String eimMobile;
    private byte olympicTorch;
    private long uApolloSignTime;
    private long uLaviUin;
    private long uTagUpdateTime;
    private long uGameLastLoginTime;
    private long uGameAppid;

    public Buddy(boolean robotOpen, boolean sendOpen, long uin, String name, String mark, String extra) {
        super(robotOpen, sendOpen, uin, name, mark, extra);
    }

    protected Buddy(Parcel in) {
        super(in);
        groupid = in.readInt();
        faceId = in.readInt();
        sqqtype = in.readByte();
        status = in.readByte();
        memberLevel = in.readByte();
        isMqqOnLine = in.readByte();
        sqqOnLineState = in.readByte();
        isIphoneOnline = in.readByte();
        detalStatusFlag = in.readByte();
        sqqOnLineStateV2 = in.readByte();
        sShowName = in.readString();
        isRemark = in.readByte();
        specialFlag = in.readByte();
        iTermType = in.readInt();
        network = in.readByte();
        uAbiFlag = in.readLong();
        ulFaceAddonId = in.readLong();
        eNetworkType = in.readInt();
        uVipFont = in.readLong();
        eIconType = in.readInt();
        termDesc = in.readString();
        uColorRing = in.readLong();
        apolloFlag = in.readByte();
        uApolloTimestamp = in.readLong();
        sex = in.readByte();
        uFounderFont = in.readLong();
        eimId = in.readString();
        eimMobile = in.readString();
        olympicTorch = in.readByte();
        uApolloSignTime = in.readLong();
        uLaviUin = in.readLong();
        uTagUpdateTime = in.readLong();
        uGameLastLoginTime = in.readLong();
        uGameAppid = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(groupid);
        dest.writeInt(faceId);
        dest.writeByte(sqqtype);
        dest.writeByte(status);
        dest.writeByte(memberLevel);
        dest.writeByte(isMqqOnLine);
        dest.writeByte(sqqOnLineState);
        dest.writeByte(isIphoneOnline);
        dest.writeByte(detalStatusFlag);
        dest.writeByte(sqqOnLineStateV2);
        dest.writeString(sShowName);
        dest.writeByte(isRemark);
        dest.writeByte(specialFlag);
        dest.writeInt(iTermType);
        dest.writeByte(network);
        dest.writeLong(uAbiFlag);
        dest.writeLong(ulFaceAddonId);
        dest.writeInt(eNetworkType);
        dest.writeLong(uVipFont);
        dest.writeInt(eIconType);
        dest.writeString(termDesc);
        dest.writeLong(uColorRing);
        dest.writeByte(apolloFlag);
        dest.writeLong(uApolloTimestamp);
        dest.writeByte(sex);
        dest.writeLong(uFounderFont);
        dest.writeString(eimId);
        dest.writeString(eimMobile);
        dest.writeByte(olympicTorch);
        dest.writeLong(uApolloSignTime);
        dest.writeLong(uLaviUin);
        dest.writeLong(uTagUpdateTime);
        dest.writeLong(uGameLastLoginTime);
        dest.writeLong(uGameAppid);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Buddy> CREATOR = new Creator<Buddy>() {
        @Override
        public Buddy createFromParcel(Parcel in) {
            return new Buddy(in);
        }

        @Override
        public Buddy[] newArray(int size) {
            return new Buddy[size];
        }
    };

    @Override
    public String toString() {
        return "Buddy{" +
                "groupid=" + groupid +
                ", faceId=" + faceId +
                ", sqqtype=" + sqqtype +
                ", status=" + status +
                ", memberLevel=" + memberLevel +
                ", isMqqOnLine=" + isMqqOnLine +
                ", sqqOnLineState=" + sqqOnLineState +
                ", isIphoneOnline=" + isIphoneOnline +
                ", detalStatusFlag=" + detalStatusFlag +
                ", sqqOnLineStateV2=" + sqqOnLineStateV2 +
                ", sShowName='" + sShowName + '\'' +
                ", isRemark=" + isRemark +
                ", specialFlag=" + specialFlag +
                ", iTermType=" + iTermType +
                ", network=" + network +
                ", uAbiFlag=" + uAbiFlag +
                ", ulFaceAddonId=" + ulFaceAddonId +
                ", eNetworkType=" + eNetworkType +
                ", uVipFont=" + uVipFont +
                ", eIconType=" + eIconType +
                ", termDesc='" + termDesc + '\'' +
                ", uColorRing=" + uColorRing +
                ", apolloFlag=" + apolloFlag +
                ", uApolloTimestamp=" + uApolloTimestamp +
                ", sex=" + sex +
                ", uFounderFont=" + uFounderFont +
                ", eimId='" + eimId + '\'' +
                ", eimMobile='" + eimMobile + '\'' +
                ", olympicTorch=" + olympicTorch +
                ", uApolloSignTime=" + uApolloSignTime +
                ", uLaviUin=" + uLaviUin +
                ", uTagUpdateTime=" + uTagUpdateTime +
                ", uGameLastLoginTime=" + uGameLastLoginTime +
                ", uGameAppid=" + uGameAppid +
                "} " + super.toString();
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public int getFaceId() {
        return faceId;
    }

    public void setFaceId(int faceId) {
        this.faceId = faceId;
    }

    public byte getSqqtype() {
        return sqqtype;
    }

    public void setSqqtype(byte sqqtype) {
        this.sqqtype = sqqtype;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public byte getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(byte memberLevel) {
        this.memberLevel = memberLevel;
    }

    public byte getIsMqqOnLine() {
        return isMqqOnLine;
    }

    public void setIsMqqOnLine(byte isMqqOnLine) {
        this.isMqqOnLine = isMqqOnLine;
    }

    public byte getSqqOnLineState() {
        return sqqOnLineState;
    }

    public void setSqqOnLineState(byte sqqOnLineState) {
        this.sqqOnLineState = sqqOnLineState;
    }

    public byte getIsIphoneOnline() {
        return isIphoneOnline;
    }

    public void setIsIphoneOnline(byte isIphoneOnline) {
        this.isIphoneOnline = isIphoneOnline;
    }

    public byte getDetalStatusFlag() {
        return detalStatusFlag;
    }

    public void setDetalStatusFlag(byte detalStatusFlag) {
        this.detalStatusFlag = detalStatusFlag;
    }

    public byte getSqqOnLineStateV2() {
        return sqqOnLineStateV2;
    }

    public void setSqqOnLineStateV2(byte sqqOnLineStateV2) {
        this.sqqOnLineStateV2 = sqqOnLineStateV2;
    }

    public String getsShowName() {
        return sShowName;
    }

    public void setsShowName(String sShowName) {
        this.sShowName = sShowName;
    }

    public byte getIsRemark() {
        return isRemark;
    }

    public void setIsRemark(byte isRemark) {
        this.isRemark = isRemark;
    }

    public byte getSpecialFlag() {
        return specialFlag;
    }

    public void setSpecialFlag(byte specialFlag) {
        this.specialFlag = specialFlag;
    }

    public int getiTermType() {
        return iTermType;
    }

    public void setiTermType(int iTermType) {
        this.iTermType = iTermType;
    }

    public byte getNetwork() {
        return network;
    }

    public void setNetwork(byte network) {
        this.network = network;
    }

    public long getuAbiFlag() {
        return uAbiFlag;
    }

    public void setuAbiFlag(long uAbiFlag) {
        this.uAbiFlag = uAbiFlag;
    }

    public long getUlFaceAddonId() {
        return ulFaceAddonId;
    }

    public void setUlFaceAddonId(long ulFaceAddonId) {
        this.ulFaceAddonId = ulFaceAddonId;
    }

    public int geteNetworkType() {
        return eNetworkType;
    }

    public void seteNetworkType(int eNetworkType) {
        this.eNetworkType = eNetworkType;
    }

    public long getuVipFont() {
        return uVipFont;
    }

    public void setuVipFont(long uVipFont) {
        this.uVipFont = uVipFont;
    }

    public int geteIconType() {
        return eIconType;
    }

    public void seteIconType(int eIconType) {
        this.eIconType = eIconType;
    }

    public String getTermDesc() {
        return termDesc;
    }

    public void setTermDesc(String termDesc) {
        this.termDesc = termDesc;
    }

    public long getuColorRing() {
        return uColorRing;
    }

    public void setuColorRing(long uColorRing) {
        this.uColorRing = uColorRing;
    }

    public byte getApolloFlag() {
        return apolloFlag;
    }

    public void setApolloFlag(byte apolloFlag) {
        this.apolloFlag = apolloFlag;
    }

    public long getuApolloTimestamp() {
        return uApolloTimestamp;
    }

    public void setuApolloTimestamp(long uApolloTimestamp) {
        this.uApolloTimestamp = uApolloTimestamp;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public long getuFounderFont() {
        return uFounderFont;
    }

    public void setuFounderFont(long uFounderFont) {
        this.uFounderFont = uFounderFont;
    }

    public String getEimId() {
        return eimId;
    }

    public void setEimId(String eimId) {
        this.eimId = eimId;
    }

    public String getEimMobile() {
        return eimMobile;
    }

    public void setEimMobile(String eimMobile) {
        this.eimMobile = eimMobile;
    }

    public byte getOlympicTorch() {
        return olympicTorch;
    }

    public void setOlympicTorch(byte olympicTorch) {
        this.olympicTorch = olympicTorch;
    }

    public long getuApolloSignTime() {
        return uApolloSignTime;
    }

    public void setuApolloSignTime(long uApolloSignTime) {
        this.uApolloSignTime = uApolloSignTime;
    }

    public long getuLaviUin() {
        return uLaviUin;
    }

    public void setuLaviUin(long uLaviUin) {
        this.uLaviUin = uLaviUin;
    }

    public long getuTagUpdateTime() {
        return uTagUpdateTime;
    }

    public void setuTagUpdateTime(long uTagUpdateTime) {
        this.uTagUpdateTime = uTagUpdateTime;
    }

    public long getuGameLastLoginTime() {
        return uGameLastLoginTime;
    }

    public void setuGameLastLoginTime(long uGameLastLoginTime) {
        this.uGameLastLoginTime = uGameLastLoginTime;
    }

    public long getuGameAppid() {
        return uGameAppid;
    }

    public void setuGameAppid(long uGameAppid) {
        this.uGameAppid = uGameAppid;
    }
}
