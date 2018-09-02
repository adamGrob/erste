package com.codecool.erste.ejb;

import javax.persistence.*;

@Embeddable
public class ContactInfo {

    private String type;

    private String contact;

    public ContactInfo() {}

    public ContactInfo(String type, String contact) {

        this.type = type;
        this.contact = contact;
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
