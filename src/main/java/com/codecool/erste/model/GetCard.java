package com.codecool.erste.model;

import com.codecool.erste.ejb.ContactInfo;

import java.util.List;

public class GetCard extends TransCard {

    private boolean disabled;

    public GetCard(){}

    public GetCard(String cardType, String cardNumber, String validThru, boolean disabled, String owner, List<ContactInfo> contactInfos) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.validThru = validThru;
        this.disabled = disabled;
        this.owner = owner;
        this.contactInfos = contactInfos;
    }
}
