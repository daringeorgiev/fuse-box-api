package com.fusebox.api.dto.response;

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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
