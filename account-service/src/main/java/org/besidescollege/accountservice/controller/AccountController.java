package org.besidescollege.accountservice.controller;

import lombok.RequiredArgsConstructor;
import org.besidescollege.accountservice.repository.AccountRepository;
import org.besidescollege.domain.account.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {

  private final AccountRepository accountRepository;

  @GetMapping("/hello")
  public ResponseEntity<String> hello() {
    return ResponseEntity.ok().body("Hello. Service up and running!!!");
  }

  @GetMapping("/accounts")
  public ResponseEntity<List<Account>> getAllAccounts(@RequestParam(required = false) String name) {
    try {
      List<Account> accounts = new ArrayList<>();

      if (name == null)
        accounts.addAll(accountRepository.findAll());

      if (accounts.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(accounts, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/accounts/{id}")
  public ResponseEntity<Account> getAccountById(@PathVariable("id") String id) {
    Optional<Account> accountData = accountRepository.findById(id);

    return accountData.map(account -> new ResponseEntity<>(account, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/accounts")
  public ResponseEntity<Account> createAccount(@RequestBody Account account) {
    try {
      Account accountSaved = accountRepository.insert(
              Account.builder()
                      .name(account.getName())
                      .address(account.getAddress())
                      .phone(account.getPhone())
                      .phoneAlternate(account.getPhoneAlternate()).build());
      return new ResponseEntity<>(accountSaved, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/accounts/{id}")
  public ResponseEntity<Account> updateAccount(@PathVariable("id") String id, @RequestBody Account account) {
    Optional<Account> accountData = accountRepository.findById(id);

    if (accountData.isPresent()) {
      return new ResponseEntity<>(accountRepository.save(Account.builder()
              .name(account.getName())
              .address(account.getAddress())
              .phone(account.getPhone())
              .phoneAlternate(account.getPhoneAlternate()).build()), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/accounts/{id}")
  public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("id") String id) {
    try {
      accountRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/accounts")
  public ResponseEntity<HttpStatus> deleteAllAccounts() {
    try {
      accountRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
