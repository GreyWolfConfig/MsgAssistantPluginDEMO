package com.ma.pluginframework.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 群成员
 */
public class TroopMember extends HostContact implements Parcelable {
    /**
     * face 没什么卵用
     */
    private long face;
    /**
     * 权限
     */
    private TroopPermission permission;


    //新增字段
    private byte age;
    private byte gender;
    private byte status;
    private String sShowName;
    private String sName;
    private byte cGender;
    private String sPhone;
    private String sEmail;
    private String sMemo;
    private String autoRemark;
    private long dwMemberLevel;
    private long dwJoinTime;
    private long dwLastSpeakTime;
    private long dwCreditLevel;
    private long dwFlag;
    private long dwFlagExt;
    private long dwPoint;
    private byte concerned;
    private byte shielded;
    private String sSpecialTitle;
    private long dwSpecialTitleExpireTime;
    private String job;
    private long dwShutupTimestap;


    public TroopMember(boolean robotOpen, boolean sendOpen, long uin, String name, String mark, String extra, long face, TroopPermission permission) {
        super(robotOpen, sendOpen, uin, name, mark, extra);
        this.face = face;
        this.permission = permission;
    }

    protected TroopMember(Parcel in) {
        super(in);
        face = in.readLong();
        age = in.readByte();
        gender = in.readByte();
        status = in.readByte();
        sShowName = in.readString();
        sName = in.readString();
        cGender = in.readByte();
        sPhone = in.readString();
        sEmail = in.readString();
        sMemo = in.readString();
        autoRemark = in.readString();
        dwMemberLevel = in.readLong();
        dwJoinTime = in.readLong();
        dwLastSpeakTime = in.readLong();
        dwCreditLevel = in.readLong();
        dwFlag = in.readLong();
        dwFlagExt = in.readLong();
        dwPoint = in.readLong();
        concerned = in.readByte();
        shielded = in.readByte();
        sSpecialTitle = in.readString();
        dwSpecialTitleExpireTime = in.readLong();
        job = in.readString();
        dwShutupTimestap = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeLong(face);
        dest.writeByte(age);
        dest.writeByte(gender);
        dest.writeByte(status);
        dest.writeString(sShowName);
        dest.writeString(sName);
        dest.writeByte(cGender);
        dest.writeString(sPhone);
        dest.writeString(sEmail);
        dest.writeString(sMemo);
        dest.writeString(autoRemark);
        dest.writeLong(dwMemberLevel);
        dest.writeLong(dwJoinTime);
        dest.writeLong(dwLastSpeakTime);
        dest.writeLong(dwCreditLevel);
        dest.writeLong(dwFlag);
        dest.writeLong(dwFlagExt);
        dest.writeLong(dwPoint);
        dest.writeByte(concerned);
        dest.writeByte(shielded);
        dest.writeString(sSpecialTitle);
        dest.writeLong(dwSpecialTitleExpireTime);
        dest.writeString(job);
        dest.writeLong(dwShutupTimestap);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TroopMember> CREATOR = new Creator<TroopMember>() {
        @Override
        public TroopMember createFromParcel(Parcel in) {
            return new TroopMember(in);
        }

        @Override
        public TroopMember[] newArray(int size) {
            return new TroopMember[size];
        }
    };

    @Override
    public String toString() {
        return "TroopMember{" +
                "face=" + face +
                ", permission=" + permission +
                ", age=" + age +
                ", gender=" + gender +
                ", status=" + status +
                ", sShowName='" + sShowName + '\'' +
                ", sName='" + sName + '\'' +
                ", cGender=" + cGender +
                ", sPhone='" + sPhone + '\'' +
                ", sEmail='" + sEmail + '\'' +
                ", sMemo='" + sMemo + '\'' +
                ", autoRemark='" + autoRemark + '\'' +
                ", dwMemberLevel=" + dwMemberLevel +
                ", dwJoinTime=" + dwJoinTime +
                ", dwLastSpeakTime=" + dwLastSpeakTime +
                ", dwCreditLevel=" + dwCreditLevel +
                ", dwFlag=" + dwFlag +
                ", dwFlagExt=" + dwFlagExt +
                ", dwPoint=" + dwPoint +
                ", concerned=" + concerned +
                ", shielded=" + shielded +
                ", sSpecialTitle='" + sSpecialTitle + '\'' +
                ", dwSpecialTitleExpireTime=" + dwSpecialTitleExpireTime +
                ", job='" + job + '\'' +
                ", dwShutupTimestap=" + dwShutupTimestap +
                "} " + super.toString();
    }

    public long getFace() {
        return face;
    }

    public void setFace(long face) {
        this.face = face;
    }

    public TroopPermission getPermission() {
        return permission;
    }

    public void setPermission(TroopPermission permission) {
        this.permission = permission;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getsShowName() {
        return sShowName;
    }

    public void setsShowName(String sShowName) {
        this.sShowName = sShowName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public byte getcGender() {
        return cGender;
    }

    public void setcGender(byte cGender) {
        this.cGender = cGender;
    }

    public String getsPhone() {
        return sPhone;
    }

    public void setsPhone(String sPhone) {
        this.sPhone = sPhone;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsMemo() {
        return sMemo;
    }

    public void setsMemo(String sMemo) {
        this.sMemo = sMemo;
    }

    public String getAutoRemark() {
        return autoRemark;
    }

    public void setAutoRemark(String autoRemark) {
        this.autoRemark = autoRemark;
    }

    public long getDwMemberLevel() {
        return dwMemberLevel;
    }

    public void setDwMemberLevel(long dwMemberLevel) {
        this.dwMemberLevel = dwMemberLevel;
    }

    public long getDwJoinTime() {
        return dwJoinTime;
    }

    public void setDwJoinTime(long dwJoinTime) {
        this.dwJoinTime = dwJoinTime;
    }

    public long getDwLastSpeakTime() {
        return dwLastSpeakTime;
    }

    public void setDwLastSpeakTime(long dwLastSpeakTime) {
        this.dwLastSpeakTime = dwLastSpeakTime;
    }

    public long getDwCreditLevel() {
        return dwCreditLevel;
    }

    public void setDwCreditLevel(long dwCreditLevel) {
        this.dwCreditLevel = dwCreditLevel;
    }

    public long getDwFlag() {
        return dwFlag;
    }

    public void setDwFlag(long dwFlag) {
        this.dwFlag = dwFlag;
    }

    public long getDwFlagExt() {
        return dwFlagExt;
    }

    public void setDwFlagExt(long dwFlagExt) {
        this.dwFlagExt = dwFlagExt;
    }

    public long getDwPoint() {
        return dwPoint;
    }

    public void setDwPoint(long dwPoint) {
        this.dwPoint = dwPoint;
    }

    public byte getConcerned() {
        return concerned;
    }

    public void setConcerned(byte concerned) {
        this.concerned = concerned;
    }

    public byte getShielded() {
        return shielded;
    }

    public void setShielded(byte shielded) {
        this.shielded = shielded;
    }

    public String getsSpecialTitle() {
        return sSpecialTitle;
    }

    public void setsSpecialTitle(String sSpecialTitle) {
        this.sSpecialTitle = sSpecialTitle;
    }

    public long getDwSpecialTitleExpireTime() {
        return dwSpecialTitleExpireTime;
    }

    public void setDwSpecialTitleExpireTime(long dwSpecialTitleExpireTime) {
        this.dwSpecialTitleExpireTime = dwSpecialTitleExpireTime;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public long getDwShutupTimestap() {
        return dwShutupTimestap;
    }

    public void setDwShutupTimestap(long dwShutupTimestap) {
        this.dwShutupTimestap = dwShutupTimestap;
    }
}
