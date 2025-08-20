package org.vlad;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("validation")
public interface ValidationClient {

    @PostMapping(path = "validation-service/validate")
    ValidationCheckResponse validatePrice(@RequestBody AlbumDetailsDTO albumDetailsDTO);
}
