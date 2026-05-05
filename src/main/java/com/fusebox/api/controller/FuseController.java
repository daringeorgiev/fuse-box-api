package com.fusebox.api.controller;

import com.fusebox.api.dto.request.FuseRequest;
import com.fusebox.api.dto.request.ReorderRequest;
import com.fusebox.api.dto.response.FuseResponse;
import com.fusebox.api.service.FuseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/panels/{panelId}/fuses")
@RequiredArgsConstructor
public class FuseController {

    private final FuseService fuseService;

    @GetMapping
    public List<FuseResponse> getAll(@PathVariable UUID panelId) {
        return fuseService.findByPanel(panelId);
    }

    @GetMapping("/{id}")
    public FuseResponse getById(@PathVariable UUID panelId, @PathVariable UUID id) {
        return fuseService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FuseResponse create(@PathVariable UUID panelId, @Valid @RequestBody FuseRequest request) {
        return fuseService.create(panelId, request);
    }

    @PutMapping("/{id}")
    public FuseResponse update(@PathVariable UUID panelId, @PathVariable UUID id,
                               @Valid @RequestBody FuseRequest request) {
        return fuseService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID panelId, @PathVariable UUID id) {
        fuseService.delete(id);
    }

    @PutMapping("/reorder")
    public List<FuseResponse> reorder(@PathVariable UUID panelId,
                                      @Valid @RequestBody ReorderRequest request) {
        return fuseService.reorder(panelId, request);
    }
}
