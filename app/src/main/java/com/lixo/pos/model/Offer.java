package com.lixo.pos.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id")
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "percentage")
    private float percentage;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @Column(name = "from_date")
    private Timestamp fromDate = Timestamp.valueOf(LocalDateTime.now());

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @Column(name = "to_date")
    private Timestamp toDate = Timestamp.valueOf(LocalDateTime.now());
}
