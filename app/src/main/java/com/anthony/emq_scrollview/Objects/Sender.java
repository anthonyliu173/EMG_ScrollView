package com.anthony.emq_scrollview.Objects;

/**
 * Created by anthonyliu on 2016/3/30.
 */
public class Sender {

    private String sender;
    private String note;

    public Sender(String sender, String note) {
        this.sender = sender;
        this.note = note;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
