package com.codecool.erste.controller;

import com.codecool.erste.ejb.Card;
import com.codecool.erste.model.validationCardModels.Valid;
import com.codecool.erste.model.validationCardModels.ValidCard;
import com.codecool.erste.model.validationCardModels.ValidationResult;

import javax.ejb.Stateless;
import javax.transaction.Transactional;

@Transactional
@Stateless
public class CardValidationController {
    private Boolean isDataMatching(Card card, ValidCard validCard) {

        return validCard.getCardNumber().equals(card.getCardNumber())
                && validCard.getCardType().equals(card.getCardType())
                && validCard.getValidThru().equals(card.getValidThru());
    }

    private Boolean isCardNotDisabled(Card card) {
        return !card.getDisabled();
    }

    private Boolean isHashMatching(Card card, ValidCard validCard) {
        Integer hashToValidate =
                        validCard.getCardNumber().hashCode() +
                        validCard.getCvv().hashCode() +
                        validCard.getValidThru().hashCode();
        Integer persistedHash = card.getHash();
        return hashToValidate.equals(persistedHash);
    }

    public ValidationResult getValidationResult(Card card, ValidCard validCard){
        System.out.println(validCard.getCardNumber());
        if (isDataMatching(card, validCard) && isCardNotDisabled(card) && isHashMatching(card, validCard)) {
            return new ValidationResult(Valid.VALID);
        } else {
            return new ValidationResult(Valid.INVALID);
        }
    }
}
