package com.alpaca.AiPica.auth.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Entity(name="t_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max=45)
    @Column(unique = false)
    private String userName;

    @Email (message="이메일을 형식에 맞게 작성해 주세요.")
    @Column(unique = true)
    private String email;

    private String password;
}
