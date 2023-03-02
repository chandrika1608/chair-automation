package com.lixo.pos.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Setter
@Getter
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private float percentage;

    private Long restaurantId;
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private Instant fromDate;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    private Instant toDate;


}
