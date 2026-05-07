package com.fusebox.api.service;

import com.fusebox.api.dto.request.PanelRequest;
import com.fusebox.api.dto.response.PanelResponse;
import com.fusebox.api.entity.Panel;
import com.fusebox.api.exception.ResourceNotFoundException;
import com.fusebox.api.mapper.PanelMapper;
import com.fusebox.api.repository.PanelRepository;
import lombok.RequiredArgsConstructor;
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

    public List<PanelResponse> findAll() {
        return panelRepository.findAll().stream()
                .sorted(Comparator.comparing(Panel::isDefault).reversed())
                .map(panelMapper::toResponse)
                .toList();
    }

    public PanelResponse findById(UUID id) {
        return panelMapper.toResponse(getPanel(id));
    }

    @Transactional
    public PanelResponse create(PanelRequest request) {
        Panel panel = panelMapper.toEntity(request);
        return panelMapper.toResponse(panelRepository.save(panel));
    }

    @Transactional
    public PanelResponse update(UUID id, PanelRequest request) {
        Panel panel = getPanel(id);
        panelMapper.updateEntity(request, panel);
        return panelMapper.toResponse(panelRepository.save(panel));
    }

    @Transactional
    public void delete(UUID id) {
        panelRepository.delete(getPanel(id));
    }

    Panel getPanel(UUID id) {
        return panelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Panel not found: " + id));
    }
}
