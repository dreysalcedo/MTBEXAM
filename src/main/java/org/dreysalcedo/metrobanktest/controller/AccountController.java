package org.dreysalcedo.metrobanktest.controller;

import org.dreysalcedo.metrobanktest.model.Account;
import org.dreysalcedo.metrobanktest.repo.AccountRepository;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    //401 status code error is related in security/authorization
    @GetMapping("/getAccountsById/{customerNumber}")
    public ResponseEntity<Map<String, Object>> getAccountById(@PathVariable Long customerNumber) {
        Optional<Account> account = accountRepository.findById(customerNumber);
        Map<String, Object> response = new HashMap<>();
        try {
            if (account.isPresent()) {

                response.put("data", account);
                response.put("transactionStatusCode", HttpStatus.OK.value());
                response.put("transactionStatusDescription", "Customer Account found.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("data", null);
                response.put("transactionStatusCode", HttpStatus.NOT_FOUND.value());
                response.put("transactionStatusDescription", "Customer Account Not Found.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("data", null);
            response.put("transactionStatusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("transactionStatusDescription", "Internal Server Error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    @PostMapping("/account")
    public ResponseEntity<Map<String, Object>>  addAccount(@Valid @RequestBody Account account, BindingResult bindingResult){
        Map<String, Object> response = new HashMap<>();
        try{
            Account savedAccount = accountRepository.save(account); //save acc
            response.put("data", account);
            response.put("transactionStatusCode", HttpStatus.CREATED.value());
            response.put("transactionStatusDescription", "Customer Account created.");
            return new ResponseEntity<>(response,  HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            List<Map<String, String>> errors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                Map<String, String> fieldError = new HashMap<>();
                fieldError.put("transactionStatusCode", String.valueOf(HttpStatus.BAD_REQUEST.value()));
                fieldError.put("transactionStatusDescription", error.getDefaultMessage());
                errors.add(fieldError);
            }

            response.put("Error", errors); // include error message in validation

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
