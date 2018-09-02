package com.codecool.erste.ejb;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@XmlRootElement
public class Card implements Serializable {

    private String cardType;

    @Id
    private String cardNumber;

    @Temporal(value = TemporalType.DATE)
    private Date validThru;

    private Integer hash;

    private String owner;

    private boolean disabled;

    @ElementCollection
    private List<ContactInfo> contactInfos;

    public Card(){}

    public Card(String cardType, String cardNumber, Date validThru, Integer hash, String owner, List<ContactInfo> contactInfos) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.validThru = validThru;
        this.hash = hash;
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

    public String getValidThru() {
        DateFormat dateFormat = new SimpleDateFormat("MM/YY");
        return dateFormat.format(validThru);
    }

    public void setValidThru(Date validThru) {
        this.validThru = validThru;
    }

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
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

    public boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
