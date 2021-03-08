package com.ma.pluginframework.domain;

public enum TroopPermission {
    Normal(0, "成员"),
    Manager(1, "管理"),
    Owner(2, "群主");

    private int value;
    private String text;

    TroopPermission(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public static TroopPermission get(int value) {
        for (TroopPermission troopPermission : TroopPermission.values()) {
            if (troopPermission.getValue() == value) {
                return troopPermission;
            }
        }
        return null;
    }
}
