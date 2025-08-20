package org.vlad.music.store.app.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vlad.AlbumDetailsDTO;
import org.vlad.ValidationCheckResponse;
import org.vlad.ValidationClient;
import org.vlad.music.store.app.exceptions.InvalidPriceException;


@Service
@Slf4j
@AllArgsConstructor
public class PriceValidationService {

    private final ValidationClient validationClient;

    public void validatePrice(AlbumDetailsDTO albumDetailsDTO) {
        ValidationCheckResponse validationCheckResponse = validationClient.validatePrice(albumDetailsDTO);
        if (validationCheckResponse != null && !validationCheckResponse.getIsPriceValid()) {
            throw new InvalidPriceException("Invalid price for this album type!");
        }
    }
}
