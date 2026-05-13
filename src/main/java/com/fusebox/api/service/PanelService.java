package com.fusebox.api.service;

import com.fusebox.api.dto.request.PanelRequest;
import com.fusebox.api.dto.response.PanelResponse;
import com.fusebox.api.entity.Panel;
import com.fusebox.api.exception.ResourceNotFoundException;
import com.fusebox.api.mapper.PanelMapper;
import com.fusebox.api.repository.PanelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PanelService {

    private final PanelRepository panelRepository;
    private final PanelMapper panelMapper;

    public List<PanelResponse> findAll(String userId) {
        List<Panel> panels = userId != null
                ? panelRepository.findByUserIdOrIsDefaultTrue(userId)
                : panelRepository.findByIsDefaultTrue();
        return panels.stream()
                .sorted(Comparator.comparing(Panel::isDefault).reversed())
                .map(panelMapper::toResponse)
                .toList();
    }

    public PanelResponse findById(UUID id, String userId) {
        return panelMapper.toResponse(getReadablePanel(id, userId));
    }

    @Transactional
    public PanelResponse create(PanelRequest request, String userId) {
        Panel panel = panelMapper.toEntity(request);
        panel.setUserId(userId);
        return panelMapper.toResponse(panelRepository.save(panel));
    }

    @Transactional
    public PanelResponse update(UUID id, PanelRequest request, String userId) {
        Panel panel = getOwnedPanel(id, userId);
        panelMapper.updateEntity(request, panel);
        return panelMapper.toResponse(panelRepository.save(panel));
    }

    @Transactional
    public void delete(UUID id, String userId) {
        panelRepository.delete(getOwnedPanel(id, userId));
    }

    Panel getReadablePanel(UUID id, String userId) {
        if (userId != null) {
            return panelRepository.findByIdAndUserId(id, userId)
                    .or(() -> panelRepository.findById(id).filter(Panel::isDefault))
                    .orElseThrow(() -> new ResourceNotFoundException("Panel not found: " + id));
        }
        Panel panel = panelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Panel not found: " + id));
        if (!panel.isDefault()) {
            throw new ResourceNotFoundException("Panel not found: " + id);
        }
        return panel;
    }

    Panel getOwnedPanel(UUID id, String userId) {
        Panel panel = panelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Panel not found: " + id));
        if (isAdmin()) {
            return panel;
        }
        if (panel.isDefault()) {
            throw new AccessDeniedException("Only admins can modify the default panel");
        }
        if (userId != null && userId.equals(panel.getUserId())) {
            return panel;
        }
        throw new AccessDeniedException("Access denied to panel: " + id);
    }

    private boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }
}
