package com.statbroker.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "verification_codes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "code"}))
public class VerificationCode {

    @Id
    @GeneratedValue
    UUID id;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
    @Column(nullable = false)
    String code;
    @Column(nullable = false)
    LocalDateTime  createdAt ;
    @Column(nullable = false)
    LocalDateTime expiresAt;

}
