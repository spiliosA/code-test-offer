package com.example.codetestoffer;

import com.example.codetestoffer.entities.Offer;
import com.example.codetestoffer.repositories.OfferRepository;
import com.example.codetestoffer.repositories.OfferRepositoryInMemory;
import com.example.codetestoffer.usecases.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CancelOfferUseCase_test {

    private CreateOfferRequest activeOffer = new CreateOfferRequest.Builder()
            .id("1")
            .descriptionOffer("test description offer")
            .price(1500)
            .createdAt(LocalDateTime.now())
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
    void shouldCancelActiveOffer() {
        createOffer(activeOffer);

        CancelOfferRequest cancelOfferRequest = new CancelOfferRequest(expiredOfferStatusNotUpdated.getId());
        CancelOfferUseCase cancelOfferUseCase = new CancelOfferUseCase(offerRepository);
        cancelOfferUseCase.handle(cancelOfferRequest);

        assertEquals(Offer.Status.CANCELLED, offerRepository.getOffer(activeOffer.getId()).getStatus());
    }

    @Test
    void cancelOfferThatDoesNotExist() {
        CancelOfferRequest cancelOfferRequest = new CancelOfferRequest(activeOffer.getId());
        CancelOfferUseCase cancelOfferUseCase = new CancelOfferUseCase(offerRepository);

        assertThrows(IllegalArgumentException.class, () -> cancelOfferUseCase.handle(cancelOfferRequest));
    }

    @Test
    void cancelExpiredOfferStatusExpired() {
        createOffer(expiredOffer);

        CancelOfferRequest cancelOfferRequest = new CancelOfferRequest(expiredOfferStatusNotUpdated.getId());
        CancelOfferUseCase cancelOfferUseCase = new CancelOfferUseCase(offerRepository);

        assertThrows(IllegalArgumentException.class, () -> cancelOfferUseCase.handle(cancelOfferRequest));
    }

    @Test
    void cancelExpiredOfferStatusHasNotExpired() {
        createOffer(expiredOfferStatusNotUpdated);

        CancelOfferRequest cancelOfferRequest = new CancelOfferRequest(expiredOfferStatusNotUpdated.getId());
        CancelOfferUseCase cancelOfferUseCase = new CancelOfferUseCase(offerRepository);

        assertThrows(IllegalArgumentException.class, () -> cancelOfferUseCase.handle(cancelOfferRequest));
        assertEquals(Offer.Status.EXPIRED, offerRepository.getOffer(expiredOfferStatusNotUpdated.getId()).getStatus());
    }


    private CreateOfferResponse createOffer(CreateOfferRequest createOfferRequest) {

        CreateOfferUseCase createOfferUseCase = new CreateOfferUseCase(offerRepository);
        return createOfferUseCase.handle(createOfferRequest);
    }
}
