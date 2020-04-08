package com.example.codetestoffer;

import com.example.codetestoffer.entities.Offer;
import com.example.codetestoffer.repositories.OfferRepository;
import com.example.codetestoffer.repositories.OfferRepositoryInMemory;
import com.example.codetestoffer.usecases.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class GetOfferUseCase_test {

    private CreateOfferRequest activeOffer = new CreateOfferRequest.Builder()
            .id("1")
            .descriptionOffer("test description offer")
            .createdAt(LocalDateTime.now())
            .price(1500)
            .validMinutes((long) 120)
            .status(Offer.Status.ACTIVE).build();

    private CreateOfferRequest expiredOffer = new CreateOfferRequest.Builder()
            .id("1")
            .descriptionOffer("test description offer")
            .price(1500)
            .createdAt(LocalDateTime.of(2018, 1, 1, 0, 0))
            .validMinutes((long) 120)
            .status(Offer.Status.EXPIRED)
            .build();

    private CreateOfferRequest expiredOfferStatusNotUpdated = new CreateOfferRequest.Builder()
            .id("1")
            .descriptionOffer("test description offer")
            .price(1500)
            .createdAt(LocalDateTime.of(2018, 1, 1, 0, 0))
            .validMinutes((long) 120)
            .status(Offer.Status.ACTIVE)
            .build();

    private OfferRepository offerRepository = new OfferRepositoryInMemory();

    @Test
    void getActiveOffer() {
        createOffer(activeOffer);

        GetOfferRequest getOfferRequest = new GetOfferRequest(activeOffer.getId());
        GetOfferUseCase getOfferUseCase = new GetOfferUseCase(offerRepository);
        GetOfferResponse getOfferResponse = getOfferUseCase.handle(getOfferRequest);

        assertEquals(activeOffer.getId(), getOfferResponse.getId());
    }

    @Test
    void getOfferThatDoesNotExist() {
        GetOfferRequest getOfferRequest = new GetOfferRequest(activeOffer.getId());
        GetOfferUseCase getOfferUseCase = new GetOfferUseCase(offerRepository);

        assertThrows(IllegalArgumentException.class, () -> getOfferUseCase.handle(getOfferRequest));
    }

    @Test
    void getExpiredOfferStatusExpired() {
        createOffer(expiredOffer);

        GetOfferRequest getOfferRequest = new GetOfferRequest(activeOffer.getId());
        GetOfferUseCase getOfferUseCase = new GetOfferUseCase(offerRepository);

        assertThrows(IllegalArgumentException.class, () -> getOfferUseCase.handle(getOfferRequest));
    }

    @Test
    void getExpiredOfferStatusNotExpired() {
        createOffer(expiredOfferStatusNotUpdated);

        GetOfferRequest getOfferRequest = new GetOfferRequest(expiredOfferStatusNotUpdated.getId());
        GetOfferUseCase getOfferUseCase = new GetOfferUseCase(offerRepository);

        assertThrows(IllegalArgumentException.class, () -> getOfferUseCase.handle(getOfferRequest));
        assertEquals(Offer.Status.EXPIRED, offerRepository.getOffer(expiredOfferStatusNotUpdated.getId()).getStatus());
    }

    private CreateOfferResponse createOffer(CreateOfferRequest createOfferRequest) {

        CreateOfferUseCase createOfferUseCase = new CreateOfferUseCase(offerRepository);
        return createOfferUseCase.handle(createOfferRequest);
    }
}
