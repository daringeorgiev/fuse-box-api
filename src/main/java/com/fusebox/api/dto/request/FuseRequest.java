package com.fusebox.api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FuseRequest {

    @NotNull
    @Min(0)
    private Integer position;

    @NotBlank
    private String label;

    @NotNull
    @Min(1)
    private Integer amperage;

    private String description;
}
