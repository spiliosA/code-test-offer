package com.example.codetestoffer.entities;

import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;

@Slf4j
public class Offer {
    private final String id;
    private final String descriptionOffer;
    private final int price;
    private final LocalDateTime createdAt;
    private final long validMinutes;
    private final Status status;

    public enum Status {
        ACTIVE,
        CANCELLED,
        EXPIRED
    }

    private Offer(Builder builder) {
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
        private LocalDateTime createdAt;
        private long validMinutes;
        private Status status;

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
        public Builder status(Status val) {
            this.status = val;
            return this;
        }

        public Offer build() {
            return new Offer(this);
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
    public LocalDateTime getCreatedAt() { return createdAt; }
    public long getValidMinutes() {
        return validMinutes;
    }
    public Status getStatus() {
        return status;
    }

    public static boolean offerCanBeAccessed(Offer offer) {
        return (offer != null) && (offer.getStatus() == Status.ACTIVE) && (!offer.getCreatedAt().plusMinutes(offer.getValidMinutes()).isBefore(LocalDateTime.now()));
    }
}
