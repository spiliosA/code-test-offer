package com.example.codetestoffer.repositories;

import com.example.codetestoffer.entities.Offer;

public interface OfferRepository {
    String putOffer(Offer offer);

    Offer getOffer(String id);
}
