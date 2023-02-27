package com.lixo.pos.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Data
@MappedSuperclass
public class BaseEntity {
    @JsonInclude(JsonInclude.Include.ALWAYS)
    @Column(name = "created_date")
    private Timestamp createdDate = Timestamp.valueOf(LocalDateTime.now());

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @Column(name = "created_user")
    private String createdUser = "System";

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @Column(name = "updated_date")
    private Timestamp updatedDate = Timestamp.valueOf(LocalDateTime.now());

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @Column(name = "updated_user")
    private String updatedUser = "SYSTEM";
}

