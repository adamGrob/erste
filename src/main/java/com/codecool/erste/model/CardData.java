package com.codecool.erste.model;


import javax.persistence.OneToMany;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CardData {


    private String cardType;

    private String cardNumber;

    private String validThru;

    private String cvv;

    private String owner;

    private List<ContactInfo> contactInfos;


    public CardData(){}

    public CardData(String cardType, String cardNumber, String validThru, String cvv, String owner, List<ContactInfo> contactInfos) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.validThru = validThru;
        this.cvv = cvv;
        this.owner = owner;
        this.contactInfos = contactInfos;
    }

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
        SimpleDateFormat format = new SimpleDateFormat("MM-yy"); // your format
        return format.parse(validThru);
    }

    public void setValidThru(String validThru) {
        this.validThru = validThru;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
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

    public Integer createHash() {
        return (cardNumber.hashCode() + cvv.hashCode() + validThru.hashCode());
    }
}