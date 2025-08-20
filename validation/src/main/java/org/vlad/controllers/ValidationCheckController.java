package org.vlad.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vlad.dtos.AlbumDetailsDTO;
import org.vlad.responses.ValidationCheckResponse;
import org.vlad.services.ValidationCheckService;

@RestController
@RequestMapping("validation-service")
@AllArgsConstructor
public class ValidationCheckController {

    private ValidationCheckService validationCheckService;

    @PostMapping("/validate")
    public ValidationCheckResponse validatePrice(@RequestBody AlbumDetailsDTO albumDetailsDTO) {
        ValidationCheckResponse response = new ValidationCheckResponse();
        response.setIsPriceValid(validationCheckService.validatePrice(albumDetailsDTO));
        return response;
    }
}
