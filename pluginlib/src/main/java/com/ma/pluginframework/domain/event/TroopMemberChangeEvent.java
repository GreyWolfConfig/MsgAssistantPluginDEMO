package com.ma.pluginframework.domain.event;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 群成员变动
 */
public class TroopMemberChangeEvent extends PluginEvent implements Parcelable {
    private long guin;
    private long uin;
    /**
     * 0 有人进群
     * 1 主动出群
     * 2 被动出群
     * 3 群转让 from=uin to=operator
     * 4 管理变更 operator 1上 0下管理
     */
    private int type;

    private long operator;

    /**
     * 进群的人马甲
     */
    private String reqNick;

    public TroopMemberChangeEvent(long currentUin, String extra, long guin, long uin, int type, long operator, String reqNick) {
        super(currentUin, extra);
        this.guin = guin;
        this.uin = uin;
        this.type = type;
        this.operator = operator;
        this.reqNick = reqNick;
    }

    protected TroopMemberChangeEvent(Parcel in) {
        super(in);
        guin = in.readLong();
        uin = in.readLong();
        type = in.readInt();
        operator = in.readLong();
        reqNick = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeLong(guin);
        dest.writeLong(uin);
        dest.writeInt(type);
        dest.writeLong(operator);
        dest.writeString(reqNick);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TroopMemberChangeEvent> CREATOR = new Creator<TroopMemberChangeEvent>() {
        @Override
        public TroopMemberChangeEvent createFromParcel(Parcel in) {
            return new TroopMemberChangeEvent(in);
        }

        @Override
        public TroopMemberChangeEvent[] newArray(int size) {
            return new TroopMemberChangeEvent[size];
        }
    };

    public long getGuin() {
        return guin;
    }

    public void setGuin(long guin) {
        this.guin = guin;
    }

    public long getUin() {
        return uin;
    }

    public void setUin(long uin) {
        this.uin = uin;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getOperator() {
        return operator;
    }

    public void setOperator(long operator) {
        this.operator = operator;
    }

    public String getReqNick() {
        return reqNick;
    }

    public void setReqNick(String reqNick) {
        this.reqNick = reqNick;
    }

    @Override
    public String toString() {
//          * 0 有人进群
//                * 1 主动出群
//                * 2 被动出群
//                * 3 群转让 from=uin to=operator
//                * 4 管理变更 operator 1上 0下管理
        StringBuffer sb = new StringBuffer();
        switch (type) {
            case 0:
                sb.append("有人进群:").append(uin).append("进入").append(guin).append(" operator:").append(operator);
                break;
            case 1:
                sb.append("主动出群:").append(uin).append("离开").append(guin).append(" operator:").append(operator);
                break;
            case 2:
                sb.append("被T出群:").append(uin).append("离开").append(guin).append(" operator:").append(operator);
                break;
            case 3:
                sb.append("群转让:").append(uin).append("将").append(guin).append("转给:").append(operator);
                break;
            case 4:
                sb.append("管理变更:").append(guin).append(" QQ").append(uin).append(operator == 1 ? "上管理" : "下管理");
                break;
        }
        return sb.toString() + "TroopMemberChangeEvent{" +
                "guin=" + guin +
                ", uin=" + uin +
                ", type=" + type +
                ", operator=" + operator +
                ", reqNick='" + reqNick + '\'' +
                "} " + super.toString();
    }
}
