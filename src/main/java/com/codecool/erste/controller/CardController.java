package com.codecool.erste.controller;
import com.codecool.erste.ejb.Card;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Stateless
public class CardController {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Card card) {
        entityManager.persist(card);
    }

    public Card getCard(String cardNumber) {
        return entityManager.find(Card.class, cardNumber);
    }
}

