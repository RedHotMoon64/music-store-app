package org.vlad;

import lombok.Data;

import java.io.Serializable;

@Data
public class ValidationCheckResponse implements Serializable {
    private Boolean isPriceValid;
}
