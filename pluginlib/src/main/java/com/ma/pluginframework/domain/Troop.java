package com.ma.pluginframework.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;
import java.util.List;

/**
 * 群
 */
public class Troop extends HostContact implements Parcelable {
    /**
     * GID
     */
    private long code;

    /**
     * 当前账号的权限
     */
    private TroopPermission permission;
    /**
     * 群主QQ
     */
    private long ownerUin;

    /**
     * 管理员数量 包含群主
     */
    private int managerCount;
    /**
     * 管理员QQ 包含群主
     */
    private long[] managers;

    //新增字段
    private byte flag;
    private long dwGroupInfoSeq;
    private String groupMemo;
    private long dwGroupFlagExt;
    private long dwGroupRankSeq;
    private long dwCertificationType;
    private long dwShutUpTimestamp;
    private long dwMyShutUpTimestamp;
    private long dwCmdUinUinFlag;
    private long dwAdditionalFlag;
    private long dwGroupTypeFlag;
    private long dwGroupSecType;
    private long dwGroupSecTypeInfo;
    private long dwMemberNum;
    private long dwMemberNumSeq;
    private long dwMemberCardSeq;
    private long dwGroupFlagExt3;
    private long dwGroupOwnerUin;

    private long dwCmduinJoinTime;
    private long dwMaxGroupMemberNum;
    private long dwCmdUinGroupMask;

    /**
     * 群成员 包含群主和管理员
     */
    private List<TroopMember> members;




    public Troop(boolean robotOpen, boolean sendOpen, long uin, String name, String mark, String extra, long code, TroopPermission permission, long ownerUin, int managerCount, long[] managers, List<TroopMember> members) {
        super(robotOpen, sendOpen, uin, name, mark, extra);
        this.code = code;
        this.permission = permission;
        this.ownerUin = ownerUin;
        this.managerCount = managerCount;
        this.managers = managers;
        this.members = members;
    }

