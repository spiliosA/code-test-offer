package com.example.codetestoffer.usecases;

public class CreateOfferResponse {
    private final String id;

    public CreateOfferResponse(String id) {

        this.id = id;
    }

    public String getId() {
        return id;
    }
}
