package org.vlad.music.store.app.enums;

import lombok.Getter;

@Getter
public enum AlbumType {
    CD( 100),
    TAPE( 120),
    LP( 150);

    private final int priceRange;

    AlbumType(int priceRange) {
        this.priceRange = priceRange;
    }
}
