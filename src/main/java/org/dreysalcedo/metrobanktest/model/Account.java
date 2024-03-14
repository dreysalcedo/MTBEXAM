package org.dreysalcedo.metrobanktest.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "account_table")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerNumber;

   // @NotBlank(message = "Customer Name is required field")
    @Column(columnDefinition = "CHAR(50)", nullable = false)
    private String customerName;

   // @NotBlank(message = "Customer Mobbile is required field")
    @Column(columnDefinition = "CHAR(20)",  nullable = false)
    private String customerMobile;
    @NotBlank(message = "Email is required field")
    @Column(columnDefinition = "CHAR(50)", nullable = false)
    @Email(message = "Invalid email format")
    private String customerEmail;

   // @NotBlank(message = "address2 is required field")
    @Column(columnDefinition = "CHAR(100)", nullable = false)
    private String address1;

    //@NotBlank(message = "address2 is required field")
    @Column(columnDefinition = "CHAR(100)", nullable = false)
    private String address2;


    @NotNull(message = "accountNumber must not be null")
    @Positive(message = "accountNumber must be a positive number")
    @Column(columnDefinition = "LONG(100)", nullable = false)
    private Long accountNumber;

    @NotNull(message = "availableBalance must not be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "availableBalance must be greater than 0")
    @Column(columnDefinition = "DOUBLE(100)", nullable = false)
    private Double availableBalance;



    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private AccountType accountType;


    // no argument constructor. boilerplate needed for JPA
    public Account() {
    }

    // Constructor for API specification
    public Account(Long customerNumber, String customerName, String customerMobile, String customerEmail,
                   String address1, String address2, AccountType accountType, Long accountNumber, double availableBalance) {
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.customerMobile = customerMobile;
        this.customerEmail = customerEmail;
        this.address1 = address1;
        this.address2 = address2;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.availableBalance = availableBalance;
    }


    public enum AccountType {
        S, // Savings
        C  // Checking
    }

    public Long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getAvailableBalance() {
        return availableBalance;
    }

    public void setAccountBalance(Double availableBalance) {
        this.availableBalance = availableBalance;
    }

}
