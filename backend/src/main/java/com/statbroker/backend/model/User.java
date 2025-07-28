package com.statbroker.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {
    @Id
    @GeneratedValue
    UUID id;
    @NotBlank(message = "Name cannot be blank")
    @Column(nullable = false)
    String name;
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    @Column(nullable = false, unique = true)
    String email;
    @NotBlank(message = "Password cannot be blank")
    @Column(nullable = false)
    String password;
    @NotNull(message = "The confirmed state cannot be null")
    @Column(nullable = false)
    Boolean confirmed = false;
}
