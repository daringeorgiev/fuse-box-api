package com.fusebox.api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PanelRequest {

    @NotBlank
    private String name;

    private String location;

    private String description;

    @NotNull
    @Min(1)
    private Integer numRows;

    @NotNull
    @Min(1)
    private Integer fusesPerRow;
}
