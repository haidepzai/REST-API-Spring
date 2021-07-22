package de.oette.lecture.A04.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class SubValueDto {

    @Min(value = 1)
    @Max(value = 100)
    private Long value;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
