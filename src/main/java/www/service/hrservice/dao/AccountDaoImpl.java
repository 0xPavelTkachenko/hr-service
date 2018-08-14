package www.service.hrservice.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import www.service.hrservice.dao.exception.ObjectNotFoundException;
import www.service.hrservice.model.Account;

import java.util.List;

public class AccountDaoImpl implements AccountDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int create(Account account) {
        int id;
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            id = (Integer) session.save(account);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }

        return id;
    }

    @Override
    public List<Account> findAll(int limit, int offset) {
        List<Account> list;
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            if (limit <= 0) {
                if (offset <= 0) {
                    list = session.createQuery("FROM Account").list();
                } else {
                    list = session.createQuery("FROM Account").setFirstResult(offset).list();
                }
            } else {
                if (offset <= 0) {
                    list = session.createQuery("FROM Account").setMaxResults(limit).list();
                } else {
                    list = session.createQuery("FROM Account").setFirstResult(offset).setMaxResults(limit).list();
                }
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        return list;
    }

    @Override
    public Account findById(int id) throws ObjectNotFoundException {
        Account account;
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            account = session.get(Account.class, id);
            if (account == null) {
                throw new ObjectNotFoundException();
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        return account;
    }

    @Override
    public Account findByLogin(String login) throws ObjectNotFoundException {
        Account account;
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Account WHERE login = :login");
            query.setParameter("login", login);
            account = (Account) query.uniqueResult();
            if (account == null) {
                throw new ObjectNotFoundException();
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        return account;
    }

    @Override
    public void update(int id, Account account) throws ObjectNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            Account tmpAccount = session.get(Account.class, id);
            if (tmpAccount == null) {
                throw new ObjectNotFoundException();
            }
            if (account.getPassword() == null) {
                account.setPassword(tmpAccount.getPassword());
            }
            account.setId(id);
            session.merge(account);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(int id) throws ObjectNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            Account account = session.get(Account.class, id);
            if (account == null) {
                throw new ObjectNotFoundException();
            }
            session.delete(account);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public boolean exists(int id) {
        Account account;
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            account = session.get(Account.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        return account != null;
    }

    @Override
    public boolean exists(String login) {
        Account account;
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Account WHERE login = :login");
            query.setParameter("login", login);
            account = (Account) query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        return account != null;
    }

}
