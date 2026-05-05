package com.fusebox.api.repository;

import com.fusebox.api.entity.Fuse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FuseRepository extends JpaRepository<Fuse, UUID> {
    List<Fuse> findByPanelIdOrderByPosition(UUID panelId);
}
