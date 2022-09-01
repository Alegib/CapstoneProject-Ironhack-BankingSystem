package Ironhack.CapstoneProject.Services;

import Ironhack.CapstoneProject.DTO.AccountDTO;
import Ironhack.CapstoneProject.models.Accounts.*;
import Ironhack.CapstoneProject.models.Enums.AccountType;
import Ironhack.CapstoneProject.models.Enums.Status;
import Ironhack.CapstoneProject.models.Users.AccountHolder;
import Ironhack.CapstoneProject.models.repositories.AccountHolderRepository;
import Ironhack.CapstoneProject.models.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;


    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }
  /*
    public Account createAccount(AccountDTO accountDTO) {
        if (accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).isPresent()) {
            if (accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).get().getAge() < 24 && accountDTO.getAccountType().equals(AccountType.STUDENT_CHECKING)) {
                AccountHolder accountHolder = accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).get();
                StudentChecking studentChecking = new StudentChecking(accountHolder, accountDTO.getBalance(), Status.ACTIVE, accountDTO.getSecretKey());
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
                checking.setBalance(accountDTO.getBalance());
                checking.setSecretKey(accountDTO.getSecretKey());
                checking.setStatus(Status.ACTIVE);
                if (accountDTO.getSecondaryOwnerId() != null) {
                    if (accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).isPresent()) {
                        checking.setSecondaryOwner(accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).get());
                        return accountRepository.save(checking);
                    }
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Id inserted as a second-owner doesn't exist.");
                }
                return accountRepository.save(checking);

            } else if (accountDTO.getAccountType().equals(AccountType.SAVINGS)) {
                Savings savings = new Savings();
                savings.setPrimaryOwner(accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).get());
                savings.setBalance(accountDTO.getBalance());
                savings.setSecretKey(accountDTO.getSecretKey());
                savings.setStatus(Status.ACTIVE);

                if(accountDTO.getInterestRate() == null){
                    savings.setInterestRate(Savings.DEFAULT_INTEREST_RATE);
                }
                else if(accountDTO.getInterestRate().compareTo(Savings.MAX_INTEREST_RATE) > 0){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Interest rate cannot be higher than " + Savings.MAX_INTEREST_RATE);
                }
                else if(accountDTO.getInterestRate().compareTo(Savings.DEFAULT_INTEREST_RATE) < 0){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Interest rate cannot be lower than " + Savings.DEFAULT_INTEREST_RATE);
                }
                else{ savings.setInterestRate(accountDTO.getInterestRate());}


                if (accountDTO.getSecondaryOwnerId() != null) {
                    if (accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).isPresent()) {
                        savings.setSecondaryOwner(accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).get());
                        return accountRepository.save(savings);
                    }
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Id inserted as a second-owner doesn't exist.");
                }
                return accountRepository.save(savings);

            } else if (accountDTO.getAccountType().equals(AccountType.CREDIT_CARD)) {
                CreditCard creditCard = new CreditCard();
                creditCard.setPrimaryOwner(accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).get());
                creditCard.setBalance(accountDTO.getBalance());
                creditCard.setSecretKey(creditCard.getSecretKey());
                creditCard.setStatus(Status.ACTIVE);
                if(accountDTO.getInterestRate() == null){
                    creditCard.setInterestRate(CreditCard.DEFAULT_INTEREST_RATE);
                }
                else if(accountDTO.getInterestRate().compareTo(CreditCard.MIN_INTEREST_RATE) < 0){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Interest rate cannot be lower than " + CreditCard.MIN_INTEREST_RATE);
                }
                else if(accountDTO.getInterestRate().compareTo(CreditCard.DEFAULT_INTEREST_RATE) > 0){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Interest rate cannot be higher than " + CreditCard.DEFAULT_INTEREST_RATE);
                }
                else if (accountDTO.getCreditLimit().getAmount().compareTo(CreditCard.MAX_CREDIT_LIMIT) > 0){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credit Limit cannot be higher than " + CreditCard.MAX_CREDIT_LIMIT);
                }
                else if (accountDTO.getCreditLimit().getAmount().compareTo(CreditCard.DEFAULT_CREDIT_LIMIT) < 0){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credit Limit cannot be lower than " + CreditCard.DEFAULT_CREDIT_LIMIT);
                }
                else{ creditCard.setInterestRate(accountDTO.getInterestRate());}

                if (accountDTO.getSecondaryOwnerId() != null) {
                    if (accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).isPresent()) {
                        creditCard.setSecondaryOwner(accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).get());
                        return accountRepository.save(creditCard);
                    }
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Id inserted as a second-owner doesn't exist.");
                }
                return accountRepository.save(creditCard);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No records found, register the account holder before opening an account or try again with the correct Id.");
    }

   */

}
