package com.codecool.erste.model;
import java.util.List;

public class PostCard extends TransCard {

    private String cvv;

    public PostCard(){}

    public PostCard(String cardType, String cardNumber, String validThru, String cvv, String owner, List<ContactInfo> contactInfos) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.validThru = validThru;
        this.cvv = cvv;
        this.owner = owner;
        this.contactInfos = contactInfos;
    }

    public Integer createHash() {
        return (cardNumber.hashCode() + cvv.hashCode() + validThru.hashCode());
    }
}