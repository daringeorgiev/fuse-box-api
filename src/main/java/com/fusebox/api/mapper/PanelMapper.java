package com.fusebox.api.mapper;

import com.fusebox.api.dto.request.PanelRequest;
import com.fusebox.api.dto.response.PanelResponse;
import com.fusebox.api.entity.Panel;
import org.springframework.stereotype.Component;

@Component
public class PanelMapper {

    public Panel toEntity(PanelRequest request) {
        Panel panel = new Panel();
        panel.setName(request.getName());
        panel.setLocation(request.getLocation());
        panel.setDescription(request.getDescription());
        panel.setNumRows(request.getNumRows());
        panel.setFusesPerRow(request.getFusesPerRow());
        panel.setMainAmp(request.getMainAmp());
        panel.setVoltage(request.getVoltage());
        panel.setFrequency(request.getFrequency());
        return panel;
    }

    public PanelResponse toResponse(Panel panel) {
        PanelResponse response = new PanelResponse();
        response.setId(panel.getId());
        response.setName(panel.getName());
        response.setLocation(panel.getLocation());
        response.setDescription(panel.getDescription());
        response.setNumRows(panel.getNumRows());
        response.setFusesPerRow(panel.getFusesPerRow());
        response.setMainAmp(panel.getMainAmp());
        response.setVoltage(panel.getVoltage());
        response.setFrequency(panel.getFrequency());
        response.setCreatedAt(panel.getCreatedAt());
        response.setUpdatedAt(panel.getUpdatedAt());
        return response;
    }

    public void updateEntity(PanelRequest request, Panel panel) {
        panel.setName(request.getName());
        panel.setLocation(request.getLocation());
        panel.setDescription(request.getDescription());
        panel.setNumRows(request.getNumRows());
        panel.setFusesPerRow(request.getFusesPerRow());
        panel.setMainAmp(request.getMainAmp());
        panel.setVoltage(request.getVoltage());
        panel.setFrequency(request.getFrequency());
    }
}
