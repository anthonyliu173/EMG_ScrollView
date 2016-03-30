package com.anthony.emq_scrollview.Objects;

import java.util.Date;

/**
 * Created by anthonyliu on 2016/3/30.
 */
public class Transaction {

    public static final String ID = "id";
    public static final String SOURCE = "source";
    public static final String DESTINATION = "destination";
    public static final String CREATEDAT = "created";
    public static final String SENDER = "sender";
    public static final String NOTE = "note";
    public static final String RECIPIENT = "recipient";
    public static final String CURRENCY = "currency";
    public static final String AMOUNT = "amount";

    private int id;
    private Date createdAt;

    private Sender sender;
    private Destination destination;

    public Transaction(int id, Date createdAt, String senderName, String senderNote, String recipient, int amount, String currency){
        
        this.id = id;
        this.createdAt = createdAt;
        this.sender = new Sender(senderName, senderNote);
        this.destination = new Destination(recipient, currency, amount);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

}
