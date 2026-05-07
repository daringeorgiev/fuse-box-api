package com.fusebox.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PanelResponse {
    private UUID id;
    private String name;
    private String location;
    private String description;
    private int numRows;
    private int fusesPerRow;
    private int mainAmp;
    private int voltage;
    private int frequency;
    @JsonProperty("isDefault")
    private boolean isDefault;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
