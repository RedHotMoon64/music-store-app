
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vlad.music.store.app.dtos.AlbumDetailsDTO;
import org.vlad.music.store.app.entities.Album;
import org.vlad.music.store.app.exceptions.AlbumNotFoundException;
import org.vlad.music.store.app.repositories.AlbumRepository;
import org.vlad.music.store.app.services.AlbumProcessingService;
import org.vlad.music.store.app.services.PriceValidationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.vlad.music.store.app.enums.AlbumType.*;

public class AlbumProcessingServiceTest {

    private AlbumRepository albumRepository;
    private AlbumProcessingService albumProcessingService;
    private PriceValidationService priceValidationService;
    List<Album> albumList = new ArrayList<>();
    List<AlbumDetailsDTO> result = new ArrayList<>();
    Album album1 = new Album(1, "charli xcx - brat", "deluxe edition", LP, 200, 10);
    Album album2 = new Album(2, "joy division - unknwown pleasures", "digipack", CD, 100, 20);
    AlbumDetailsDTO albumDetailsDTO = new AlbumDetailsDTO(1, "charli xcx - brat", "deluxe edition", LP, 200, 10);


    @BeforeEach
    void beforeEach() {
        albumRepository = mock(AlbumRepository.class);
        priceValidationService = mock(PriceValidationService.class);
        albumProcessingService = new AlbumProcessingService(albumRepository, priceValidationService);
        albumList.add(album1);
        albumList.add(album2);
    }

    @Test
    void should_return_correct_album() {
        when(albumRepository.findById(1)).thenReturn(Optional.ofNullable(this.album1));
        AlbumDetailsDTO result = albumProcessingService.getAlbum(1);
        assertEquals(result.id(), album1.getId());
    }

    @Test
    void should_return_all_albums() {
        when(albumRepository.findAll()).thenReturn(albumList);
        result = albumProcessingService.getAllAlbums();
        assertEquals(result.size(), albumList.size());
    }

    @Test
    void should_have_the_same_values() {
        when(albumRepository.findAll()).thenReturn(albumList);
        result = albumProcessingService.getAllAlbums();
        assertEquals(result.get(0).id(), albumList.get(0).getId());
        assertEquals(result.get(1).id(), albumList.get(1).getId());
    }

    @Test
    void should_throw_album_not_found_exception_when_album_not_found() {
        assertThrows(AlbumNotFoundException.class, () -> albumProcessingService.getAlbum(1));
    }

    @Test
    void should_call_validate_price_when_add_new_album() {
        albumProcessingService.addNewAlbum(albumDetailsDTO);
        verify(priceValidationService, times(1)).validatePrice(albumDetailsDTO.albumType(), albumDetailsDTO.price());
    }
}
