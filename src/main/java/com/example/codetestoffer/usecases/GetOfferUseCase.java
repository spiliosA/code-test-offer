package com.example.codetestoffer.usecases;

import com.example.codetestoffer.entities.Offer;
import com.example.codetestoffer.repositories.OfferRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetOfferUseCase {
    private final OfferRepository offerRepository;

    public GetOfferUseCase(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public GetOfferResponse handle(GetOfferRequest getOfferRequest) throws IllegalArgumentException {

        Offer offer = offerRepository.getOffer(getOfferRequest.getId());

        if (Offer.offerCanBeAccessed(offer)) {
            return new GetOfferResponse(offer.getId(),
                    offer.getDescriptionOffer(),
                    offer.getPrice(),
                    offer.getCreatedAt(),
                    offer.getValidMinutes(),
                    offer.getStatus());
        } else {
            if (offer != null) {
                if (offer.getStatus() == Offer.Status.ACTIVE) {
                    offerRepository.putOffer(new Offer.Builder()
                            .id(offer.getId())
                            .descriptionOffer(offer.getDescriptionOffer())
                            .price(offer.getPrice())
                            .createdAt(offer.getCreatedAt())
                            .validMinutes(offer.getValidMinutes())
                            .status(Offer.Status.EXPIRED)
                            .build());
                }
            }
            throw new IllegalArgumentException("Offer cannot be accessed");

        }
    }
}
