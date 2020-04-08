package com.example.codetestoffer.usecases;

import com.example.codetestoffer.entities.Offer;
import com.example.codetestoffer.repositories.OfferRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CancelOfferUseCase {
    private final OfferRepository offerRepository;

    public CancelOfferUseCase(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public void handle(CancelOfferRequest cancelOfferRequest) throws IllegalArgumentException {
        Offer offer = offerRepository.getOffer(cancelOfferRequest.getId());

        if (Offer.offerCanBeAccessed(offer)) {
            Offer cancelledOffer = new Offer.Builder()
                    .id(offer.getId())
                    .descriptionOffer(offer.getDescriptionOffer())
                    .price(offer.getPrice())
                    .createdAt(offer.getCreatedAt())
                    .validMinutes(offer.getValidMinutes())
                    .status(Offer.Status.CANCELLED)
                    .build();
            offerRepository.putOffer(cancelledOffer);
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
            throw new IllegalArgumentException("Offer cannot be cancelled");
        }
    }


}
