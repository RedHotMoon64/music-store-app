package org.vlad.music.store.app.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vlad.music.store.app.enums.AlbumType;
import org.vlad.music.store.app.exceptions.InvalidPriceException;

import static org.vlad.music.store.app.enums.AlbumType.LP;

@Service
@Slf4j
public class PriceValidationService {
    public void validatePrice(AlbumType albumType, int price) {
        log.info("Validating price...");
        if (albumType.equals(LP)) {
            if (price < albumType.getPriceRange()) {
                throw new InvalidPriceException("Invalid price for this album type!");
            }
        }
        else {
            if (price > albumType.getPriceRange()) {
                throw new InvalidPriceException("Invalid price for this album type!");
            }
        }
    }
}
