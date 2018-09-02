package com.codecool.erste.controller;
import com.codecool.erste.ErsteApplication;
import com.codecool.erste.ejb.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Stateless
public class CardController {

    private final Logger log = LoggerFactory.getLogger(ErsteApplication.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Card card) {

        entityManager.persist(card);
        log.info("Card object has been persisted.");
    }

    public Card getCard(String cardNumber) {
        log.info("Retrieving Card from database.");
        return entityManager.find(Card.class, cardNumber);
    }

    public void update(Card card) {
        log.info("Updating Card in database.");
        entityManager.merge(card);
    }
}

