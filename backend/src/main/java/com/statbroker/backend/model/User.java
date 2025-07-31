package com.statbroker.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User implements UserDetails {
    @Id
    @GeneratedValue
    UUID id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false, unique = true)
    String email;
    @Column(nullable = true)
    String password;
    @Builder.Default
    @Column(nullable = false)
    Boolean confirmed = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
