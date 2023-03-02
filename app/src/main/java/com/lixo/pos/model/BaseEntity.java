package com.lixo.pos.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
@Data
@MappedSuperclass
public class BaseEntity {
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private Instant createdDate;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String createdUser = "System";

    @JsonInclude(JsonInclude.Include.ALWAYS)
    private Instant updatedDate;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String updatedUser = "SYSTEM";
}

