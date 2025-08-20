package org.vlad;

import lombok.Builder;

@Builder
public record AlbumDetailsDTO(
        int id,
        String albumName,
        String albumDetails,
        AlbumType albumType,
        int price,
        int stock) {
}
