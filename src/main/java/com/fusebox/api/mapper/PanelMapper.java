package com.fusebox.api.mapper;

import com.fusebox.api.dto.request.PanelRequest;
import com.fusebox.api.dto.response.PanelResponse;
import com.fusebox.api.entity.Panel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PanelMapper {
    Panel toEntity(PanelRequest request);
    PanelResponse toResponse(Panel panel);
    void updateEntity(PanelRequest request, @MappingTarget Panel panel);
}
