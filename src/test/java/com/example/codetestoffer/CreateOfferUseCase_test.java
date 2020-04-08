package com.example.codetestoffer;

import com.example.codetestoffer.entities.Offer;
import com.example.codetestoffer.repositories.OfferRepository;
import com.example.codetestoffer.repositories.OfferRepositoryInMemory;
import com.example.codetestoffer.usecases.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CreateOfferUseCase_test {

    private CreateOfferRequest activeOffer = new CreateOfferRequest.Builder()
            .id("1")
            .descriptionOffer("test description offer")
            .price(1500)
            .createdAt(LocalDateTime.now())
            .validMinutes((long) 120)
            .status(Offer.Status.ACTIVE)
            .build();

    private CreateOfferRequest activeOfferDefaultDate = new CreateOfferRequest.Builder()
            .id("1")
            .descriptionOffer("test description offer")
            .price(1500)
            .validMinutes((long) 120)
            .status(Offer.Status.ACTIVE)
            .build();

    private OfferRepository offerRepository = new OfferRepositoryInMemory();

    @Test
    void shouldCreateOffer() {
        CreateOfferResponse createOfferResponse = createOffer(activeOffer);
        assertEquals(activeOffer.getId(), createOfferResponse.getId());
    }

    @Test
    void shouldCreateOfferDefaultDate() {
        createOffer(activeOfferDefaultDate);
        assertEquals(LocalDate.now(), offerRepository.getOffer(activeOfferDefaultDate.getId()).getCreatedAt().toLocalDate());
    }

    private CreateOfferResponse createOffer(CreateOfferRequest createOfferRequest) {

        CreateOfferUseCase createOfferUseCase = new CreateOfferUseCase(offerRepository);
        return createOfferUseCase.handle(createOfferRequest);
    }
}
