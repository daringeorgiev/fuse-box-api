package com.fusebox.api.mapper;

import com.fusebox.api.dto.request.FuseRequest;
import com.fusebox.api.dto.response.FuseResponse;
import com.fusebox.api.entity.Fuse;
import org.springframework.stereotype.Component;

@Component
public class FuseMapper {

    public Fuse toEntity(FuseRequest request) {
        Fuse fuse = new Fuse();
        fuse.setPosition(request.getPosition());
        fuse.setLabel(request.getLabel());
        fuse.setAmperage(request.getAmperage());
        fuse.setDescription(request.getDescription());
        return fuse;
    }

    public FuseResponse toResponse(Fuse fuse) {
        FuseResponse response = new FuseResponse();
        response.setId(fuse.getId());
        response.setPanelId(fuse.getPanel().getId());
        response.setPosition(fuse.getPosition());
        response.setLabel(fuse.getLabel());
        response.setAmperage(fuse.getAmperage());
        response.setDescription(fuse.getDescription());
        response.setCreatedAt(fuse.getCreatedAt());
        response.setUpdatedAt(fuse.getUpdatedAt());
        return response;
    }

    public void updateEntity(FuseRequest request, Fuse fuse) {
        fuse.setPosition(request.getPosition());
        fuse.setLabel(request.getLabel());
        fuse.setAmperage(request.getAmperage());
        fuse.setDescription(request.getDescription());
    }
}
