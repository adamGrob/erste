package com.codecool.erste.api;
import com.codecool.erste.ErsteApplication;
import com.codecool.erste.controller.CardController;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.codecool.erste.controller.CardValidationController;
import com.codecool.erste.ejb.Card;
import com.codecool.erste.model.*;
import com.codecool.erste.model.validationCardModels.ValidCard;
import com.codecool.erste.model.validationCardModels.ValidationResult;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;

@Path("/ecards")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class CardServiceRest extends Application {

    private final Logger log = LoggerFactory.getLogger(ErsteApplication.class);

    @Inject
    CardController cardController;

    @Inject
    CardValidationController cardValidationController;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewCard(String jsonString) throws ParseException {
        PostCard postCard = new Gson().fromJson(jsonString, PostCard.class);
        if(postCard == null) {
            log.error("Postcard object is null.");
        }
        Card card = new Card(
                postCard.getCardType(),
                postCard.getCardNumber(),
                postCard.getValidThru(),
                postCard.createHash(),
                postCard.getOwner(),
                postCard.getContactInfos()
        );
        if(card.getContactInfos() == null) {
            log.warn("getContactInfos returned null.");
        }
        cardController.create(card);
        return Response.status(200).build();
    }

    @GET
    @Path("{cardNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCard(@PathParam("cardNumber") String cardNumber) {
        log.info("The cardNumber is:", cardNumber);
        Card card = cardController.getCard(cardNumber);
        if (card == null) {
            log.warn("Couldn't find the matching cardNumber in the database.");
            return Response.status(404).build();
        }
        TransCard responseCard = new GetCard(
                card.getCardType(),
                card.getCardNumber(),
                card.getValidThru(),
                card.getDisabled(),
                card.getOwner(),
                card.getContactInfos());
        String jsonResponse = new Gson().toJson(responseCard, GetCard.class);
        log.info("Jsonresponse object created.");
        return Response.ok(jsonResponse).build();
    }

    @POST
    @Path("/validate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateCard(String jsonString) {
        ValidCard validCard = new Gson().fromJson(jsonString, ValidCard.class);
        Card card = cardController.getCard(validCard.getCardNumber());
        if (card == null) {
            log.warn("Couldn't find the matching cardNumber in the database.");
            return Response.status(404).build();
        }
        ValidationResult validationResult = cardValidationController.getValidationResult(card, validCard);
        String jsonResponse = new Gson().toJson(validationResult, ValidationResult.class);
        log.info("Jsonresponse object created.");
        return Response.ok(jsonResponse).build();
    }

    @PUT
    @Path("{cardNumber}")
    public Response disableCard(@PathParam("cardNumber") String cardNumber) {
        Card card = cardController.getCard(cardNumber);
        if (card == null) {
            log.warn("Couldn't find the matching cardNumber in the database.");
            return Response.status(404).build();
        }
        card.setDisabled(true);
        cardController.update(card);
        if(cardController.getCard(card.getCardNumber()).getDisabled()) {
            log.info("Card is successfully disabled.");
            return Response.ok().build();
        }
        return Response.serverError().build();
    }

}
