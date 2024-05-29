package com.alpaca.AiPica.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Content<T> {

    private T body;
}
