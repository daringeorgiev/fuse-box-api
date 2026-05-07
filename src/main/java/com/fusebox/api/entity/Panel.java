package com.fusebox.api.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "panel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Panel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String location;

    private String description;

    @Column(nullable = false)
    private int numRows;

    @Column(nullable = false)
    private int fusesPerRow;

    @Column(nullable = false)
    private int mainAmp;

    @Column(nullable = false)
    private int voltage;

    @Column(nullable = false)
    private int frequency;

    @Column(nullable = false)
    private boolean isDefault;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
