package com.statbroker.backend.service;

import com.statbroker.backend.repository.VerificationCodeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class VerificationCodeCleanupService {

    private final VerificationCodeRepository verificationCodeRepository;

    @Transactional
    @Scheduled(fixedRate = 60 * 1000) // 1 minute
    public void deleteExpiredCodes() {
        int deleted = verificationCodeRepository.deleteByExpiresAtBefore(LocalDateTime.now());
        if (deleted > 0) {
            log.info("Deleted {} expired verification codes", deleted);
        }
    }
}