package org.vlad.music.store.app.dtos;

import lombok.Builder;
import org.vlad.music.store.app.enums.AlbumType;

@Builder
public record AlbumDetailsDTO(
        int id,
        String albumName,
        String albumDetails,
        AlbumType albumType,
        int price,
        int stock) {

}
