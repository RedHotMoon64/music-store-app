package org.vlad.music.store.app.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vlad.music.store.app.dtos.AlbumDetailsDTO;
import org.vlad.music.store.app.dtos.PriceDTO;
import org.vlad.music.store.app.entities.Album;
import org.vlad.music.store.app.exceptions.AlbumNotFoundException;
import org.vlad.music.store.app.repositories.AlbumRepository;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class AlbumProcessingService {
    private final AlbumRepository albumRepository;
    private final PriceValidationService priceValidationService;

    public AlbumDetailsDTO getAlbum(int id) {
        return mapToDTO(albumRepository.findById(id).orElseThrow(() -> new AlbumNotFoundException("There is no album with id: " + id)));
    }

    public List<AlbumDetailsDTO> getAllAlbums() {
        return albumRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void addNewAlbum (AlbumDetailsDTO albumDetailsDTO) {
        priceValidationService.validatePrice(albumDetailsDTO.albumType(), albumDetailsDTO.price());
        albumRepository.saveAndFlush(createAlbumEntity(albumDetailsDTO));
        log.info("Added new album: {}", albumDetailsDTO.albumName());
    }

    public void updateAlbumPrice (int id, PriceDTO priceDTO) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new AlbumNotFoundException("There is no album with id: " + id));
        priceValidationService.validatePrice(album.getType(), priceDTO.price());
        album.setPrice(priceDTO.price());
        log.info("Updating album {}'s price...", album.getName());
        this.albumRepository.saveAndFlush(album);
        log.info("Album {} 's price updated to {}", album.getName(), priceDTO.price());
    }

    private AlbumDetailsDTO mapToDTO(Album album) {
        return AlbumDetailsDTO.builder()
                .id(album.getId())
                .albumName(album.getName())
                .albumDetails(album.getDetails())
                .albumType(album.getType())
                .price(album.getPrice())
                .stock(album.getStock())
                .build();
    }

    private Album createAlbumEntity(AlbumDetailsDTO albumDetailsDTO) {
        Album album = new Album();
        album.setName(albumDetailsDTO.albumName());
        album.setDetails(albumDetailsDTO.albumDetails());
        album.setType(albumDetailsDTO.albumType());
        album.setPrice(albumDetailsDTO.price());
        album.setStock(albumDetailsDTO.stock());
        return album;
    }

    public void deleteAlbum(int id) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new AlbumNotFoundException("There is no album with id: " + id));
        log.info("Deleting album {}...", album.getName());
        this.albumRepository.delete(album);
        log.info("Album {} has been deleted", album.getName());
    }
}
