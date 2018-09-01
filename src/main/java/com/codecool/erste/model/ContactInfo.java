package com.codecool.erste.model;

import javax.persistence.*;

@Entity
public class ContactInfo {

    @Id
    @ManyToOne
    private Card card;

    private String type;

    private String contact;

    public ContactInfo() {}

    public ContactInfo(Card card, String type, String contact) {
        this.card = card;
        this.type = type;
        this.contact = contact;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getType() {
        return type;
    }

    public void setType(String contactType) {
        this.type = contactType;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
