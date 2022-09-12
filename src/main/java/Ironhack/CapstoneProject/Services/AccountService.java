package Ironhack.CapstoneProject.Services;

import Ironhack.CapstoneProject.DTO.AccountBalanceDTO;
import Ironhack.CapstoneProject.DTO.AccountDTO;
import Ironhack.CapstoneProject.DTO.AccountStatusDTO;

import Ironhack.CapstoneProject.models.Accounts.*;
import Ironhack.CapstoneProject.models.Enums.AccountType;
import Ironhack.CapstoneProject.models.Enums.Status;
import Ironhack.CapstoneProject.models.Transactions.Money;
import Ironhack.CapstoneProject.models.Users.AccountHolder;
import Ironhack.CapstoneProject.models.Users.User;
import Ironhack.CapstoneProject.models.repositories.AccountHolderRepository;
import Ironhack.CapstoneProject.models.repositories.AccountRepository;
import Ironhack.CapstoneProject.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    UserRepository userRepository;


    public List<Account> findAllAccounts() {

        return accountRepository.findAll();
    }

    public Account createCheckingAccount(AccountDTO accountDTO) {
        if (accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).isPresent()) {
            if (accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).get().getAge() < 24) {
                AccountHolder accountHolder = accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).get();
                StudentChecking studentChecking = new StudentChecking(accountHolder, accountDTO.getBalance(), Status.ACTIVE);
                studentChecking.setCreationDate();
                if (accountDTO.getSecondaryOwnerId() != null) {
                    if (accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).isPresent()) {
                        studentChecking.setSecondaryOwner(accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).get());
                        return accountRepository.save(studentChecking);
                    }
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Id inserted as a second-owner doesn't exist.");
                }
                return accountRepository.save(studentChecking);
            } else if (accountDTO.getAccountType().equals(AccountType.CHECKING)) {
                Checking checking = new Checking();
                checking.setPrimaryOwner(accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).get());
                checking.setSecretKey();
                checking.setStatus(Status.ACTIVE);
                checking.setCreationDate();
                checking.setInterestDate(checking.getCreationDate());
                checking.setMonthlyFeeDate(LocalDateTime.now());

                if (accountDTO.getBalance().getAmount().compareTo(Checking.MINIMUM_BALANCE.getAmount()) < 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Balance cannot be lower than " + Checking.MINIMUM_BALANCE.getAmount());
                }

                checking.setBalance(accountDTO.getBalance());

                if (accountDTO.getSecondaryOwnerId() != null) {
                    if (accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).isPresent()) {
                        checking.setSecondaryOwner(accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).get());
                        return accountRepository.save(checking);
                    }
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Id inserted as a second-owner doesn't exist.");
                }
                return accountRepository.save(checking);

            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No records found, register the account holder before opening an account or try again with the correct Id.");

    }

    public Account createSavingsAccount(AccountDTO accountDTO) {
        if (accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).isPresent()) {
            Savings savings = new Savings();
            savings.setPrimaryOwner(accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).get());
            savings.setBalance(accountDTO.getBalance());
            savings.setSecretKey();
            savings.setStatus(Status.ACTIVE);
            savings.setCreationDate();
            savings.setInterestDate(LocalDateTime.now());

            if (accountDTO.getInterestRate() == null) {
                savings.setInterestRate(Savings.DEFAULT_INTEREST_RATE);
            } else if (accountDTO.getInterestRate().compareTo(Savings.MAX_INTEREST_RATE) > 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Interest rate cannot be higher than " + Savings.MAX_INTEREST_RATE);
            } else if (accountDTO.getInterestRate().compareTo(Savings.DEFAULT_INTEREST_RATE) < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Interest rate cannot be lower than " + Savings.DEFAULT_INTEREST_RATE);
            } else if (accountDTO.getInterestRate() != null) {
                savings.setInterestRate(accountDTO.getInterestRate());
            }


            if (accountDTO.getSecondaryOwnerId() != null) {
                if (accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).isPresent()) {
                    savings.setSecondaryOwner(accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).get());
                    return accountRepository.save(savings);
                }
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Id inserted as a second-owner doesn't exist.");
            }
            return accountRepository.save(savings);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No records found, register the account holder before opening an account or try again with the correct Id.");
    }


        public Account createCreditCard(AccountDTO accountDTO) {
            if (accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).isPresent()) {
                CreditCard creditCard = new CreditCard();
                creditCard.setPrimaryOwner(accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).get());
                creditCard.setBalance(accountDTO.getBalance());
                creditCard.setSecretKey();
                creditCard.setStatus(Status.ACTIVE);
                creditCard.setCreationDate();
                creditCard.setInterestDate(LocalDateTime.now());
                creditCard.setMonthlyFeeDate(LocalDateTime.now());
                creditCard.setCardNumber();

                if (accountDTO.getInterestRate() == null) {
                    creditCard.setInterestRate(CreditCard.DEFAULT_INTEREST_RATE);
                } else if (accountDTO.getInterestRate().compareTo(CreditCard.MIN_INTEREST_RATE) < 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Interest rate cannot be lower than " + CreditCard.MIN_INTEREST_RATE);
                } else if (accountDTO.getInterestRate().compareTo(CreditCard.DEFAULT_INTEREST_RATE) > 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Interest rate cannot be higher than " + CreditCard.DEFAULT_INTEREST_RATE);
                } else if (accountDTO.getInterestRate() != null) {
                    creditCard.setInterestRate(accountDTO.getInterestRate());
                }

                if (creditCard.getCreditLimit() == null) {
                    creditCard.setCreditLimit(new Money(CreditCard.DEFAULT_CREDIT_LIMIT));
                } else if (accountDTO.getCreditLimit().getAmount().compareTo(CreditCard.MAX_CREDIT_LIMIT) > 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credit Limit cannot be higher than " + CreditCard.MAX_CREDIT_LIMIT);
                } else if (accountDTO.getCreditLimit().getAmount().compareTo(CreditCard.DEFAULT_CREDIT_LIMIT) < 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credit Limit cannot be lower than " + CreditCard.DEFAULT_CREDIT_LIMIT);
                } else if (accountDTO.getCreditLimit() != null) {
                    creditCard.setCreditLimit(accountDTO.getCreditLimit());
                }


                if (accountDTO.getSecondaryOwnerId() != null) {
                    if (accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).isPresent()) {
                        creditCard.setSecondaryOwner(accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).get());
                        return accountRepository.save(creditCard);
                    }
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Id inserted as a second-owner doesn't exist.");
                }
                return accountRepository.save(creditCard);
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No records found, register the account holder before opening an account or try again with the correct Id.");

        }




    public Account changeStatus(AccountStatusDTO accountStatusDTO){
        if (accountRepository.findById(accountStatusDTO.getId()).isPresent()) {
            Account account = accountRepository.findById(accountStatusDTO.getId()).get();
            account.setStatus(accountStatusDTO.getStatus());
            return accountRepository.save(account);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No records found");
    }

    public Account modifyBalance(AccountBalanceDTO accountBalanceDTO){
        if (accountRepository.findById(accountBalanceDTO.getId()).isPresent()) {
            /*
            if (accountBalanceDTO.getBalance().getAmount().compareTo(BigDecimal.valueOf(0)) < 0){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Balance cannot be lower than 0");
            }
             */
            Account account = accountRepository.findById(accountBalanceDTO.getId()).get();
            account.setBalance(accountBalanceDTO.getBalance());
            return accountRepository.save(account);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No records found");

    }
    public List<Account> showAccounts(UserDetails userDetails){

        User userAccount = userRepository.findByUsername(userDetails.getUsername()).get();

        return accountRepository.findAllById(Collections.singleton(userAccount.getId()));
    }
    public Account findById(Map<String, Long> header){
        return accountRepository.findById(header.get("id")).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found."));
    }
    public void deleteAccount(AccountDTO accountDTO){

        if(accountRepository.findById(accountDTO.getAccountId()).isPresent()){
                accountRepository.deleteById(accountDTO.getAccountId());
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found.");
        }
    }

}
