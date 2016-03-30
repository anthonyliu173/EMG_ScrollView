package com.anthony.emq_scrollview.Objects;

/**
 * Created by anthonyliu on 2016/3/30.
 */
public class Sender {

    private String senderName;
    private String note;

    public Sender(String senderName, String note) {
        this.senderName = senderName;
        this.note = note;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
