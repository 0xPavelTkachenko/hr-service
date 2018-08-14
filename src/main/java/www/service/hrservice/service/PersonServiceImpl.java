package www.service.hrservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import www.service.hrservice.dao.AccountDao;
import www.service.hrservice.dao.PersonDao;
import www.service.hrservice.dao.StateDao;
import www.service.hrservice.dao.exception.ObjectNotFoundException;
import www.service.hrservice.model.Person;
import www.service.hrservice.service.exception.ValidationException;

import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private StateDao stateDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public int create(Person person) throws ValidationException {
        if (person.getAccount() != null && !accountDao.exists(person.getAccount().getId())) {
            throw new ValidationException(messageSource.getMessage("error.account.notfound", null, Locale.getDefault()));
        }

        if (person.getState() != null && !stateDao.exists(person.getState().getId())) {
            throw new ValidationException(messageSource.getMessage("error.state.notfound", null, Locale.getDefault()));
        }

        person.setCreatedWhen(new Date());
        return personDao.create(person);
    }

    @Override
    public List<Person> findAll(int limit, int offset) {
        return personDao.findAll(limit, offset);
    }

    @Override
    public List<Person> findAllByIgnored(int limit, int offset, boolean ignored) {
        return personDao.findAllByIgnored(limit, offset, ignored);
    }

    @Override
    public Person findById(int id) throws ObjectNotFoundException {
        return personDao.findById(id);
    }

    @Override
    public void update(int id, Person person) throws ObjectNotFoundException, ValidationException {
        if (person.getAccount() != null && !accountDao.exists(person.getAccount().getId())) {
            throw new ValidationException(messageSource.getMessage("error.account.notfound", null, Locale.getDefault()));
        }

        if (person.getState() != null && !stateDao.exists(person.getState().getId())) {
            throw new ValidationException(messageSource.getMessage("error.state.notfound", null, Locale.getDefault()));
        }

        personDao.update(id, person);
    }

    @Override
    public void delete(int id) throws ObjectNotFoundException {
        personDao.delete(id);
    }

    @Override
    public boolean exists(int id) {
        return personDao.exists(id);
    }

}
