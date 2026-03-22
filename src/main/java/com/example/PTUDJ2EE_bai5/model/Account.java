package com.example.PTUDJ2EE_bai5.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Tên đăng nhập là bắt buộc")
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @NotBlank(message = "Mật khẩu là bắt buộc")
    @Column(nullable = false, length = 255)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public Account(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
