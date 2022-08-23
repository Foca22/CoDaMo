package com.codamo.codamo.controller.admin;

import com.codamo.codamo.dto.admin.account.request.CreateAccountByAdminRequest;
import com.codamo.codamo.dto.admin.account.request.UpdateAccountByAdminRequest;
import com.codamo.codamo.dto.exceptions.security.AccountNotFoundException;
import com.codamo.codamo.service.external.AccountAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/account")
public class AccountAdminController {

    private final AccountAdminService accountAdminService;

    @Autowired
    public AccountAdminController(AccountAdminService accountAdminService){
        this.accountAdminService = accountAdminService;
    }

    @PostMapping()
    ResponseEntity<?> createAccount(@Valid @RequestBody CreateAccountByAdminRequest createAccountByAdminRequest) {
        return ResponseEntity.ok(accountAdminService.createAccount(createAccountByAdminRequest));
    }

    /**
     * Aveam doua posibilitati pentru acest update.
     * In orice caz, avem nevoie de id-ul contului pentru a stii unde sa facem update.
     * Putem sa facem cum e acum, adica sa punem id-ul ca si path variable in url, sau am fi putut sa punem id-ul ca si camp in UpdateAccountByAdminRequest
     * Ambele variante sunt viabile.
     *
     * @param id id-ul luat din path variable a contului pe care vrem sa-l updatam
     * @param updateAccountByAdminRequest Noile date ale contului
     * @return contul updatat
     */
    @PutMapping("/{id}")
    ResponseEntity<?> updateAccount(@PathVariable String id, @Valid @RequestBody UpdateAccountByAdminRequest updateAccountByAdminRequest) {
        try {
            return ResponseEntity.ok(accountAdminService.updateAccount(id, updateAccountByAdminRequest));
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getAccount(@PathVariable String id) {
        try {
            return ResponseEntity.ok(accountAdminService.getAccount(id));
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{search}/{page}")
    ResponseEntity<?> getAccounts(@PathVariable("search") String search, @PathVariable("page") int page) {
        return ResponseEntity.ok(accountAdminService.getAccounts(search, page));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteAccount(String id) {
        try {
            accountAdminService.deleteAccount(id);
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok().body("Account successfully deleted");

    }

    @PostMapping("/ban/{id}")
    ResponseEntity<?> banAccount(String id) {
//        try {
        accountAdminService.banAccount(id);
//        } catch (AccountNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
        return ResponseEntity.ok().body("Account successfully banned");
    }

    @PostMapping("/reset/{id}")
    ResponseEntity<?> resetPassword(String id) {
        try {
            accountAdminService.resetPassword(id);
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok().body("Account password reset successful");
    }
}
