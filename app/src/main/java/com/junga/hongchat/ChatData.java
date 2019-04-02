package com.junga.hongchat;

import java.io.Serializable;

public class ChatData implements Serializable {

    private String msg;
    private String nickname;


    public String getMeg() {
        return msg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setMeg(String meg) {
        this.msg = meg;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
