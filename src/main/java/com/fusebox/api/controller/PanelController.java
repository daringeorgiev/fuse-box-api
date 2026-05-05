package com.fusebox.api.controller;

import com.fusebox.api.dto.request.PanelRequest;
import com.fusebox.api.dto.response.PanelResponse;
import com.fusebox.api.service.PanelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/panels")
@RequiredArgsConstructor
public class PanelController {

    private final PanelService panelService;

    @GetMapping
    public List<PanelResponse> getAll() {
        return panelService.findAll();
    }

    @GetMapping("/{id}")
    public PanelResponse getById(@PathVariable UUID id) {
        return panelService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PanelResponse create(@Valid @RequestBody PanelRequest request) {
        return panelService.create(request);
    }

    @PutMapping("/{id}")
    public PanelResponse update(@PathVariable UUID id, @Valid @RequestBody PanelRequest request) {
        return panelService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        panelService.delete(id);
    }
}
