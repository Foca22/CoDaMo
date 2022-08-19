package com.codamo.codamo.service.external;

import com.codamo.codamo.dto.admin.account.request.CreateAccountByAdminRequest;
import com.codamo.codamo.dto.admin.account.request.UpdateAccountByAdminRequest;
import com.codamo.codamo.dto.auth.response.AccountResponse;
import com.codamo.codamo.dto.exceptions.security.AccountNotFoundException;

import java.util.List;

public interface AccountAdminService {

    /**
     * An admin can create a new account by providing the name and email of a client
     * An email will be sent to the provided email with a default password
     *
     * @param createAccountByAdminRequest account to be created
     * @return an account
     */
    AccountResponse createAccount(CreateAccountByAdminRequest createAccountByAdminRequest);

    AccountResponse updateAccount(String id, UpdateAccountByAdminRequest updateAccountByAdminRequest) throws AccountNotFoundException;

    AccountResponse getAccount(String id) throws AccountNotFoundException;

    List<AccountResponse> getAccounts(String search, int page);

    void deleteAccount(String id) throws AccountNotFoundException;

    void banAccount(String id);

    void resetPassword(String id) throws AccountNotFoundException;
}
