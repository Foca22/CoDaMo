package com.codamo.codamo.service.external.impl;

import com.codamo.codamo.dto.admin.account.request.CreateAccountByAdminRequest;
import com.codamo.codamo.dto.admin.account.request.UpdateAccountByAdminRequest;
import com.codamo.codamo.dto.auth.response.AccountResponse;
import com.codamo.codamo.dto.exceptions.security.AccountNotFoundException;
import com.codamo.codamo.model.auth.Account;
import com.codamo.codamo.repo.account.AccountRepo;
import com.codamo.codamo.security.AuthoritiesConstants;
import com.codamo.codamo.service.external.AccountAdminService;
import com.codamo.codamo.service.internal.AccountInternalService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountAdminServiceImpl implements AccountAdminService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private AccountInternalService accountInternalService;

    @Override
    public AccountResponse createAccount(CreateAccountByAdminRequest createAccountByAdminRequest) {

        Account account = new Account();
        account.setAuthorities(createAccountByAdminRequest.isAdmin() ?
                List.of(AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER) :
                List.of(AuthoritiesConstants.USER)
        );
        account.setEmail(createAccountByAdminRequest.getEmail().toLowerCase().trim());
        account.setFirstName(createAccountByAdminRequest.getFirstName());
        account.setLastName(createAccountByAdminRequest.getLastName());
        account.setFullName(account.getFirstName() + " " + account.getLastName());

        String generatedPassword = generatePassword();
        account.setPassword(accountInternalService.getPasswordEncoder().encode(generatedPassword));

        account.setVerified(true);

        account = accountRepo.save(account);

        // TODO Send email with generatedPassword to the provided email
        return accountInternalService.toAccountResponse(account);
    }

    @Override
    public AccountResponse updateAccount(String id, UpdateAccountByAdminRequest updateAccountByAdminRequest) throws AccountNotFoundException {

        /*
         accountRepo.findById(id) intoarce un Optional<Account>
         Poti sa faci map direct pe optional. Daca exista Account, atunci va intra in map si face ce e in map.
         Daca nu exista va intra la final in acel orElseThrow. Daca nu exista un account va arunca o exceptie.
         Observa cum putem sa avem mai multe map-uri unul dupa altul. Astfel se poate structura codul intr-un mod mai lizibil.
         Primul map este folosit pentru a create contul in baza de date, al doilea map este folosit pentru a crea raspunsul pe baza contului nou creat.
         In al doilea map, folosesc method reference. Este acelasi lucru cum as zice
         .map(createdAccount -> accountInternalService.toAccountResponse(createdAccount))
         */
        return accountRepo.findById(id)
                .map(account -> {
                    account.setFirstName(updateAccountByAdminRequest.getFirstName());
                    account.setLastName(updateAccountByAdminRequest.getLastName());
                    account.setEmail(updateAccountByAdminRequest.getEmail().toLowerCase().trim());

                    return accountRepo.save(account);
                })
                .map(accountInternalService::toAccountResponse)
                .orElseThrow(() -> new AccountNotFoundException("Account with id " + id + " not found!"));
    }

    @Override
    public AccountResponse getAccount(String id) throws AccountNotFoundException {
        return accountRepo.findById(id)
                .map(accountInternalService::toAccountResponse)
                .orElseThrow(() -> new AccountNotFoundException("Account with id " + id + " not found!"));
    }

    @Override
    public List<AccountResponse> getAccounts(String search, int page) {
        // accountRepo.findByFullNameContainingOrEmailContaining intoarce o lista.
        // pentru a putea aplica functii pe o colectie, trebuie sa folosim stream.
        // la final, pentru a avea o lista, va trebui sa colectam stream-ul intr-una
        return accountRepo.findByFullNameContainingOrEmailContaining(search, search)
                .stream()
                .map(accountInternalService::toAccountResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(String id) throws AccountNotFoundException {
        if (accountRepo.existsById(id)) {
            accountRepo.deleteById(id);
        } else {
            throw new AccountNotFoundException("Account with id " + id + " not found!");
        }
    }

    @Override
    public void banAccount(String id) {

    }

    @Override
    public void resetPassword(String id) throws AccountNotFoundException {
        Account account = accountRepo.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account with id " + id + " not found!"));

        String generatedPassword = generatePassword();
        account.setPassword(accountInternalService.getPasswordEncoder().encode(generatedPassword));

        accountRepo.save(account);
        // TODO Send email with generatedPassword
    }

    /**
     * Generates a random String that is used as a default password when a new account is created by an admin
     *
     * @return the generated password
     */
    private String generatePassword() {
        return RandomStringUtils.random(10, true, true);
    }
}
