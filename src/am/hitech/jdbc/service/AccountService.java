package am.hitech.jdbc.service;

import am.hitech.jdbc.model.Account;
import am.hitech.jdbc.repo.AccountRepo;
import am.hitech.jdbc.util.exceptionmessage.ErrorMessage;

public class AccountService {
    private final AccountRepo accountRepo = new AccountRepo();

    public void transfer(int from, int to, int amount) {
        accountRepo.transfer(from, to, amount);
    }

    public void createAccount(Account account) {
        int row = accountRepo.createAccount(account);

        if (row == 0) {
            throw new RuntimeException(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }

    }

    public void deleteAccount(Account account) {
        int row = accountRepo.deleteAccount(account);

        if (row == 0) {
            throw new RuntimeException(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }
    }

    public int findByPhone(String num) {
        int code = Integer.parseInt((String) num.subSequence(0,2));
        int number = Integer.parseInt((String) num.subSequence(2,num.length()));

        return accountRepo.findByPhone(code,number);


    }
}
