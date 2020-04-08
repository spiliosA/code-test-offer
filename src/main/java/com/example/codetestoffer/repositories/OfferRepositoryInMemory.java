package com.example.codetestoffer.repositories;

import com.example.codetestoffer.entities.Offer;

import java.util.HashMap;
import java.util.Map;

public class OfferRepositoryInMemory implements OfferRepository {
    private Map<String, Offer> offerHashMap = new HashMap<>();

    @Override
    public String putOffer(Offer offer) {
        offerHashMap.put(offer.getId(), offer);
        return offer.getId();
    }

    @Override
    public Offer getOffer(String id) {
        return offerHashMap.get(id);
    }

}
