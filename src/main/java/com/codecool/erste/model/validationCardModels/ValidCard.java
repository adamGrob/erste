package com.codecool.erste.model.validationCardModels;

public class ValidCard {

    private String cardType;

    private String cardNumber;

    private String validThru;

    private String cvv;

    public ValidCard(){}

    public ValidCard(String cardType, String cardNumber, String validThru, String cvv) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.validThru = validThru;
        this.cvv = cvv;
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
        return validThru;
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
}
