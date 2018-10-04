package com.skysoft.vaultlogic.web.maya.clients;

public enum Light {

    WHITE("white"), IR("ir"), UV("uv");

    private String light;

    Light(String light) {
        this.light = light;
    }

    public String getLightAsString(){
        return this.light;
    }

}