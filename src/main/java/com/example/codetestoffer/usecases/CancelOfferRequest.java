package com.example.codetestoffer.usecases;

public class CancelOfferRequest {
    private final String id;

    public CancelOfferRequest(String id) {

        this.id = id;
    }

    public String getId() {
        return id;
    }
}
