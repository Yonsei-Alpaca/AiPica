package com.Alpaca.AiPica.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "words")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "word", nullable = false)
    private String word;

    @Size(max = 255)
    @NotNull
    @Column(name = "meaning", nullable = false)
    private String meaning;

    @Size(max = 255)
    @Column(name = "memo")
    private String memo;

    @Size(max = 255)
    @Column(name = "pronunciation")
    private String pronunciation;

    @Size(max = 255)
    @ColumnDefault("'그룹 미지정'")
    @Column(name = "group_name")
    private String groupName;

    @Lob
    @Column(name = "image", columnDefinition="LONGBLOB")
    private byte[] image;

    @Size(max = 45)
    @Column(name = "email", nullable = false)
    private String email;
}
