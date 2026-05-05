package com.fusebox.api.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class FuseResponse {
    private UUID id;
    private UUID panelId;
    private int position;
    private String label;
    private int amperage;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