    protected Troop(Parcel in) {
        super(in);
        code = in.readLong();
        ownerUin = in.readLong();
        managerCount = in.readInt();
        managers = in.createLongArray();
        flag = in.readByte();
        dwGroupInfoSeq = in.readLong();
        groupMemo = in.readString();
        dwGroupFlagExt = in.readLong();
        dwGroupRankSeq = in.readLong();
        dwCertificationType = in.readLong();
        dwShutUpTimestamp = in.readLong();
        dwMyShutUpTimestamp = in.readLong();
        dwCmdUinUinFlag = in.readLong();
        dwAdditionalFlag = in.readLong();
        dwGroupTypeFlag = in.readLong();
        dwGroupSecType = in.readLong();
        dwGroupSecTypeInfo = in.readLong();
        dwMemberNum = in.readLong();
        dwMemberNumSeq = in.readLong();
        dwMemberCardSeq = in.readLong();
        dwGroupFlagExt3 = in.readLong();
        dwGroupOwnerUin = in.readLong();
        dwCmduinJoinTime = in.readLong();
        dwMaxGroupMemberNum = in.readLong();
        dwCmdUinGroupMask = in.readLong();
        members = in.createTypedArrayList(TroopMember.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeLong(code);
        dest.writeLong(ownerUin);
        dest.writeInt(managerCount);
        dest.writeLongArray(managers);
        dest.writeByte(flag);
        dest.writeLong(dwGroupInfoSeq);
        dest.writeString(groupMemo);
        dest.writeLong(dwGroupFlagExt);
        dest.writeLong(dwGroupRankSeq);
        dest.writeLong(dwCertificationType);
        dest.writeLong(dwShutUpTimestamp);
        dest.writeLong(dwMyShutUpTimestamp);
        dest.writeLong(dwCmdUinUinFlag);
        dest.writeLong(dwAdditionalFlag);
        dest.writeLong(dwGroupTypeFlag);
        dest.writeLong(dwGroupSecType);
        dest.writeLong(dwGroupSecTypeInfo);
        dest.writeLong(dwMemberNum);
        dest.writeLong(dwMemberNumSeq);
        dest.writeLong(dwMemberCardSeq);
        dest.writeLong(dwGroupFlagExt3);
        dest.writeLong(dwGroupOwnerUin);
        dest.writeLong(dwCmduinJoinTime);
        dest.writeLong(dwMaxGroupMemberNum);
        dest.writeLong(dwCmdUinGroupMask);
        dest.writeTypedList(members);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Troop> CREATOR = new Creator<Troop>() {
        @Override
        public Troop createFromParcel(Parcel in) {
            return new Troop(in);
        }

        @Override
        public Troop[] newArray(int size) {
            return new Troop[size];
        }
    };

    @Override
    public String toString() {
        return "Troop{" +
                "code=" + code +
                ", permission=" + permission +
                ", ownerUin=" + ownerUin +
                ", managerCount=" + managerCount +
                ", managers=" + Arrays.toString(managers) +
                ", flag=" + flag +
                ", dwGroupInfoSeq=" + dwGroupInfoSeq +
                ", groupMemo='" + groupMemo + '\'' +
                ", dwGroupFlagExt=" + dwGroupFlagExt +
                ", dwGroupRankSeq=" + dwGroupRankSeq +
                ", dwCertificationType=" + dwCertificationType +
                ", dwShutUpTimestamp=" + dwShutUpTimestamp +
                ", dwMyShutUpTimestamp=" + dwMyShutUpTimestamp +
                ", dwCmdUinUinFlag=" + dwCmdUinUinFlag +
                ", dwAdditionalFlag=" + dwAdditionalFlag +
                ", dwGroupTypeFlag=" + dwGroupTypeFlag +
                ", dwGroupSecType=" + dwGroupSecType +
                ", dwGroupSecTypeInfo=" + dwGroupSecTypeInfo +
                ", dwMemberNum=" + dwMemberNum +
                ", dwMemberNumSeq=" + dwMemberNumSeq +
                ", dwMemberCardSeq=" + dwMemberCardSeq +
                ", dwGroupFlagExt3=" + dwGroupFlagExt3 +
                ", dwGroupOwnerUin=" + dwGroupOwnerUin +
                ", dwCmduinJoinTime=" + dwCmduinJoinTime +
                ", dwMaxGroupMemberNum=" + dwMaxGroupMemberNum +
                ", dwCmdUinGroupMask=" + dwCmdUinGroupMask +
                ", members=" + members +
                "} " + super.toString();
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public TroopPermission getPermission() {
        return permission;
    }

    public void setPermission(TroopPermission permission) {
        this.permission = permission;
    }

    public long getOwnerUin() {
        return ownerUin;
    }

    public void setOwnerUin(long ownerUin) {
        this.ownerUin = ownerUin;
    }

    public int getManagerCount() {
        return managerCount;
    }

    public void setManagerCount(int managerCount) {
        this.managerCount = managerCount;
    }

    public long[] getManagers() {
        return managers;
    }

    public void setManagers(long[] managers) {
        this.managers = managers;
    }

    public List<TroopMember> getMembers() {
        return members;
    }

    public void setMembers(List<TroopMember> members) {
        this.members = members;
    }


    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public long getDwGroupInfoSeq() {
        return dwGroupInfoSeq;
    }

    public void setDwGroupInfoSeq(long dwGroupInfoSeq) {
        this.dwGroupInfoSeq = dwGroupInfoSeq;
    }

    public String getGroupMemo() {
        return groupMemo;
    }

    public void setGroupMemo(String groupMemo) {
        this.groupMemo = groupMemo;
    }

    public long getDwGroupFlagExt() {
        return dwGroupFlagExt;
    }

    public void setDwGroupFlagExt(long dwGroupFlagExt) {
        this.dwGroupFlagExt = dwGroupFlagExt;
    }

    public long getDwGroupRankSeq() {
        return dwGroupRankSeq;
    }

    public void setDwGroupRankSeq(long dwGroupRankSeq) {
        this.dwGroupRankSeq = dwGroupRankSeq;
    }

    public long getDwCertificationType() {
        return dwCertificationType;
    }

    public void setDwCertificationType(long dwCertificationType) {
        this.dwCertificationType = dwCertificationType;
    }

    public long getDwShutUpTimestamp() {
        return dwShutUpTimestamp;
    }

    public void setDwShutUpTimestamp(long dwShutUpTimestamp) {
        this.dwShutUpTimestamp = dwShutUpTimestamp;
    }

    public long getDwMyShutUpTimestamp() {
        return dwMyShutUpTimestamp;
    }

    public void setDwMyShutUpTimestamp(long dwMyShutUpTimestamp) {
        this.dwMyShutUpTimestamp = dwMyShutUpTimestamp;
    }

    public long getDwCmdUinUinFlag() {
        return dwCmdUinUinFlag;
    }

    public void setDwCmdUinUinFlag(long dwCmdUinUinFlag) {
        this.dwCmdUinUinFlag = dwCmdUinUinFlag;
    }

    public long getDwAdditionalFlag() {
        return dwAdditionalFlag;
    }

    public void setDwAdditionalFlag(long dwAdditionalFlag) {
        this.dwAdditionalFlag = dwAdditionalFlag;
    }

    public long getDwGroupTypeFlag() {
        return dwGroupTypeFlag;
    }

    public void setDwGroupTypeFlag(long dwGroupTypeFlag) {
        this.dwGroupTypeFlag = dwGroupTypeFlag;
    }

    public long getDwGroupSecType() {
        return dwGroupSecType;
    }

    public void setDwGroupSecType(long dwGroupSecType) {
        this.dwGroupSecType = dwGroupSecType;
    }

    public long getDwGroupSecTypeInfo() {
        return dwGroupSecTypeInfo;
    }

    public void setDwGroupSecTypeInfo(long dwGroupSecTypeInfo) {
        this.dwGroupSecTypeInfo = dwGroupSecTypeInfo;
    }

    public long getDwMemberNum() {
        return dwMemberNum;
    }

    public void setDwMemberNum(long dwMemberNum) {
        this.dwMemberNum = dwMemberNum;
    }

    public long getDwMemberNumSeq() {
        return dwMemberNumSeq;
    }

    public void setDwMemberNumSeq(long dwMemberNumSeq) {
        this.dwMemberNumSeq = dwMemberNumSeq;
    }

    public long getDwMemberCardSeq() {
        return dwMemberCardSeq;
    }

    public void setDwMemberCardSeq(long dwMemberCardSeq) {
        this.dwMemberCardSeq = dwMemberCardSeq;
    }

    public long getDwGroupFlagExt3() {
        return dwGroupFlagExt3;
    }

    public void setDwGroupFlagExt3(long dwGroupFlagExt3) {
        this.dwGroupFlagExt3 = dwGroupFlagExt3;
    }

    public long getDwGroupOwnerUin() {
        return dwGroupOwnerUin;
    }

    public void setDwGroupOwnerUin(long dwGroupOwnerUin) {
        this.dwGroupOwnerUin = dwGroupOwnerUin;
    }

    public long getDwCmduinJoinTime() {
        return dwCmduinJoinTime;
    }

    public void setDwCmduinJoinTime(long dwCmduinJoinTime) {
        this.dwCmduinJoinTime = dwCmduinJoinTime;
    }

    public long getDwMaxGroupMemberNum() {
        return dwMaxGroupMemberNum;
    }

    public void setDwMaxGroupMemberNum(long dwMaxGroupMemberNum) {
        this.dwMaxGroupMemberNum = dwMaxGroupMemberNum;
    }

    public long getDwCmdUinGroupMask() {
        return dwCmdUinGroupMask;
    }

    public void setDwCmdUinGroupMask(long dwCmdUinGroupMask) {
        this.dwCmdUinGroupMask = dwCmdUinGroupMask;
    }
}
