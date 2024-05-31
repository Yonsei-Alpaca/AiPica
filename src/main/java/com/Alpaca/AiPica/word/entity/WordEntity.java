package com.alpaca.AiPica.word.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Setter
@Getter
@Entity(name="t_word")
public class WordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(max=45)
    private String userName;
    @Size(max=45)
    private String word;
    @Size(max=45)
    private String meaning;
    private String memo;
    @Size(max=2048)
    private String imageUrl;
    private String example;
    private String groupTag;
    private boolean bookmark;

}
