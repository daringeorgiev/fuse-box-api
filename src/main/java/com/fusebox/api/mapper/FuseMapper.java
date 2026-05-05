package com.fusebox.api.mapper;

import com.fusebox.api.dto.request.FuseRequest;
import com.fusebox.api.dto.response.FuseResponse;
import com.fusebox.api.entity.Fuse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FuseMapper {
    Fuse toEntity(FuseRequest request);

    @Mapping(source = "panel.id", target = "panelId")
    FuseResponse toResponse(Fuse fuse);

    void updateEntity(FuseRequest request, @MappingTarget Fuse fuse);
}
