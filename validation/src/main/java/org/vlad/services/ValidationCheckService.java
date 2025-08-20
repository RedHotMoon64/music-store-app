package org.vlad.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vlad.dtos.AlbumDetailsDTO;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.vlad.enums.AlbumType.LP;

@Service
@Slf4j
public class ValidationCheckService {
    public Boolean validatePrice(AlbumDetailsDTO albumDetailsDTO) {
        log.info("Validating price...");
        if (albumDetailsDTO.albumType().equals(LP)) {
            if (albumDetailsDTO.price() < albumDetailsDTO.albumType().getPriceRange()) {
                return FALSE;
            }
        }
        else {
            if (albumDetailsDTO.price() > albumDetailsDTO.albumType().getPriceRange()) {
                return FALSE;
            }
        }
        return TRUE;
        //TODO save in a db
    }
}
