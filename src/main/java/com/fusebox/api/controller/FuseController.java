package com.fusebox.api.controller;

import com.fusebox.api.dto.request.FuseRequest;
import com.fusebox.api.dto.request.ReorderRequest;
import com.fusebox.api.dto.response.FuseResponse;
import com.fusebox.api.service.FuseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/panels/{panelId}/fuses")
@RequiredArgsConstructor
public class FuseController {

    private final FuseService fuseService;

    @GetMapping
    public List<FuseResponse> getAll(@PathVariable UUID panelId,
                                     @AuthenticationPrincipal String uid) {
        return fuseService.findByPanel(panelId, uid);
    }

    @GetMapping("/{id}")
    public FuseResponse getById(@PathVariable UUID panelId,
                                @PathVariable UUID id,
                                @AuthenticationPrincipal String uid) {
        return fuseService.findById(panelId, id, uid);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FuseResponse create(@PathVariable UUID panelId,
                               @Valid @RequestBody FuseRequest request,
                               @AuthenticationPrincipal String uid) {
        return fuseService.create(panelId, request, uid);
    }

    @PutMapping("/{id}")
    public FuseResponse update(@PathVariable UUID panelId,
                               @PathVariable UUID id,
                               @Valid @RequestBody FuseRequest request,
                               @AuthenticationPrincipal String uid) {
        return fuseService.update(panelId, id, request, uid);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID panelId,
                       @PathVariable UUID id,
                       @AuthenticationPrincipal String uid) {
        fuseService.delete(panelId, id, uid);
    }

    @PutMapping("/reorder")
    public List<FuseResponse> reorder(@PathVariable UUID panelId,
                                      @Valid @RequestBody ReorderRequest request,
                                      @AuthenticationPrincipal String uid) {
        return fuseService.reorder(panelId, request, uid);
    }
}
