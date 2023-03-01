package com.lixo.pos.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private String id;
    @Column(name = "name")
    private String name;

    @Column(name = "percentage")
    private float percentage;

    @Column(name = "restaurant_id")
    private Long restaurantId;
    @JsonInclude(JsonInclude.Include.ALWAYS)
    @Column(name = "from_date")
    private Instant fromDate;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @Column(name = "to_date")
    private Instant toDate;

    @OneToMany(mappedBy = "offer")
    private Set <MenuItem> menuItem;


}
