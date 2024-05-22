package com.Alpaca.AiPica.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "wordbooks")
public class Wordbook {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @Column(name = "user_id", length = 45)
    private String userId;

    @Size(max = 45)
    @Column(name = "title", length = 45)
    private String title;

    @Size(max = 45)
    @Column(name = "description", length = 45)
    private String description;

}