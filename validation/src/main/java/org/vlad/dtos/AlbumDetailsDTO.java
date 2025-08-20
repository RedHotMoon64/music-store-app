package org.vlad.dtos;

import lombok.Builder;
import org.vlad.enums.AlbumType;


@Builder
public record AlbumDetailsDTO(
        int id,
        String albumName,
        String albumDetails,
        AlbumType albumType,
        int price,
        int stock) {
}
