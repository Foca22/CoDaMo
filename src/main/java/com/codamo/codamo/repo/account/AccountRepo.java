package com.codamo.codamo.repo.account;

import com.codamo.codamo.model.auth.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, String> {

    Optional<Account> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Account> findByFullNameContainingOrEmailContaining(String firstName, String email);
}
