import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.vlad.ValidationClient;
import org.vlad.AlbumDetailsDTO;

import org.vlad.music.store.app.exceptions.InvalidPriceException;
import org.vlad.music.store.app.services.PriceValidationService;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.vlad.AlbumType.*;

@Disabled
public class PriceValidationServiceTest {
    private PriceValidationService priceValidationService;
    AlbumDetailsDTO albumDetailsDTO = new AlbumDetailsDTO(1, "charli xcx - brat", "deluxe edition", LP, 100, 10);
    AlbumDetailsDTO albumDetailsDTO1 = new AlbumDetailsDTO(1, "charli xcx - brat", "deluxe edition", TAPE, 200, 10);
    ValidationClient validationClient;


    @BeforeEach
    void beforeEach() {
        validationClient = mock(ValidationClient.class);
        priceValidationService = new PriceValidationService(validationClient);
    }

    @Test
    void should_throw_invalid_price_exception_because_wrong_price_for_lp() {
        assertThrows(InvalidPriceException.class, () -> priceValidationService.validatePrice(albumDetailsDTO));
    }

    @Test
    void should_throw_invalid_price_exception_because_wrong_price_for_tape() {
        assertThrows(InvalidPriceException.class, () -> priceValidationService.validatePrice(albumDetailsDTO1));

    }
}
