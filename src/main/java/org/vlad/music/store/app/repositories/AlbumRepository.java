package org.vlad.music.store.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vlad.music.store.app.entities.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
