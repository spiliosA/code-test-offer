package com.example.codetestoffer.usecases;

import com.example.codetestoffer.entities.Offer;
import com.example.codetestoffer.repositories.OfferRepository;

import java.time.LocalDateTime;

public class CreateOfferUseCase {
    private final OfferRepository offerRepository;

    public CreateOfferUseCase(OfferRepository offerRepository) {

        this.offerRepository = offerRepository;
    }

    public CreateOfferResponse handle(CreateOfferRequest createOfferRequest) {
        Offer offer = new Offer.Builder()
                .id(createOfferRequest.getId())
                .descriptionOffer(createOfferRequest.getDescriptionOffer())
                .price(createOfferRequest.getPrice())
                .createdAt(createOfferRequest.getCreatedAt())
                .validMinutes(createOfferRequest.getValidMinutes())
                .status(createOfferRequest.getStatus())
                .build();

        String offerId = offerRepository.putOffer(offer);
        return new CreateOfferResponse(offerId);
    }
}
