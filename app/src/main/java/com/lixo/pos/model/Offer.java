package com.lixo.pos.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

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
