package com.fusebox.api.controller;

import com.fusebox.api.dto.request.PanelRequest;
import com.fusebox.api.dto.response.PanelResponse;
import com.fusebox.api.service.PanelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/panels")
@RequiredArgsConstructor
public class PanelController {

    private final PanelService panelService;

    @GetMapping
    public List<PanelResponse> getAll(@AuthenticationPrincipal String uid) {
        return panelService.findAll(uid);
    }

    @GetMapping("/{id}")
    public PanelResponse getById(@PathVariable UUID id, @AuthenticationPrincipal String uid) {
        return panelService.findById(id, uid);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated()")
    public PanelResponse create(@Valid @RequestBody PanelRequest request,
                                @AuthenticationPrincipal String uid) {
        return panelService.create(request, uid);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public PanelResponse update(@PathVariable UUID id,
                                @Valid @RequestBody PanelRequest request,
                                @AuthenticationPrincipal String uid) {
        return panelService.update(id, request, uid);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("isAuthenticated()")
    public void delete(@PathVariable UUID id, @AuthenticationPrincipal String uid) {
        panelService.delete(id, uid);
    }
}
