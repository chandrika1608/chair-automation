package com.lixo.pos.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    private Instant createdDate;

    private String createdUser = "System";

    private Instant updatedDate;

    private String updatedUser = "System";
}

