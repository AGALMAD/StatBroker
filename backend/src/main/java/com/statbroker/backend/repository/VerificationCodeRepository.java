package com.statbroker.backend.repository;

import com.statbroker.backend.model.User;
import com.statbroker.backend.model.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, UUID> {

    Optional<VerificationCode> findByUser(User user);

    Optional<VerificationCode> findByUserAndCode(User user, String code);
}
