package com.fusebox.api.repository;

import com.fusebox.api.entity.Panel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PanelRepository extends JpaRepository<Panel, UUID> {
}
