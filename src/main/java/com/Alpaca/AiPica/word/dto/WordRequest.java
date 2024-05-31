package com.alpaca.AiPica.word.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class WordRequest {

    @NotBlank
    private String userName;

    @NotBlank
    private String word;

    @NotBlank
    private String meaning;

    private String memo;

    private String imageUrl;

    private String example;

    private String groupTag;

    private Boolean bookmark;
}
