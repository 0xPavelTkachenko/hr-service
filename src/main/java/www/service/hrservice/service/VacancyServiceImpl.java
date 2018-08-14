package www.service.hrservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import www.service.hrservice.dao.AccountDao;
import www.service.hrservice.dao.VacancyDao;
import www.service.hrservice.dao.exception.ObjectNotFoundException;
import www.service.hrservice.model.Vacancy;
import www.service.hrservice.service.exception.ValidationException;

import java.util.Date;
import java.util.List;
import java.util.Locale;

public class VacancyServiceImpl implements VacancyService {

    @Autowired
    private VacancyDao vacancyDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public int create(Vacancy vacancy) throws ValidationException {
        if (vacancy.getAccount() != null && !accountDao.exists(vacancy.getAccount().getId())) {
            throw new ValidationException(messageSource.getMessage("error.account.notfound", null, Locale.getDefault()));
        }

        vacancy.setCreatedWhen(new Date());
        return vacancyDao.create(vacancy);
    }

    @Override
    public List<Vacancy> findAll(int limit, int offset) {
        return vacancyDao.findAll(limit, offset);
    }

    @Override
    public Vacancy findById(int id) throws ObjectNotFoundException {
        return vacancyDao.findById(id);
    }

    @Override
    public void update(int id, Vacancy vacancy) throws ObjectNotFoundException {
        vacancyDao.update(id, vacancy);
    }

    @Override
    public void delete(int id) throws ObjectNotFoundException {
        vacancyDao.delete(id);
    }

    @Override
    public boolean exists(int id) {
        return vacancyDao.exists(id);
    }

}
