package com.anthony.emq_scrollview.Objects;

/**
 * Created by anthonyliu on 2016/3/30.
 */
public class Destination {

    private String recipient;
    private String currency;
    private int amount;

    public Destination(String recipient, String currency, int amount) {
        this.recipient = recipient;
        this.currency = currency;
        this.amount = amount;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
