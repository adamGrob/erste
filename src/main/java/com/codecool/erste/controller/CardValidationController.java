package com.codecool.erste.controller;

import com.codecool.erste.ErsteApplication;
import com.codecool.erste.ejb.Card;
import com.codecool.erste.model.validationCardModels.Valid;
import com.codecool.erste.model.validationCardModels.ValidCard;
import com.codecool.erste.model.validationCardModels.ValidationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.transaction.Transactional;

@Transactional
@Stateless
public class CardValidationController {

    private final Logger log = LoggerFactory.getLogger(ErsteApplication.class);

    private Boolean isDataMatching(Card card, ValidCard validCard) {

        log.info("Checking if cardNumber, cardType and expirationDate(validThru) are matching");
        Boolean result = validCard.getCardNumber().equals(card.getCardNumber())
                && validCard.getCardType().equals(card.getCardType())
                && validCard.getValidThru().equals(card.getValidThru());
        if (!result) {
            log.warn("cardNumber, cardType and expirationDate(validThru) are not matching.");
        } else {
            log.info("cardNumber, cardType and expirationDate(validThru) are correctly matching.");
        }
        return result;
    }

    private Boolean isCardNotDisabled(Card card) {
        log.info("Checking if card is disabled.");
        return !card.getDisabled();
    }

    private Boolean isHashMatching(Card card, ValidCard validCard) {
        log.info("Checking hash is matching.");
        Integer hashToValidate =
                        validCard.getCardNumber().hashCode() +
                        validCard.getCvv().hashCode() +
                        validCard.getValidThru().hashCode();
        Integer persistedHash = card.getHash();
        if(!hashToValidate.equals(persistedHash)){
            log.warn("The two hashes are not matching");
        } else {
            log.info("The two hashes are matching");
        }
        return hashToValidate.equals(persistedHash);
    }

    public ValidationResult getValidationResult(Card card, ValidCard validCard){
        System.out.println(validCard.getCardNumber());
        if (isDataMatching(card, validCard) && isCardNotDisabled(card) && isHashMatching(card, validCard)) {
            log.info("Card is not valid");
            return new ValidationResult(Valid.VALID);
        } else {
            log.info("Card is valid.");
            return new ValidationResult(Valid.INVALID);
        }
    }
}
