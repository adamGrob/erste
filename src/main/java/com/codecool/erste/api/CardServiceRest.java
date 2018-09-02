package com.codecool.erste.api;

import com.codecool.erste.controller.CardController;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codecool.erste.model.Card;
import com.codecool.erste.model.GetCard;
import com.codecool.erste.model.PostCard;
import com.codecool.erste.model.TransCard;
import com.google.gson.Gson;

import java.text.ParseException;

@Path("/ecards")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class CardServiceRest extends Application {

    @Inject
    CardController cardController;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewCard(String jsonString) throws ParseException {
        PostCard postCard = new Gson().fromJson(jsonString, PostCard.class);

        Card card = new Card(
                postCard.getCardType(),
                postCard.getCardNumber(),
                postCard.getValidThru(),
                postCard.createHash(),
                postCard.getOwner(),
                postCard.getContactInfos()
        );


        if(card.getContactInfos() == null) {
            System.out.println("halleluja");
        }
        if(cardController == null) {
            System.out.println("Fuck this shit");
        } else {
            System.out.println(postCard.getCardNumber());
            System.out.println(card.getCardNumber());
            System.out.println(postCard.getContactInfos().get(0).getType());
            System.out.println(card.getContactInfos().get(0).getType());
            cardController.create(card);
            return Response.status(200).build();
        }
        return Response.status(500).build();
    }

    @GET
    @Path("{cardNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCard(@PathParam("cardNumber") String cardNumber) {
        System.out.println(cardNumber);
        Card card = cardController.getCard(cardNumber);
        if (card == null) {
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
        return Response.ok(jsonResponse).build();
    }
}
