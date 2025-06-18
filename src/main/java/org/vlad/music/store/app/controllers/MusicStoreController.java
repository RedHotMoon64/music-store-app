package org.vlad.music.store.app.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.vlad.music.store.app.dtos.AlbumDetailsDTO;
import org.vlad.music.store.app.dtos.PriceDTO;
import org.vlad.music.store.app.services.AlbumProcessingService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("music-store")
public class MusicStoreController {
    private final AlbumProcessingService albumProcessingService;

    @GetMapping("/get-all-albums")
    public List<AlbumDetailsDTO> getAllAlbums() {
        return albumProcessingService.getAllAlbums();
    }

    @GetMapping("/get-album/{id}")
    public AlbumDetailsDTO getAlbum(@PathVariable("id") int id) {
       return albumProcessingService.getAlbum(id);
    }

    @PostMapping("/add")
    public void addNewAlbum(@RequestBody AlbumDetailsDTO albumDetailsDTO) {
        albumProcessingService.addNewAlbum(albumDetailsDTO);
    }

    @PutMapping("/update-price/{id}")
    public void updateAlbumPrice(@RequestBody PriceDTO priceDTO, @PathVariable("id") int id) {
        albumProcessingService.updateAlbumPrice(id, priceDTO);
    }
}
