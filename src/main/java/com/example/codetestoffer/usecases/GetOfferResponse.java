package com.example.codetestoffer.usecases;

import com.example.codetestoffer.entities.Offer;

import java.time.LocalDateTime;

public class GetOfferResponse {

    private final String id;
    private final String descriptionOffer;
    private final int price;
    private final LocalDateTime createdAt;
    private final long validMinutes;
    private final Offer.Status status;

    public String getId() {
        return id;
    }

    public String getDescriptionOffer() {
        return descriptionOffer;
    }

    public int getPrice() {
        return price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public long getValidMinutes() {
        return validMinutes;
    }

    public Offer.Status getStatus() {
        return status;
    }

    public GetOfferResponse(String id, String descriptionOffer, int price, LocalDateTime createdAt, long validMinutes, Offer.Status status) {

        this.id = id;
        this.descriptionOffer = descriptionOffer;
        this.price = price;
        this.createdAt = createdAt;
        this.validMinutes = validMinutes;
        this.status = status;
    }

}
