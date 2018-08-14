package www.service.hrservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import www.service.hrservice.dao.AccountDao;
import www.service.hrservice.dao.exception.ObjectNotFoundException;
import www.service.hrservice.model.Account;
import www.service.hrservice.service.exception.ValidationException;

import java.util.List;
import java.util.Locale;

public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MessageSource messageSource;

    @Override
    public int create(Account account) throws ValidationException {
        if (account.getLogin() == null || account.getLogin().isEmpty()) {
            throw new ValidationException(messageSource.getMessage("error.account.login.notfound", null, Locale.getDefault()));
        }

        if (account.getPassword() == null || account.getPassword().isEmpty()) {
            throw new ValidationException(messageSource.getMessage("error.account.password.notfound", null, Locale.getDefault()));
        }

        if (accountDao.exists(account.getLogin())) {
            throw new ValidationException(messageSource.getMessage("error.account.alreadyexists", null, Locale.getDefault()));
        }

        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountDao.create(account);
    }

    @Override
    public List<Account> findAll(int limit, int offset) {
        return accountDao.findAll(limit, offset);
    }

    @Override
    public Account findById(int id) throws ObjectNotFoundException {
        return accountDao.findById(id);
    }

    @Override
    public void update(int id, Account account) throws ObjectNotFoundException, ValidationException {
        if (account.getLogin() == null || account.getLogin().isEmpty()) {
            throw new ValidationException(messageSource.getMessage("error.account.login.notfound", null, Locale.getDefault()));
        }

        if (account.getPassword() != null && account.getPassword().isEmpty()) {
            throw new ValidationException(messageSource.getMessage("error.account.password.notfound", null, Locale.getDefault()));
        }

        try {
            Account tmpAccount = accountDao.findByLogin(account.getLogin());
            if (tmpAccount.getId() != id) {
                throw new ValidationException(messageSource.getMessage("error.account.alreadyexists", null, Locale.getDefault()));
            }
        } catch (ValidationException e) {
            throw e;
        } catch (ObjectNotFoundException e) {}

        if (account.getPassword() != null) {
            account.setPassword(passwordEncoder.encode(account.getPassword()));
        }
        accountDao.update(id, account);
    }

    @Override
    public void delete(int id) throws ObjectNotFoundException {
        accountDao.delete(id);
    }

    @Override
    public boolean exists(int id) {
        return accountDao.exists(id);
    }

    @Override
    public boolean exists(String login) {
        return accountDao.exists(login);
    }

}
