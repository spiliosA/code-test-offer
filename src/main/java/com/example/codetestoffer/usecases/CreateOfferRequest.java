package com.example.codetestoffer.usecases;

import com.example.codetestoffer.entities.Offer;

import java.time.LocalDateTime;

public class CreateOfferRequest {

    private final String id;
    private final String descriptionOffer;
    private final int price;
    private final LocalDateTime createdAt;
    private final long validMinutes;
    private final Offer.Status status;

    private CreateOfferRequest(Builder builder) {
        this.id = builder.id;
        this.descriptionOffer = builder.descriptionOffer;
        this.price = builder.price;
        this.createdAt = builder.createdAt;
        this.validMinutes = builder.validMinutes;
        this.status = builder.status;
    }

    public static class Builder {
        private String id;
        private String descriptionOffer;
        private int price;
        private LocalDateTime createdAt = LocalDateTime.now();
        private long validMinutes;
        private Offer.Status status;

        public Builder(){}

        public Builder id(String val) {
            this.id = val;
            return this;
        }
        public Builder descriptionOffer(String val) {
            this.descriptionOffer = val;
            return this;
        }

        public Builder price(int val) {
            this.price = val;
            return this;
        }
        public Builder createdAt(LocalDateTime val) {
            this.createdAt = val;
            return this;
        }

        public Builder validMinutes(Long val) {
            this.validMinutes = val;
            return this;
        }
        public Builder status(Offer.Status val) {
            this.status = val;
            return this;
        }

        public CreateOfferRequest build() {
            return new CreateOfferRequest(this);
        }
    }


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
}
