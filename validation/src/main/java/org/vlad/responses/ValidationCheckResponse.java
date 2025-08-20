package org.vlad.responses;

import lombok.Data;

import java.io.Serializable;

@Data
public class ValidationCheckResponse implements Serializable {
    private Boolean isPriceValid;
}
