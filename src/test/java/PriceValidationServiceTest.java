import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vlad.music.store.app.enums.AlbumType;
import org.vlad.music.store.app.exceptions.InvalidPriceException;
import org.vlad.music.store.app.services.PriceValidationService;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.vlad.music.store.app.enums.AlbumType.*;

public class PriceValidationServiceTest {
    private PriceValidationService priceValidationService;

    @BeforeEach
    void beforeEach() {
        priceValidationService = new PriceValidationService();
    }

    @Test
    void should_throw_invalid_price_exception_because_wrong_price_for_lp() {
        assertThrows(InvalidPriceException.class, () -> priceValidationService.validatePrice(LP, 100));
    }

    @Test
    void should_throw_invalid_price_exception_because_wrong_price_for_tape() {
        assertThrows(InvalidPriceException.class, () -> priceValidationService.validatePrice(TAPE, 130));

    }
}
