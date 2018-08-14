package www.service.hrservice.service;

import www.service.hrservice.model.Account;

public interface AccountService extends ModelService<Account> {

    boolean exists(String login);

}
