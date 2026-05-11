package com.fusebox.api.service;

import com.fusebox.api.dto.request.FuseRequest;
import com.fusebox.api.dto.request.ReorderRequest;
import com.fusebox.api.dto.response.FuseResponse;
import com.fusebox.api.entity.Fuse;
import com.fusebox.api.exception.ResourceNotFoundException;
import com.fusebox.api.mapper.FuseMapper;
import com.fusebox.api.repository.FuseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FuseService {

    private final FuseRepository fuseRepository;
    private final FuseMapper fuseMapper;
    private final PanelService panelService;

    public List<FuseResponse> findByPanel(UUID panelId, String userId) {
        panelService.getPanel(panelId, userId);
        return fuseRepository.findByPanelIdOrderByPosition(panelId).stream()
                .map(fuseMapper::toResponse)
                .toList();
    }

    public FuseResponse findById(UUID panelId, UUID id, String userId) {
        panelService.getPanel(panelId, userId);
        return fuseMapper.toResponse(getFuse(id));
    }

    @Transactional
    public FuseResponse create(UUID panelId, FuseRequest request, String userId) {
        Fuse fuse = fuseMapper.toEntity(request);
        fuse.setPanel(panelService.getPanel(panelId, userId));
        return fuseMapper.toResponse(fuseRepository.save(fuse));
    }

    @Transactional
    public FuseResponse update(UUID panelId, UUID id, FuseRequest request, String userId) {
        panelService.getPanel(panelId, userId);
        Fuse fuse = getFuse(id);
        fuseMapper.updateEntity(request, fuse);
        return fuseMapper.toResponse(fuseRepository.save(fuse));
    }

    @Transactional
    public void delete(UUID panelId, UUID id, String userId) {
        panelService.getPanel(panelId, userId);
        fuseRepository.delete(getFuse(id));
    }

    @Transactional
    public List<FuseResponse> reorder(UUID panelId, ReorderRequest request, String userId) {
        panelService.getPanel(panelId, userId);
        List<UUID> orderedIds = request.getOrderedIds();
        for (int i = 0; i < orderedIds.size(); i++) {
            Fuse fuse = getFuse(orderedIds.get(i));
            fuse.setPosition(i);
            fuseRepository.save(fuse);
        }
        return findByPanel(panelId, userId);
    }

    private Fuse getFuse(UUID id) {
        return fuseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fuse not found: " + id));
    }
}
