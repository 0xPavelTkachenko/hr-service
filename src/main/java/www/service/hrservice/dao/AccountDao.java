package www.service.hrservice.dao;

import www.service.hrservice.dao.exception.ObjectNotFoundException;
import www.service.hrservice.model.Account;

public interface AccountDao extends Dao<Account> {

    Account findByLogin(String login) throws ObjectNotFoundException;

    boolean exists(String login);

}
