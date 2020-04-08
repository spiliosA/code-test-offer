package com.example.codetestoffer.usecases;

public class GetOfferRequest {
    private final String id;

    public GetOfferRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
