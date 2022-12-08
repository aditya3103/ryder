package com.example.ryderr.ui.main.chat;

public class ChatMessage {
    private String msgText;
    private String msgTime;
    private String grpID;
    private String senderID;
    public final static String MSG_RECEIVED = "MSG_RECEIVED";
    public final static String MSG_SENT = "MSG_SENT";
    private String msgType;

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getSenderID() {
        return senderID;
    }

    public String getGrpID() {
        return grpID;
    }

    public ChatMessage(String msgText, String msgTime, String msgType, String sID, String grpID){
        this.msgText = msgText;
        this.msgTime = msgTime;
        this.msgType = msgType;
        this.grpID = grpID;
        senderID = sID;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }
}
