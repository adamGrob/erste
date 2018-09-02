package com.codecool.erste.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class TransCard {

    String cardType;

    String cardNumber;

    String validThru;

    String owner;

    List<ContactInfo> contactInfos;


    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getValidThru() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("MM/yy"); // your format
        return format.parse(validThru);
    }

    public void setValidThru(String validThru) {
        this.validThru = validThru;
    }


    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<ContactInfo> getContactInfos() {
        return contactInfos;
    }

    public void setContactInfos(List<ContactInfo> contactInfos) {
        this.contactInfos = contactInfos;
    }

}
