package com.codamo.codamo.repo.account;

import com.codamo.codamo.model.auth.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationRepo extends JpaRepository<EmailVerification, String> {
    Optional<EmailVerification> findByToken(String token);
}
