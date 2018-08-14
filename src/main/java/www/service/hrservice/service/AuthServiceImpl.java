package www.service.hrservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import www.service.hrservice.dao.AccountDao;
import www.service.hrservice.dao.exception.ObjectNotFoundException;
import www.service.hrservice.global.GlobalPropertyHandler;
import www.service.hrservice.model.Account;

public class AuthServiceImpl implements AuthService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GlobalPropertyHandler propertyHandler;

    @Override
    public boolean isAdmin(String key) {
        return propertyHandler.getKey().equals(key);
    }

    @Override
    public boolean isClient(String login, String password) {
        if (login == null || password == null) {
            return false;
        }

        Account account;
        try {
            account = accountDao.findByLogin(login);
        } catch (ObjectNotFoundException e) {
            return false;
        }

        return passwordEncoder.matches(password, account.getPassword());
    }

}
