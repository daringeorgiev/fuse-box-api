package com.fusebox.api.repository;

import com.fusebox.api.entity.Panel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PanelRepository extends JpaRepository<Panel, UUID> {
    List<Panel> findByUserId(String userId);
    List<Panel> findByUserIdOrIsDefaultTrue(String userId);
    Optional<Panel> findByIdAndUserId(UUID id, String userId);
    List<Panel> findByIsDefaultTrue();
}
