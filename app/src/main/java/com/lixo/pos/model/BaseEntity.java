package com.lixo.pos.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.Instant;
@Data
@MappedSuperclass
public class BaseEntity {
    private Instant createdDate;

    private String createdUser = "System";

    private Instant updatedDate;

    private String updatedUser = "SYSTEM";
}

