package com.example.codetestoffer;

import com.example.codetestoffer.entities.Offer;
import com.example.codetestoffer.repositories.OfferRepository;
import com.example.codetestoffer.repositories.OfferRepositoryInMemory;
import com.example.codetestoffer.usecases.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
class OfferController {
    private OfferRepository offerRepository = new OfferRepositoryInMemory();

    @GetMapping(value = "/offer/{offerId}")
    public @ResponseBody
    GetOfferResponse getOffer(@PathVariable String offerId) {
        GetOfferRequest getOfferRequest = new GetOfferRequest(offerId);
        GetOfferUseCase getOfferUseCase = new GetOfferUseCase(offerRepository);
        GetOfferResponse getOfferResponse = getOfferUseCase.handle(getOfferRequest);
        return getOfferResponse;
    }

    @PostMapping(path = "/offer", consumes = "application/json", produces = "application/json")
    public @ResponseBody
    CreateOfferResponse postOffer(@RequestBody CreateOfferRequest createOfferRequest) {

//        CreateOfferRequest createOfferRequest = new CreateOfferRequest(randomUUIDString,
//                offer.getDescriptionOffer(),
//                offer.getPrice(),
//                offer.getCreatedAt(),
//                offer.getValidMinutes(),
//                offer.getStatus());

        CreateOfferUseCase createOfferUseCase = new CreateOfferUseCase(offerRepository);
        return createOfferUseCase.handle(createOfferRequest);
    }
}
